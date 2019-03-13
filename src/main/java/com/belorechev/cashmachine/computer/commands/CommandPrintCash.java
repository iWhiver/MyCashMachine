package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

public class CommandPrintCash implements CommandTemplate {

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

        String extraStatus = cashBank.getPrintForm();
        return extraStatus + Dictionary.OK_STATUS;
    }
}