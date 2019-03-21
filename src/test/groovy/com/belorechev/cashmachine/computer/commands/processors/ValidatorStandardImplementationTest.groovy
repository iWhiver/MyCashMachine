package com.belorechev.cashmachine.computer.commands.processors

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.computer.processors.ValidatorStandardImplementation
import org.junit.BeforeClass
import org.junit.Test

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

class ValidatorStandardImplementationTest {

    private static Validator validator

    @BeforeClass
    static void setUp() {
        validator = new ValidatorStandardImplementation()
    }

    @Test
    void shouldReturnFalse_IfArrayLengthEqualsExpectedAmountOfArguments() {

        int length = 4
        assertThat(validator.isInvalidAmountOfArguments(new String[length], length), is(false))
    }

    @Test
    void shouldReturnTrue_IfArrayLengthEqualsToExpectedAmountOfArguments() {

        int length = 3
        assertThat(validator.isInvalidAmountOfArguments(new String[length], length + 1), is(true))
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfArrayIsNull() {
        validator.isInvalidAmountOfArguments(null, 1)
    }

    @Test
    void shouldReturnTrue_ForPositiveValue() {
        assertThat(validator.isPositive(10), is(true))
    }

    @Test
    void shouldReturnFalse_ForNegativeValue() {
        assertThat(validator.isPositive(-10), is(false))
    }

    @Test
    void shouldReturnFalse_ForZero() {
        assertThat(validator.isPositive(0), is(false))
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_ForNull() {
        validator.isPositive(null)
    }

    @Test
    void shouldReturnTrue_ForThreeUppercaseLettersRussianLanguageCurrency() {
        assertThat(validator.isValidCurrency("РУБ"), is(true))
    }

    @Test
    void shouldReturnTrue_ForThreeUppercaseLettersEnglishLanguageCurrency() {
        assertThat(validator.isValidCurrency("RUB"), is(true))
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_ForNullCurrency() {
        validator.isValidCurrency(null)
    }

    @Test
    void shouldReturnFalse_ForDigits() {
        assertThat(validator.isValidCurrency("101"), is(false))
    }

    @Test
    void shouldReturnFalse_ForSymbols() {
        assertThat(validator.isValidCurrency("*/+"), is(false))
    }

    @Test
    void shouldReturnFalse_ForSymbolAndLetters() {
        assertThat(validator.isValidCurrency("US+"), is(false))
    }

    @Test
    void shouldReturnFalse_ForSymbolAndDigits() {
        assertThat(validator.isValidCurrency("1.0+"), is(false))
    }

    @Test
    void shouldReturnFalse_ForDigitAndLetters() {
        assertThat(validator.isValidCurrency("US0"), is(false))
    }

    @Test
    void shouldReturnFalse_ForUppercaseLettersFromRussianAndEnglishLanguages() {
        assertThat(validator.isValidCurrency("USД"), is(false))
    }

    @Test
    void shouldReturnFalse_ForTwoUppercaseLettersEnglishLanguageCurrency() {
        assertThat(validator.isValidCurrency("US"), is(false))
    }

    @Test
    void shouldReturnFalse_ForTwoUppercaseLettersRussianLanguageCurrency() {
        assertThat(validator.isValidCurrency("РУ"), is(false))
    }

    @Test
    void shouldReturnFalse_ForEmptyString() {
        assertThat(validator.isValidCurrency(""), is(false))
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfValueIsNull() {
        validator.isValidValue(null)
    }
}
