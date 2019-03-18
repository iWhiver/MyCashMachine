package com.belorechev.cashmachine.computer.commands;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.belorechev.cashmachine.utility.Dictionary.*;

public class CommandExitTest implements CommandTestTemplate {

    private final CommandTemplate commandExit = new CommandExit();
    private final String identification = "exit";

    @Test
    public void shouldReturnTrue_ForUpperCaseIdentificationOfClass() {

        assertTrue(commandExit.isSuited(asArray(identification.toUpperCase())));
    }

    @Test
    public void shouldReturnTrue_ForLowerCaseIdentificationOfClass() {

        assertTrue(commandExit.isSuited(asArray(identification)));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_IfClassNotChangeValueOfIdentification() {

        commandExit.identification = null;
        assertTrue(commandExit.isSuited(asArray(identification)));
    }

    @Test
    public void shouldReturnExitStatus_ForUpperCaseIdentification() {

        assertEquals(EXIT_STATUS, commandExit.apply(asArray(identification.toUpperCase())));
    }

    @Test
    public void shouldReturnExitStatus_ForLowerCaseIdentification() {

        assertEquals(EXIT_STATUS, commandExit.apply(asArray(identification)));
    }
}