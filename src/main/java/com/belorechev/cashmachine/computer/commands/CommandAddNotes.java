package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.computer.Validator;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

public class CommandAddNotes implements CommandTemplate {

    private final CashBank cashBank;

    private final Validator validator;

    public CommandAddNotes(CashBank cashBank) {

        this.cashBank = cashBank;
        validator = new Validator();
    }

    @Override
    public boolean isSuited(String[] operation) {

        String identification = "+";

        return (operation[0].equals(identification));
    }

    @Override
    public String apply(String[] operation){

        if (validator.isValidCountArguments(operation, 4)) {
            return Dictionary.ERROR_STATUS;
        }

        String currency = operation[1];

        Integer value;
        Integer number;

        try {
            value = Integer.parseInt(operation[2]);
            number = Integer.parseInt(operation[3]);
        }
        catch(NumberFormatException e){
            return Dictionary.ERROR_STATUS;
        }

        boolean isValid = validator.isValidValue(value) && validator.isValidCurrency(currency) && validator.isPositive(number);

        if (isValid) {
            cashBank.add(currency, value, number);
            return Dictionary.OK_STATUS;
        }

        return Dictionary.ERROR_STATUS;

    }
}
