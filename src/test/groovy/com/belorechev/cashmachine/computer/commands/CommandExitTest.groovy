package com.belorechev.cashmachine.computer.commands

import org.junit.Test

import static com.belorechev.cashmachine.utility.Dictionary.EXIT_STATUS

class CommandExitTest {

    private final CommandTemplate commandExit = new CommandExit()
    private final String identification = "exit"

    @Test
    void shouldReturnTrue_ForUpperCaseIdentificationOfClass() {

        assert commandExit.isSuited(identification.toUpperCase())
    }

    @Test
    void shouldReturnTrue_ForLowerCaseIdentificationOfClass() {

        assert commandExit.isSuited(identification)
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandExit.identification = null
        assert commandExit.isSuited(identification)
    }

    @Test
    void shouldReturnExitStatus_ForUpperCaseIdentification() {

        assert EXIT_STATUS == commandExit.apply(identification.toUpperCase())
    }

    @Test
    void shouldReturnExitStatus_ForLowerCaseIdentification() {

        assert EXIT_STATUS == commandExit.apply(identification)
    }
}