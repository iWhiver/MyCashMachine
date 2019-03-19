package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.utility.Validator;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

public class CommandAddNotes extends CommandTemplate {

    private final CashBank cashBank;

    public CommandAddNotes(CashBank cashBank) {

        this.cashBank = cashBank;
        identification = "+";
    }

    @Override
    public String apply(String[] operation) {

        if (Validator.isInvalidAmountOfArguments(operation, 4)) {
            return Dictionary.ERROR_STATUS;
        }

        String currency = operation[1];

        Integer value;
        Integer number;

        try {
            value = Integer.parseInt(operation[2]);
            number = Integer.parseInt(operation[3]);
        } catch (NumberFormatException e) {
            return Dictionary.ERROR_STATUS;
        }

        boolean isValid = Validator.isValidValue(value) && Validator.isValidCurrency(currency) && Validator.isPositive(number);

        if (isValid) {
            cashBank.add(currency, value, number);
            return Dictionary.OK_STATUS;
        }

        return Dictionary.ERROR_STATUS;
    }
}
