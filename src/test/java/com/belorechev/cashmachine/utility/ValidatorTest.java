package com.belorechev.cashmachine.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void shouldReturnFalse_IfArrayLengthEqualsExpectedAmountOfArguments() {
        int length = 4;
        assertFalse(Validator.isInvalidAmountOfArguments(new String[length], length));
    }

    @Test
    public void shouldReturnTrue_IfArrayLengthEqualsToExpectedAmountOfArguments() {
        int length = 3;
        assertTrue(Validator.isInvalidAmountOfArguments(new String[length], length + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_IfArrayIsNull() {
        Validator.isInvalidAmountOfArguments(null, 1);
    }

    private final Integer number = 20;

    @Test
    public void shouldReturnTrue_ForPositiveValue() {
        assertTrue(Validator.isPositive(number));
    }

    @Test
    public void shouldReturnFalse_ForNegativeValue() {
        assertFalse(Validator.isPositive(-number));
    }

    @Test
    public void shouldReturnFalse_ForZero() {
        assertFalse(Validator.isPositive(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_ForNull() {
        Validator.isPositive(null);
    }

    @Test
    public void shouldReturnTrue_ForThreeUppercaseLettersRussianLanguageCurrency() {
        assertTrue(Validator.isValidCurrency("РУБ"));
    }

    @Test
    public void shouldReturnTrue_ForThreeUppercaseLettersEnglishLanguageCurrency() {
        assertTrue(Validator.isValidCurrency("RUB"));
    }

    @Test
    public void shouldReturnFalse_ForDigits() {
        assertFalse(Validator.isValidCurrency("101"));
    }

    @Test
    public void shouldReturnFalse_ForSymbols() {
        assertFalse(Validator.isValidCurrency("*/+"));
    }

    @Test
    public void shouldReturnFalse_ForSymbolAndLetters() {
        assertFalse(Validator.isValidCurrency("US+"));
    }

    @Test
    public void shouldReturnFalse_ForSymbolAndDigits() {
        assertFalse(Validator.isValidCurrency("1.0"));
    }

    @Test
    public void shouldReturnFalse_ForDigitAndLetters() {
        assertFalse(Validator.isValidCurrency("US0"));
    }

    @Test
    public void shouldReturnFalse_ForUppercaseLettersFromRussianAndEnglishLanguages() {
        assertFalse(Validator.isValidCurrency("USД"));
    }

    @Test
    public void shouldReturnFalse_ForTwoUppercaseLettersEnglishLanguageCurrency() {
        assertFalse(Validator.isValidCurrency("US"));
    }

    @Test
    public void shouldReturnFalse_ForTwoUppercaseLettersRussianLanguageCurrency() {
        assertFalse(Validator.isValidCurrency("РУ"));
    }

    @Test
    public void shouldReturnFalse_ForEmptyString() {
        assertFalse(Validator.isValidCurrency(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_IfValueIsNull() {
        Validator.isValidValue(null);
    }

    @Test
    public void shouldReturnTrue_ForEachValueOfBanknotesFromDictionary() {
        for (Integer validValue : Dictionary.getValidBanknotes()) {
            assertTrue(Validator.isValidValue(validValue));
        }
    }
}
