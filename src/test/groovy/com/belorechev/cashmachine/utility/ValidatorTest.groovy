package com.belorechev.cashmachine.utility

import org.junit.Test

class ValidatorTest {

    @Test
    void shouldReturnFalse_IfArrayLengthEqualsExpectedAmountOfArguments() {

        int length = 4
        assert !Validator.isInvalidAmountOfArguments(new String[length], length)
    }

    @Test
    void shouldReturnTrue_IfArrayLengthEqualsToExpectedAmountOfArguments() {

        int length = 3
        assert Validator.isInvalidAmountOfArguments(new String[length], length + 1)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfArrayIsNull() {

        Validator.isInvalidAmountOfArguments(null, 1)
    }

    @Test
    void shouldReturnTrue_ForPositiveValue() {
        assert Validator.isPositive(10)
    }

    @Test
    void shouldReturnFalse_ForNegativeValue() {
        assert !Validator.isPositive(-10)
    }

    @Test
    void shouldReturnFalse_ForZero() {
        assert !Validator.isPositive(0)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_ForNull() {
        Validator.isPositive(null)
    }

    @Test
    void shouldReturnTrue_ForThreeUppercaseLettersRussianLanguageCurrency() {
        assert Validator.isValidCurrency("РУБ")
    }

    @Test
    void shouldReturnTrue_ForThreeUppercaseLettersEnglishLanguageCurrency() {
        assert Validator.isValidCurrency("RUB")
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_ForNullCurrency() {

        Validator.isValidCurrency(null)
    }

    @Test
    void shouldReturnFalse_ForDigits() {
        assert !Validator.isValidCurrency("101")
    }

    @Test
    void shouldReturnFalse_ForSymbols() {
        assert !Validator.isValidCurrency("*/+")
    }

    @Test
    void shouldReturnFalse_ForSymbolAndLetters() {
        assert !Validator.isValidCurrency("US+")
    }

    @Test
    void shouldReturnFalse_ForSymbolAndDigits() {
        assert !Validator.isValidCurrency("1.0")
    }

    @Test
    void shouldReturnFalse_ForDigitAndLetters() {
        assert !Validator.isValidCurrency("US0")
    }

    @Test
    void shouldReturnFalse_ForUppercaseLettersFromRussianAndEnglishLanguages() {
        assert !Validator.isValidCurrency("USД")
    }

    @Test
    void shouldReturnFalse_ForTwoUppercaseLettersEnglishLanguageCurrency() {
        assert !Validator.isValidCurrency("US")
    }

    @Test
    void shouldReturnFalse_ForTwoUppercaseLettersRussianLanguageCurrency() {
        assert !Validator.isValidCurrency("РУ")
    }

    @Test
    void shouldReturnFalse_ForEmptyString() {
        assert !Validator.isValidCurrency("")
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfValueIsNull() {
        Validator.isValidValue(null)
    }

    @Test
    void shouldReturnTrue_ForEachValueOfBanknotesFromDictionary() {

        for (validValue in Dictionary.getValidBanknotes()) {
            Validator.isValidValue(validValue)
        }
    }
}
