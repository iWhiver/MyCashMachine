package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.data.CashBankTreeMap;
import com.belorechev.cashmachine.utility.Dictionary;

import java.util.Optional;

class CommandGetCash extends CommandTemplate {

    private CashBank cashBank;

    private String identification = "-";

    public CommandGetCash(CashBank cashBank) {

        this.cashBank = cashBank;

    }

    @Override
    public boolean isSuited(String[] operation) {

        return operation[0].equals(identification);
    }

    @Override
    public String apply(String[] operation) {

        if (!isValidCountArguments(operation, 3)) {
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

        boolean isValid = isValidCurrency(currency) && isPositive(amount);

        if (isValid) {

            Optional<String> extraStatus = cashBank.get(currency, amount);
            if (extraStatus.isPresent()) {
                return extraStatus.get() + Dictionary.OK_STATUS;
            }
        }

        return Dictionary.ERROR_STATUS;
    }
}

