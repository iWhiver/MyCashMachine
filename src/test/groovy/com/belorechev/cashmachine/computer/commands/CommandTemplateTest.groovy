package com.belorechev.cashmachine.computer.commands

import org.junit.Test

class CommandTemplateTest {

    private final String identificationString = "ForTest"

    class CommandTemplateForTest extends CommandTemplate {

        @Override
        String apply(String[] operation) {
            return null
        }

        CommandTemplateForTest() {
            identification = identificationString
        }
    }

    private final CommandTemplate commandTemplateForTest = new CommandTemplateForTest()

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfOperationIsNull() {

        commandTemplateForTest.isSuited(null)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfLengthOfOperationIsZero() {

        commandTemplateForTest.isSuited(new String[0])
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfFirstValueOfArraysIsNull() {

        commandTemplateForTest.isSuited([null] as String[])
    }

    @Test(expected = IllegalStateException.class)
    void shouldThrowException_IfIdentificationIsNull() {

        commandTemplateForTest.identification = null
        commandTemplateForTest.isSuited(identificationString)
    }

    @Test
    void shouldReturnTrue_IfIdentificationIsSuited() {

        assert commandTemplateForTest.isSuited(identificationString)
    }

    @Test
    void shouldReturnFalse_IfIdentificationNotSuited() {

        assert !commandTemplateForTest.isSuited("")
    }


}