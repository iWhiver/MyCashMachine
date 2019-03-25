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

class CommandPrintCashTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final Validator validatorMock = Mockito.mock(Validator.class)
    private final CommandTemplate commandPrintCash = new CommandPrintCash(cashBankMock, validatorMock)

    private final String identification = "?"
    private final Integer expectedAmountOfArguments = 1

    @Test
    void shouldReturnTrue_ForIdentificationOfClass() {
        assertThat(commandPrintCash.isSuited(identification), is(true))
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandPrintCash.identification = null
        commandPrintCash.isSuited(identification)
    }

    @Test
    void shouldReturnError_ForNotSuitedApplyCommand_AndInvokeMethodForValidation() {

        String[] operation = [identification, identification]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(true)
        assertThat(commandPrintCash.apply(operation), is(ERROR_STATUS))
        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
    }

    @Test
    void shouldReturnOkStatus_ForSuitedApplyCommand_AndInvokeMethodForValidation_AndInvokeMethodFromCashBank() {

        when(cashBankMock.getPrintForm()).thenReturn("")

        String[] operation = [identification]

        when(validatorMock.isInvalidAmountOfArguments(operation, expectedAmountOfArguments)).thenReturn(false)
        assertThat(commandPrintCash.apply(operation), is(OK_STATUS))
        verify(validatorMock).isInvalidAmountOfArguments(operation, expectedAmountOfArguments)
        verify(cashBankMock).getPrintForm()
    }
}