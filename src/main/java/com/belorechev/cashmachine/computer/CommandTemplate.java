package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.utility.Dictionary;

abstract class CommandTemplate {

    abstract boolean isSuited(String[] operation);

    abstract String apply(String[] operation);


    boolean isValidCountArguments(String[] operation, int expectedCount) {

        return operation.length == expectedCount;
    }

    boolean isValidCurrency(String currency) {

        return currency.equals(currency.toUpperCase()) && currency.length() == 3;
    }

    boolean isValidValue(Integer value) {

        for (Integer validValue : Dictionary.VALID_BANKNOTES) {
            if (value.equals(validValue)) {
                return true;
            }
        }

        return false;
    }

    boolean isPositive(Integer number) {

        return number > 0;
    }


}
