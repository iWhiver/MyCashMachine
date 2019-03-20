package com.belorechev.cashmachine.computer

import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.*


class ComputerTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final Computer computer = new Computer(cashBankMock)

    @Test
    void shouldReturnErrorStatus_ForEmptyCommand() {
        assert ERROR_STATUS == computer.calculate("")
    }

    @Test
    void shouldReturnErrorStatus_ForNullCommand() {
        assert ERROR_STATUS == computer.calculate(null)
    }

    @Test
    void shouldReturnExitStatus_ForUppercaseCommandExit() {
        assert EXIT_STATUS == computer.calculate("EXIT")
    }

    @Test
    void shouldReturnExitStatus_ForLowercaseCommandExit() {
        assert EXIT_STATUS == computer.calculate("exit")
    }

    @Test
    void shouldReturnOkStatus_ForCommandPrintCash() {
        Mockito.when(cashBankMock.getPrintForm()).thenReturn("")
        assert OK_STATUS == computer.calculate("?")
    }

    @Test
    void shouldReturnOkStatus_ForCommandAddNotes() {
        assert OK_STATUS == computer.calculate("+ USD 10 50")
    }

    @Test
    void shouldReturnOkStatus_ForCommandGetCash() {

        Set setForAdding = new TreeSet()

        setForAdding << Mockito.mock(Cash.class)

        Mockito.when(cashBankMock
                .get(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(setForAdding)

        assert "0 0$NEW_LINE$OK_STATUS" == computer.calculate("- USD 10")
    }
}