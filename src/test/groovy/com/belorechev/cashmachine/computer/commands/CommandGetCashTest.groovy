package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.*
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

//TODO check mock CashBank

class CommandGetCashTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final Validator validatorMock = Mockito.mock(Validator.class)
    private final CommandTemplate commandGetCash = new CommandGetCash(cashBankMock, validatorMock)

    private final String identification = "-"
    private final Integer expectedAmountOfArguments = 3

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
    void shouldReturnOkStatus_ForSuitedApplyCommand_AndInvokeAllMethodsForValidation() {

        Set setForAdding = new TreeSet()
        setForAdding << Mockito.mock(Cash.class)

        when(cashBankMock
                .get(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(setForAdding)

        String[] operation = [identification, "USD", "100"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isPositive(operation[2] as Integer)).thenReturn(true)

        assertThat(commandGetCash.apply(operation), is("0 0" + NEW_LINE + OK_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isPositive(operation[2] as Integer)

    }

    @Test
    void shouldReturnErrorStatus_ForNotSuitedApplyCommand_ByLength_AndInvokeOnlyOneMethodForValidation() {

        String[] operation = [identification]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(true)

        assertThat(commandGetCash.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)

    }

    @Test
    void shouldReturnErrorStatus_ForNotDigitThirdCommand_AndInvokeOnlyOneMethodForValidation() {

        String[] operation = [identification, "USD", "USD"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)

        assertThat(commandGetCash.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidCurrency_ByCase_AndInvokeOnlyTwoMethodsForValidation() {

        String[] operation = [identification, "usd", "100"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(false)

        assertThat(commandGetCash.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidNumber_AndInvokeAllMethodsForValidation() {

        String[] operation = [identification, "USD", "-1"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isPositive(operation[2] as Integer)).thenReturn(false)

        assertThat(commandGetCash.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isPositive(operation[2] as Integer)
    }

}