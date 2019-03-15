package com.belorechev.cashmachine.utility;

public class Validator {

    public static boolean isValidCountArguments(String[] operation, int expectedCount) {

        return operation.length != expectedCount;
    }

    public static boolean isValidValue(Integer value) {

        for (Integer validValue : Dictionary.getValidBanknotes()) {
            if (value.equals(validValue)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isValidCurrency(String currency) {

        return currency.equals(currency.toUpperCase()) && currency.length() == 3;
    }

    public static boolean isPositive(Integer number) {

        return number > 0;
    }

    private Validator(){
        throw new IllegalStateException("Utility class");

    }
}
