package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.data.CashBank
import com.belorechev.cashmachine.utility.Dictionary
import org.junit.Test
import org.mockito.Mockito

import static com.belorechev.cashmachine.utility.Dictionary.ERROR_STATUS
import static com.belorechev.cashmachine.utility.Dictionary.OK_STATUS

class CommandAddNotesTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class)
    private final CommandTemplate commandAddNotes = new CommandAddNotes(cashBankMock)

    private final String identification = "+"

    @Test
    void shouldReturnTrue_ForIdentificationOfClass() {

        assert commandAddNotes.isSuited(identification)
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandAddNotes.identification = null
        assert commandAddNotes.isSuited(identification)
    }

    @Test
    void shouldReturnOkStatus_ForSuitedCommand() {

        assert OK_STATUS == commandAddNotes.apply(identification, "USD", "10", "10")
    }

    @Test
    void shouldReturnOkStatus_ForEachCommandFromDictionary() {

        for (banknote in Dictionary.getValidBanknotes()) {
            String command = "+ USD $banknote 50"
            assert OK_STATUS == commandAddNotes.apply(command.split())
        }
    }

    @Test
    void shouldReturnErrorStatus_ForNotSuitedApplyCommand_ByLength() {

        assert ERROR_STATUS == commandAddNotes.apply(identification)
    }

    @Test
    void shouldReturnErrorStatus_ForNotValidCurrency_ByCase() {

        assert ERROR_STATUS == commandAddNotes.apply(identification, "usd", "100", "10")
    }

    @Test
    void shouldReturnErrorStatus_ForThirdNotDigitCommand() {

        assert ERROR_STATUS == commandAddNotes.apply(identification, "USD", "USD", "10")
    }

    @Test
    void shouldReturnErrorStatus_ForFourthNotDigitCommand() {

        assert ERROR_STATUS == commandAddNotes.apply(identification, "USD", "100", "USD")
    }
}