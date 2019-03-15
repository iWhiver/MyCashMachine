package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.utility.Dictionary;

public class Validator {

    public boolean isValidCountArguments(String[] operation, int expectedCount) {

        return operation.length != expectedCount;
    }

    public boolean isValidValue(Integer value) {

        for (Integer validValue : Dictionary.getValidBanknotes()) {
            if (value.equals(validValue)) {
                return true;
            }
        }

        return false;
    }

    public boolean isValidCurrency(String currency) {

        return currency.equals(currency.toUpperCase()) && currency.length() == 3;
    }

    public boolean isPositive(Integer number) {

        return number > 0;
    }
}
