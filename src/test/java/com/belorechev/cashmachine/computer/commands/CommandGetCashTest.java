package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.data.CashBank;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.belorechev.cashmachine.utility.Dictionary.*;
import static org.junit.Assert.*;

public class CommandGetCashTest implements CommandTestTemplate {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class);
    private final CommandTemplate commandGetCash = new CommandGetCash(cashBankMock);

    private final String identification = "-";

    @Test
    public void shouldReturnTrue_ForIdentificationOfClass() {

        assertTrue(commandGetCash.isSuited(asArray(identification)));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandGetCash.identification = null;
        assertTrue(commandGetCash.isSuited(asArray(identification)));
    }

    @Test
    public void shouldReturnErrorStatus_ForNotSuitedApplyCommand_ByLength() {

        assertEquals(ERROR_STATUS, commandGetCash.apply(asArray(identification)));
    }

    @Test
    public void shouldReturnErrorStatus_ForNotDigitThirdCommand() {

        assertEquals(ERROR_STATUS, commandGetCash.apply(asArray(identification, "USD", "USD")));
    }

    @Test
    public void shouldReturnErrorStatus_ForNotValidCurrency_ByCase() {

        assertEquals(ERROR_STATUS, commandGetCash.apply(asArray(identification, "usd", "100")));
    }

    @Test
    public void shouldReturnErrorStatus_ForNotValidCurrency_ByLength() {

        assertEquals(ERROR_STATUS, commandGetCash.apply(asArray(identification, "USDA", "USD")));
    }

    @Test
    public void shouldReturnOkStatus_ForSuitedApplyCommand() {

        Mockito.when(cashBankMock.get(Mockito.anyString(), Mockito.anyInt())).thenReturn(Optional.of(""));
        assertEquals(OK_STATUS, commandGetCash.apply(asArray(identification, "USD", "100")));
    }
}