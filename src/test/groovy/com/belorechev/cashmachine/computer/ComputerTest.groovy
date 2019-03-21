package com.belorechev.cashmachine.computer

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.*
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

//TODO add verify for cashBankMonk

class ComputerTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final Validator validatorMock = Mockito.mock(Validator.class)
    private final Computer computer = new Computer(cashBankMock, validatorMock)

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
        String[] operation = ["?"]
        int expectedAmountOfArguments = 1

        when(cashBankMock.getPrintForm()).thenReturn("")
        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        assert OK_STATUS == computer.calculate(operation[0])
        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
    }

    @Test
    void shouldReturnOkStatus_ForCommandAddNotes() {

        String[] operation = ["+", "USD", "10", "10"]

        int expectedAmountOfArguments = 4

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isValidValue(operation[2] as Integer)).thenReturn(true)
        when(validatorMock.isPositive(operation[3] as Integer)).thenReturn(true)

        assert computer.calculate(operation.join(" ")) == OK_STATUS

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isValidValue(operation[2] as Integer)
        verify(validatorMock).isPositive(operation[3] as Integer)
    }

    @Test
    void shouldReturnOkStatus_ForCommandGetCash() {

        Set setForAdding = new TreeSet()
        setForAdding << Mockito.mock(Cash.class)
        when(cashBankMock.get(Mockito.anyString(), Mockito.anyInt())).thenReturn(setForAdding)

        String[] operation = ["-", "USD", "10"]

        int expectedAmountOfArguments = 3

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isPositive(operation[2] as Integer)).thenReturn(true)

        assert computer.calculate(operation.join(" ")) == "0 0$NEW_LINE$OK_STATUS"

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isPositive(operation[2] as Integer)


    }
}