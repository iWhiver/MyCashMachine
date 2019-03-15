package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.computer.Validator;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

import java.util.Optional;

public class CommandGetCash implements CommandTemplate {

    private final CashBank cashBank;

    private final Validator validator;

    public CommandGetCash(CashBank cashBank) {

        this.cashBank = cashBank;
        validator = new Validator();
    }

    @Override
    public boolean isSuited(String[] operation) {

        String identification = "-";

        return operation[0].equals(identification);
    }

    @Override
    public String apply(String[] operation) {

        if (validator.isValidCountArguments(operation, 3)) {
            return Dictionary.ERROR_STATUS;
        }

        String currency = operation[1];
        int amount;

        try {
            amount = Integer.parseInt(operation[2]);
        }
        catch(NumberFormatException e){
            return Dictionary.ERROR_STATUS;
        }

        boolean isValid = validator.isValidCurrency(currency) && validator.isPositive(amount);

        if (isValid) {

            Optional<String> extraStatus = cashBank.get(currency, amount);
            if (extraStatus.isPresent()) {
                return extraStatus.get() + Dictionary.OK_STATUS;
            }
        }

        return Dictionary.ERROR_STATUS;
    }
}

