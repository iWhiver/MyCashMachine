package com.belorechev.cashmachine.computer

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.*
import static org.hamcrest.CoreMatchers.either
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class ComputerTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final Validator validatorMock = Mockito.mock(Validator.class)
    private final Computer computer = new Computer(cashBankMock, validatorMock)

    @Test
    void shouldReturnErrorStatus_ForEmptyCommand() {
        assertThat(computer.calculate(""), is(ERROR_STATUS))
    }

    @Test
    void shouldReturnErrorStatus_ForNullCommand() {
        assertThat(computer.calculate(null), is(ERROR_STATUS))
    }

    @Test
    void shouldReturnExitStatus_ForUppercaseCommandExit() {
        assertThat(computer.calculate("EXIT"), is(EXIT_STATUS))
    }

    @Test
    void shouldReturnExitStatus_ForLowercaseCommandExit() {
        assertThat(computer.calculate("exit"), is(EXIT_STATUS))
    }

    @Test
    void shouldReturnOkStatus_ForCommandPrintCash_AndInvokeMethodForValidation_AndInvokeMethodFromCashBank() {
        String[] operation = ["?"]
        int expectedAmountOfArguments = 1

        when(cashBankMock.getPrintForm()).thenReturn("")
        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)

        assertThat(computer.calculate(operation[0]), is(OK_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(cashBankMock).getPrintForm()
    }

    @Test
    void shouldReturnOkStatus_ForCommandAddNotes_AndInvokeAllMethodsForValidation_AndInvokeMethodFromCashBank() {

        String[] operation = ["+", "USD", "10", "10"]

        int expectedAmountOfArguments = 4

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isValidValue(operation[2] as Integer)).thenReturn(true)
        when(validatorMock.isPositive(operation[3] as Integer)).thenReturn(true)

        assertThat(computer.calculate(operation.join(" ")), is(OK_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isValidValue(operation[2] as Integer)
        verify(validatorMock).isPositive(operation[3] as Integer)
        verify(cashBankMock).add(operation[1], operation[2] as Integer, operation[3] as Integer)
    }

    @Test
    void shouldReturnOkStatus_ForCommandGetCash_AndInvokeAllMethodsForValidation_AndInvokeMethodFromCashBank() {

        Set setForAdding = new TreeSet()
        setForAdding << Mockito.mock(Cash.class)
        when(cashBankMock.get(Mockito.anyString(), Mockito.anyInt())).thenReturn(setForAdding)

        String[] operation = ["-", "USD", "10"]

        int expectedAmountOfArguments = 3

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isPositive(operation[2] as Integer)).thenReturn(true)

        assertThat(computer.calculate(operation.join(" ")), either(is("0 0" + NEW_LINE + OK_STATUS)) | is("0 0" + OK_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isPositive(operation[2] as Integer)
        verify(cashBankMock).get(operation[1], operation[2] as Integer)
    }
}