package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Converter
import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank
import org.springframework.beans.factory.annotation.Value

class CommandGetCashService extends CommandTemplate {

    CashBank cashBank
    Validator validator
    Converter converter

    @Value('${dictionary.ERROR_STATUS}')
    String ERROR_STATUS

    @Value('${dictionary.NEW_LINE}')
    String NEW_LINE

    @Value('${dictionary.OK_STATUS}')
    String OK_STATUS

    CommandGetCashService(CashBank cashBank, Validator validator) {
        this.cashBank = cashBank
        this.validator = validator
        identification = "-"
    }

    @Override
    String apply(String[] operation) {

        if (validator.isInvalidAmountOfArguments(operation, 3)) {
            return ERROR_STATUS
        }

        int amount

        try {
            amount = operation[2] as Integer
        } catch (NumberFormatException e) {
            println e.stackTrace
            return ERROR_STATUS
        }

        String currency = operation[1]

        boolean isValid = validator.isValidCurrency(currency) && validator.isPositive(amount)

        if (isValid) {
            Set<Cash> usedBanknotesForOperation = cashBank.get(currency, amount)

            if (!usedBanknotesForOperation.isEmpty()) {

                String stringRepresentationOfBanknotes = converter.
                        convertSetOfCashToString(usedBanknotesForOperation, NEW_LINE, false, true, true)

                return stringRepresentationOfBanknotes + OK_STATUS
            }
        }
        return ERROR_STATUS
    }
}

