package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

class CommandAddNotes implements Command{

    private CashBank cashBank;

    private String identification = "+";

    public CommandAddNotes(CashBank cashBank){

        this.cashBank = cashBank;

    }

    @Override
    public boolean isSuited(String[] operation) {

        return (operation[0].equals(identification));
    }

    @Override
    public String apply(String[] operation){

        if (!isValidCountArguments(operation, 4)) {
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


        boolean isValid = isValidValue(value) && isValidCurrency(currency) && isPositive(number);

        if (isValid) {
            cashBank.add(currency, value, number);
            return Dictionary.OK_STATUS;
        }

        return Dictionary.ERROR_STATUS;

    }
}
