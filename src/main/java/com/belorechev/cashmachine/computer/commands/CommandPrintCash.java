package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.utility.Validator;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

public class CommandPrintCash extends CommandTemplate {

    private final CashBank cashBank;

    public CommandPrintCash(CashBank cashBank) {

        this.cashBank = cashBank;
        identification = "?";
    }

    @Override
    public String apply(String[] operation) {

        if (Validator.isInvalidAmountOfArguments(operation, 1)) {
            return Dictionary.ERROR_STATUS;
        }

        String extraStatus = cashBank.getPrintForm();
        return extraStatus + Dictionary.OK_STATUS;
    }
}
