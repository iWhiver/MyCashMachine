package com.belorechev.cashmachine.computer.commands.processors

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.computer.processors.ValidatorStandardImplementation
import org.junit.BeforeClass
import org.junit.Test

class ValidatorStandardImplementationTest {

    private static Validator validator

    @BeforeClass
    static void setUp() {
        validator = new ValidatorStandardImplementation()
    }

    @Test
    void shouldReturnFalse_IfArrayLengthEqualsExpectedAmountOfArguments() {

        int length = 4
        assert !validator.isInvalidAmountOfArguments(new String[length], length)
    }

    @Test
    void shouldReturnTrue_IfArrayLengthEqualsToExpectedAmountOfArguments() {

        int length = 3
        assert validator.isInvalidAmountOfArguments(new String[length], length + 1)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfArrayIsNull() {

        validator.isInvalidAmountOfArguments(null, 1)
    }

    @Test
    void shouldReturnTrue_ForPositiveValue() {
        assert validator.isPositive(10)
    }

    @Test
    void shouldReturnFalse_ForNegativeValue() {
        assert !validator.isPositive(-10)
    }

    @Test
    void shouldReturnFalse_ForZero() {
        assert !validator.isPositive(0)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_ForNull() {
        validator.isPositive(null)
    }

    @Test
    void shouldReturnTrue_ForThreeUppercaseLettersRussianLanguageCurrency() {
        assert validator.isValidCurrency("РУБ")
    }

    @Test
    void shouldReturnTrue_ForThreeUppercaseLettersEnglishLanguageCurrency() {
        assert validator.isValidCurrency("RUB")
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_ForNullCurrency() {

        validator.isValidCurrency(null)
    }

    @Test
    void shouldReturnFalse_ForDigits() {
        assert !validator.isValidCurrency("101")
    }

    @Test
    void shouldReturnFalse_ForSymbols() {
        assert !validator.isValidCurrency("*/+")
    }

    @Test
    void shouldReturnFalse_ForSymbolAndLetters() {
        assert !validator.isValidCurrency("US+")
    }

    @Test
    void shouldReturnFalse_ForSymbolAndDigits() {
        assert !validator.isValidCurrency("1.0")
    }

    @Test
    void shouldReturnFalse_ForDigitAndLetters() {
        assert !validator.isValidCurrency("US0")
    }

    @Test
    void shouldReturnFalse_ForUppercaseLettersFromRussianAndEnglishLanguages() {
        assert !validator.isValidCurrency("USД")
    }

    @Test
    void shouldReturnFalse_ForTwoUppercaseLettersEnglishLanguageCurrency() {
        assert !validator.isValidCurrency("US")
    }

    @Test
    void shouldReturnFalse_ForTwoUppercaseLettersRussianLanguageCurrency() {
        assert !validator.isValidCurrency("РУ")
    }

    @Test
    void shouldReturnFalse_ForEmptyString() {
        assert !validator.isValidCurrency("")
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfValueIsNull() {
        validator.isValidValue(null)
    }
}
