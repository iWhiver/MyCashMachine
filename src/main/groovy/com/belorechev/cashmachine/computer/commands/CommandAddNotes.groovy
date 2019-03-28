package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.CashBank

import static com.belorechev.cashmachine.utility.Dictionary.ERROR_STATUS
import static com.belorechev.cashmachine.utility.Dictionary.OK_STATUS

class CommandAddNotes extends CommandTemplate {

    private final CashBank cashBank
    private final Validator validator

    CommandAddNotes(CashBank cashBank, Validator validator) {
        this.cashBank = cashBank
        this.validator = validator
        identification = "+"
    }

    @Override
    String apply(String[] operation) {

        if (validator.isInvalidAmountOfArguments(operation, 4)) {
            return ERROR_STATUS
        }

        String currency = operation[1]

        Integer value
        Integer number

        try {
            value = operation[2] as Integer
            number = operation[3] as Integer
        } catch (NumberFormatException e) {
            println e.stackTrace
            return ERROR_STATUS
        }

        boolean isValid = validator.isValidCurrency(currency) && validator.isValidValue(value) && validator.isPositive(number)

        if (isValid) {
            cashBank.add(currency, value, number)
            return OK_STATUS
        }

        return ERROR_STATUS
    }
}
