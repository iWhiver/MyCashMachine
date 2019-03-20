package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.*

class CommandGetCashTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final CommandTemplate commandGetCash = new CommandGetCash(cashBankMock)

    private final String identification = "-"

    @Test
    void shouldReturnTrue_ForIdentificationOfClass() {

        assert commandGetCash.isSuited(identification)
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandGetCash.identification = null
        assert commandGetCash.isSuited(identification)
    }

    @Test
    void shouldReturnErrorStatus_ForNotSuitedApplyCommand_ByLength() {

        assert ERROR_STATUS == commandGetCash.apply(identification)
    }

    @Test
    void shouldReturnErrorStatus_ForNotDigitThirdCommand() {

        assert ERROR_STATUS == commandGetCash.apply(identification, "USD", "USD")
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidCurrency_ByCase() {

        assert ERROR_STATUS == commandGetCash.apply(identification, "usd", "100")
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidCurrency_ByLength() {

        assert ERROR_STATUS == commandGetCash.apply(identification, "USDA", "USD")
    }

    @Test
    void shouldReturnOkStatus_ForSuitedApplyCommand() {

        Set setForAdding = new TreeSet()

        setForAdding << Mockito.mock(Cash.class)

        Mockito.when(cashBankMock
                .get(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(setForAdding)

        assert "0 0$NEW_LINE$OK_STATUS" == commandGetCash.apply(identification, "USD", "100")
    }
}