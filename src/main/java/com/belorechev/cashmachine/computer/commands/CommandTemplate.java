package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.utility.Dictionary;

public interface CommandTemplate {

    boolean isSuited(String[] operation);

   String apply(String[] operation);


    default boolean isValidCountArguments(String[] operation, int expectedCount) {

        return operation.length != expectedCount;
    }

    default boolean isValidCurrency(String currency) {

        return currency.equals(currency.toUpperCase()) && currency.length() == 3;
    }

    default boolean isValidValue(Integer value) {

        for (Integer validValue : Dictionary.getValidBanknotes()) {
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
