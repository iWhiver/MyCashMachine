package com.belorechev.cashmachine.computer.processors

import org.springframework.beans.factory.annotation.Value

class ValidatorStandardImplementation implements Validator {

    private static final int MAX_AMOUNT_LETTERS_CURRENCY = 3

    @Value('${dictionary.VALID_BANKNOTES}')
    String[] VALID_BANKNOTES

    @Value('${dictionary.NEW_LINE}')
    String NEW_LINE

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

        return VALID_BANKNOTES.any() { validValue ->
            value == validValue as Integer
        }
    }

    @Override
    boolean isPositive(Integer number) {

        if (number == null) {
            throw new IllegalArgumentException()
        }
        return number > 0
    }
}
