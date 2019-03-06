package com.Belorechev.Computer;

import com.Belorechev.Utills.Dictionary;

public class AddNotes implements Command{

    private String apply(String[] operation) throws NumberFormatException{

        if (!isValidCountArguments(operation, 4))
            return Dictionary.ERROR_STATUS;

        String currency = operation[1];
        Integer value = Integer.parseInt(operation[2]);
        Integer number = Integer.parseInt(operation[3]);


        boolean isValid = isValidValue(value) & isValidCurrency(currency) & isPositive(number);

        if (isValid) {
            cashBank.add(currency, value, number);
            return Dictionary.OK_STATUS;
        }

        return Dictionary.ERROR_STATUS;

    }
}
