package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.CashBank
import org.springframework.beans.factory.annotation.Value

class CommandPrintCashService extends CommandTemplate {

    CashBank cashBank
    Validator validator

    @Value('${dictionary.ERROR_STATUS}')
    String ERROR_STATUS

    @Value('${dictionary.OK_STATUS}')
    String OK_STATUS

    CommandPrintCashService(CashBank cashBank, Validator validator) {
        this.cashBank = cashBank
        this.validator = validator
        identification = "?"
    }

    @Override
    String apply(String[] operation) {

        if (validator.isInvalidAmountOfArguments(operation, 1)) {
            return ERROR_STATUS
        }

        return cashBank.getPrintForm() + OK_STATUS
    }
}
