package com.belorechev.cashmachine.computer.commands

import org.junit.Test

import static com.belorechev.cashmachine.utility.Dictionary.EXIT_STATUS
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

class CommandExitTest {

    private final CommandTemplate commandExit = new CommandExit()
    private final String identification = "exit"

    @Test
    void shouldReturnTrue_ForUpperCaseIdentificationOfClass() {
        assertThat(commandExit.isSuited(identification.toUpperCase()), is(true))
    }

    @Test
    void shouldReturnTrue_ForLowerCaseIdentificationOfClass() {
        assertThat(commandExit.isSuited(identification), is(true))
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandExit.identification = null
        commandExit.isSuited(identification)
    }

    @Test
    void shouldReturnExitStatus_ForUpperCaseIdentification() {
        assertThat(commandExit.apply(identification.toUpperCase()), is(EXIT_STATUS))
    }

    @Test
    void shouldReturnExitStatus_ForLowerCaseIdentification() {
        assertThat(commandExit.apply(identification), is(EXIT_STATUS))
    }
}