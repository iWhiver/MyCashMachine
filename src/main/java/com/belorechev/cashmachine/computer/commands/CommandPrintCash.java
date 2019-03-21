package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.computer.processors.Validator;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

public class CommandPrintCash extends CommandTemplate {

    private final CashBank cashBank;
    private final Validator validator;

    public CommandPrintCash(CashBank cashBank, Validator validator) {

        this.cashBank = cashBank;
        this.validator = validator;
        identification = "?";
    }

    @Override
    public String apply(String[] operation) {

        if (validator.isInvalidAmountOfArguments(operation, 1)) {
            return Dictionary.ERROR_STATUS;
        }

        String extraStatus = cashBank.getPrintForm();
        return extraStatus + Dictionary.OK_STATUS;
    }
}
