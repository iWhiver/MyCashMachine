package com.belorechev.cashmachine.computer.processors

import com.belorechev.cashmachine.utility.Dictionary

final class ValidatorStandardImplementation implements Validator {

    private static final int MAX_AMOUNT_LETTERS_CURRENCY = 3

    @Override
    boolean isInvalidAmountOfArguments(String[] operation, int expectedAmountOfArguments) {

        if (operation == null) {
            throw new IllegalArgumentException("Array of operation must be not null")
        }

        return operation.size() != expectedAmountOfArguments
    }

    @Override
    boolean isValidCurrency(String currency) {

        if (currency == null) {
            throw new IllegalArgumentException()
        }

        if (currency.size() == MAX_AMOUNT_LETTERS_CURRENCY) {
            return isValidRussianCurrency(currency) || isValidEnglishCurrency(currency)
        }
        return false
    }

    private static boolean isValidRussianCurrency(String currency) {

        for (int i in 0..<MAX_AMOUNT_LETTERS_CURRENCY) {
            def range = 'А'..'Я'
            if (!(range.contains(currency[i]))) {
                return false
            }
        }
        return true
    }

    private static boolean isValidEnglishCurrency(String currency) {

        for (int i in 0..<MAX_AMOUNT_LETTERS_CURRENCY) {
            def range = 'A'..'Z'
            if (!(range.contains(currency[i]))) {
                return false
            }
        }
        return true
    }

    @Override
    boolean isValidValue(Integer value) {

        if (value == null) {
            throw new IllegalArgumentException()
        }

        for (Integer validValue in Dictionary.getValidBanknotes()) {
            if (value == validValue) {
                return true
            }
        }
        return false
    }

    @Override
    boolean isPositive(Integer number) {

        if (number == null) {
            throw new IllegalArgumentException()
        }
        return number > 0
    }
}
