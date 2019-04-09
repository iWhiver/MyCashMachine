package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.CashBank
import grails.validation.ValidationException
import org.springframework.beans.factory.annotation.Value

class CommandAddNotesService extends CommandTemplate {

    CashBank cashBank
    Validator validator

    @Value('${dictionary.ERROR_STATUS}')
    String ERROR_STATUS

    @Value('${dictionary.OK_STATUS}')
    String OK_STATUS

    CommandAddNotesService(CashBank cashBank, Validator validator) {
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
            try {
                cashBank.add(currency, value, number)
            }
            catch (ValidationException e) {
                e.printStackTrace()
                return ERROR_STATUS
            }

            return OK_STATUS
        }
        return ERROR_STATUS
    }
}
