package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.computer.processors.Converter
import com.belorechev.cashmachine.computer.processors.ConverterStandardImplementation
import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.Cash
import com.belorechev.cashmachine.data.CashBank

import static com.belorechev.cashmachine.utility.Dictionary.*

class CommandGetCash extends CommandTemplate {

    private final CashBank cashBank
    private final Validator validator
    private final Converter converter

    CommandGetCash(CashBank cashBank, Validator validator) {
        this.cashBank = cashBank
        this.validator = validator
        converter = new ConverterStandardImplementation()
        identification = "-"
    }

    @Override
    String apply(String[] operation) {

        if (validator.isInvalidAmountOfArguments(operation, 3)) {
            return ERROR_STATUS
        }

        int amount

        try {
            amount = Integer.parseInt operation[2]
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

