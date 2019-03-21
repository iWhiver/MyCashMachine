package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.CashBank
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.ERROR_STATUS
import static com.belorechev.cashmachine.utility.Dictionary.OK_STATUS
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class CommandAddNotesTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final Validator validatorMock = Mockito.mock(Validator.class)
    private final CommandTemplate commandAddNotes = new CommandAddNotes(cashBankMock, validatorMock)

    private final String identification = "+"
    private final Integer expectedAmountOfArguments = 4

    @Test
    void shouldReturnTrue_ForIdentificationOfClass() {
        assertThat(commandAddNotes.isSuited(identification), is(true))
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandAddNotes.identification = null
        assertThat(commandAddNotes.isSuited(identification), is(true))
    }

    @Test
    void shouldReturnOkStatus_ForSuitedCommand_AndInvokeAllMethodsForValidation_AndInvokeMethodFromCashBank() {

        String[] operation = [identification, "USD", "10", "10"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isValidValue(operation[2] as Integer)).thenReturn(true)
        when(validatorMock.isPositive(operation[3] as Integer)).thenReturn(true)

        assertThat(commandAddNotes.apply(operation), is(OK_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isValidValue(operation[2] as Integer)
        verify(validatorMock).isPositive(operation[3] as Integer)

        verify(cashBankMock).add(operation[1], operation[2] as Integer, operation[3] as Integer)
    }

    @Test
    void shouldReturnErrorStatus_ForNotSuitedApplyCommand_ByLength_AndInvokeOnlyOneMethodForValidation() {

        String[] operation = [identification]

        when(validatorMock.isInvalidAmountOfArguments(operation as String[], expectedAmountOfArguments)).thenReturn(true)
        assertThat(commandAddNotes.apply(operation), is(ERROR_STATUS))
        verify(validatorMock).isInvalidAmountOfArguments(operation as String[], expectedAmountOfArguments)
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidCurrency_ByCase_AndInvokeOnlyTwoMethodsForValidation() {

        String[] operation = [identification, "usd", "30", "10"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(false)

        assertThat(commandAddNotes.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidValue_AndInvokeOnlyThreeMethodsForValidation() {

        String[] operation = [identification, "USD", "30", "10"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isValidValue(operation[2] as Integer)).thenReturn(false)

        assertThat(commandAddNotes.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isValidValue(operation[2] as Integer)
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidNumber_AndInvokeOnlyAllMethodsForValidation() {

        String[] operation = [identification, "USD", "100", "-1"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        when(validatorMock.isValidCurrency(operation[1])).thenReturn(true)
        when(validatorMock.isValidValue(operation[2] as Integer)).thenReturn(true)
        when(validatorMock.isPositive(operation[3] as Integer)).thenReturn(false)

        assertThat(commandAddNotes.apply(operation), is(ERROR_STATUS))

        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(validatorMock).isValidCurrency(operation[1])
        verify(validatorMock).isValidValue(operation[2] as Integer)
        verify(validatorMock).isPositive(operation[3] as Integer)
    }


    @Test
    void shouldReturnErrorStatus_ForThirdNotDigitCommand_AndInvokeOnlyOneMethodForValidation() {

        String[] operation = [identification, "USD", "USD", "10"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        assertThat(commandAddNotes.apply(operation), is(ERROR_STATUS))
        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
    }

    @Test
    void shouldReturnErrorStatus_ForFourthNotDigitCommand_AndInvokeOnlyOneMethodForValidation() {

        String[] operation = [identification, "USD", "100", "USD"]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        assertThat(commandAddNotes.apply(operation), is(ERROR_STATUS))
        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
    }
}