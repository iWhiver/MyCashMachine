package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.ERROR_STATUS
import static com.belorechev.cashmachine.utility.Dictionary.OK_STATUS

class CommandPrintCashTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)

    private final CommandTemplate commandPrintCash = new CommandPrintCash(cashBankMock)

    private final String identification = "?"

    @Test
    void shouldReturnTrueForIdentificationOfClass() {

        assert commandPrintCash.isSuited(identification)
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowExceptionIfClassNotChangeValueOfIdentification() {

        commandPrintCash.identification = null
        assert commandPrintCash.isSuited(identification)
    }

    @Test
    void shouldReturnErrorForNotSuitedApplyCommand() {

        assert ERROR_STATUS == commandPrintCash.apply(identification, identification)
    }

    @Test
    void shouldReturnOkStatusForSuitedApplyCommand() {

        Mockito.when(cashBankMock.getPrintForm()).thenReturn("")
        assert OK_STATUS == commandPrintCash.apply(identification)
    }


}