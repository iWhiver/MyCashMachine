package com.belorechev.cashmachine.utility;

public final class Validator {

    private static final int MAX_AMOUNT_LETTERS_CURRENCY = 3;

    public static boolean isInvalidAmountOfArguments(String[] operation, int expectedAmountOfArguments) {

        if (operation == null) {
            throw new IllegalArgumentException("Array of operation must be not null");
        }

        return operation.length != expectedAmountOfArguments;
    }

    public static boolean isValidValue(Integer value) {

        if (value == null)
            throw new IllegalArgumentException();

        for (Integer validValue : Dictionary.getValidBanknotes()) {
            if (value.equals(validValue)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isValidCurrency(String currency) {

        if (currency == null) {
            throw new IllegalArgumentException();
        }

        if (currency.length() == MAX_AMOUNT_LETTERS_CURRENCY) {

            return isValidRussianCurrency(currency) || isValidEnglishCurrency(currency);
        }

        return false;
    }

    private static boolean isValidRussianCurrency(String currency) {

        for (int i = 0; i < MAX_AMOUNT_LETTERS_CURRENCY; i++) {

            char symbol = currency.charAt(i);
            if (!('А' <= symbol && symbol <= 'Я')) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidEnglishCurrency(String currency) {

        for (int i = 0; i < MAX_AMOUNT_LETTERS_CURRENCY; i++) {

            char symbol = currency.charAt(i);
            if (!('A' <= symbol && symbol <= 'Z')) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPositive(Integer number) {

        if (number == null) {
            throw new IllegalArgumentException();
        }

        return number > 0;
    }

    private Validator() {
        throw new IllegalStateException("Utility class");

    }
}
