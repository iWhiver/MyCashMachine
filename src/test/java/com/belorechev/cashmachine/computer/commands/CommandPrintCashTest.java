package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.data.CashBank;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static com.belorechev.cashmachine.utility.Dictionary.*;

public class CommandPrintCashTest implements CommandTestTemplate {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class);

    private final CommandTemplate commandPrintCash = new CommandPrintCash(cashBankMock);

    private final String identification = "?";

    @Test
    public void shouldReturnTrueForIdentificationOfClass() {

        assertTrue(commandPrintCash.isSuited(asArray(identification)));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfClassNotChangeValueOfIdentification() {

        commandPrintCash.identification = null;
        assertTrue(commandPrintCash.isSuited(asArray(identification)));
    }

    @Test
    public void shouldReturnErrorForNotSuitedApplyCommand() {

        assertEquals(ERROR_STATUS, commandPrintCash.apply(asArray(identification, identification)));
    }

    @Test
    public void shouldReturnOkStatusForSuitedApplyCommand() {

        Mockito.when(cashBankMock.getPrintForm()).thenReturn("");
        assertEquals(OK_STATUS, commandPrintCash.apply(asArray(identification)));
    }
}