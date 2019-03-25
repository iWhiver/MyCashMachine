package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.CashBank
import com.belorechev.cashmachine.utility.Dictionary

class CommandPrintCash extends CommandTemplate {

    private final CashBank cashBank
    private final Validator validator

    CommandPrintCash(CashBank cashBank, Validator validator) {
        this.cashBank = cashBank
        this.validator = validator
        identification = "?"
    }

    @Override
    String apply(String[] operation) {

        if (validator.isInvalidAmountOfArguments(operation, 1)) {
            return Dictionary.ERROR_STATUS
        }

        return cashBank.getPrintForm() + Dictionary.OK_STATUS
    }
}
