package com.belorechev.computer;

import com.belorechev.data.CashBank;
import com.belorechev.utility.Dictionary;

class CommandPrintCash implements Command {

    private CashBank cashBank;

    private String identification = "?";

    public CommandPrintCash(CashBank cashBank) {

        this.cashBank = cashBank;
    }

    @Override
    public boolean isSuited(String[] operation) {

        return operation[0].equals(identification);
    }

    @Override
    public String apply(String[] operation) {

        if (!isValidCountArguments(operation, 1)) {
            return Dictionary.ERROR_STATUS;
        }

        String extraStatus = cashBank.toString();
        return extraStatus + Dictionary.OK_STATUS;
    }
}
