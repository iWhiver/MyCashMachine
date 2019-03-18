package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.utility.Validator;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

import java.util.Optional;

public class CommandGetCash extends CommandTemplate {

    private final CashBank cashBank;

    public CommandGetCash(CashBank cashBank) {

        this.cashBank = cashBank;
        identification = "-";
    }

    @Override
    public String apply(String[] operation) {

        if (Validator.isInvalidAmountOfArguments(operation, 3)) {
            return Dictionary.ERROR_STATUS;
        }

        int amount;

        try {
            amount = Integer.parseInt(operation[2]);
        } catch (NumberFormatException e) {
            return Dictionary.ERROR_STATUS;
        }

        String currency = operation[1];

        boolean isValid = Validator.isValidCurrency(currency) && Validator.isPositive(amount);

        if (isValid) {
            Optional<String> usedBanknotesForOperation = cashBank.get(currency, amount);

            if (usedBanknotesForOperation.isPresent()) {
                return usedBanknotesForOperation.get() + Dictionary.OK_STATUS;
            }
        }
        return Dictionary.ERROR_STATUS;
    }
}

