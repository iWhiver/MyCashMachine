package com.belorechev.computer;

import com.belorechev.utility.Dictionary;

interface Command {

    boolean isSuited (String[] operation);

    String apply (String[] operation);


    default boolean isValidCountArguments(String[] operation, int expectedCount) {

        return operation.length == expectedCount;
    }

    default boolean isValidCurrency(String currency) {

        return currency.equals(currency.toUpperCase()) && currency.length() == 3;
    }

    default boolean isValidValue(Integer value) {

        for (Integer validValue : Dictionary.VALID_BANKNOTES) {
            if (value.equals(validValue)) {
                return true;
            }
        }

        return false;
    }

    default boolean isPositive(Integer number) {

        return number > 0;
    }


}
