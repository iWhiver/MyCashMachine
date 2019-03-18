package com.belorechev.cashmachine.computer.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTemplateTest implements CommandTestTemplate {

    private final String identificationString = "ForTest";

    class CommandTemplateForTest extends CommandTemplate {

        @Override
        public String apply(String[] operation) {
            return null;
        }

        CommandTemplateForTest() {
            identification = identificationString;
        }
    }

    private final CommandTemplate commandTemplateForTest = new CommandTemplateForTest();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfOperationIsNull() {

        commandTemplateForTest.isSuited(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfLengthOfOperationIsZero() {

        commandTemplateForTest.isSuited(asArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstValueOfArraysIsNull() {

        commandTemplateForTest.isSuited(new String[]{null});
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIdentificationIsNull() {

        commandTemplateForTest.identification = null;
        commandTemplateForTest.isSuited(asArray(identificationString));
    }

    @Test
    public void shouldReturnTrueIfIdentificationIsSuited() {

        assertTrue(commandTemplateForTest.isSuited(asArray(identificationString)));
    }

    @Test
    public void shouldReturnFalseIfIdentificationNotSuited() {

        assertFalse(commandTemplateForTest.isSuited(asArray("")));
    }


}