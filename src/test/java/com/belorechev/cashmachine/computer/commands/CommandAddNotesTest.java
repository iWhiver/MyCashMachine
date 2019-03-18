package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;
import org.junit.Test;
import org.mockito.Mockito;

import static com.belorechev.cashmachine.utility.Dictionary.*;
import static org.junit.Assert.*;

public class CommandAddNotesTest implements CommandTestTemplate {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class);
    private final CommandTemplate commandAddNotes = new CommandAddNotes(cashBankMock);

    private final String identification = "+";

    @Test
    public void shouldReturnTrue_ForIdentificationOfClass() {

        assertTrue(commandAddNotes.isSuited(asArray(identification)));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandAddNotes.identification = null;
        assertTrue(commandAddNotes.isSuited(asArray(identification)));
    }

    @Test
    public void shouldReturnOkStatus_ForSuitedCommand() {

        assertEquals(OK_STATUS, commandAddNotes.apply(asArray(identification, "USD", "10", "10")));
    }

    @Test
    public void shouldReturnOkStatus_ForEachCommandFromDictionary() {

        for (Integer banknote : Dictionary.getValidBanknotes()) {
            String command = String.format("+ USD %d 50", banknote);
            assertEquals(OK_STATUS, commandAddNotes.apply(command.split(" ")));
        }
    }

    @Test
    public void shouldReturnErrorStatus_ForEachCommandFromDictionary() {

        for (Integer banknote : Dictionary.getValidBanknotes()) {
            String command = String.format("+ USD %d 50", banknote);
            assertEquals(OK_STATUS, commandAddNotes.apply(command.split(" ")));
        }
    }

    @Test
    public void shouldReturnErrorStatus_ForNotSuitedApplyCommand_ByLength() {

        assertEquals(ERROR_STATUS, commandAddNotes.apply(asArray(identification)));
    }

    @Test
    public void shouldReturnErrorStatus_ForNotValidCurrency_ByCase() {

        assertEquals(ERROR_STATUS, commandAddNotes.apply(asArray(identification, "usd", "100", "10")));
    }

    @Test
    public void shouldReturnErrorStatus_ForThirdNotDigitCommand() {

        assertEquals(ERROR_STATUS, commandAddNotes.apply(asArray(identification, "USD", "USD", "10")));
    }

    @Test
    public void shouldReturnErrorStatus_ForFourthNotDigitCommand() {

        assertEquals(ERROR_STATUS, commandAddNotes.apply(asArray(identification, "USD", "100", "USD")));
    }
}