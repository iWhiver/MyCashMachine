package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.data.Cash;
import com.belorechev.cashmachine.utility.Converter;
import com.belorechev.cashmachine.utility.Validator;
import com.belorechev.cashmachine.data.CashBank;

import static com.belorechev.cashmachine.utility.Dictionary.*;

import java.util.Optional;
import java.util.Set;

public class CommandGetCash extends CommandTemplate {

    private final CashBank cashBank;

    public CommandGetCash(CashBank cashBank) {

        this.cashBank = cashBank;
        identification = "-";
    }

    @Override
    public String apply(String[] operation) {

        if (Validator.isInvalidAmountOfArguments(operation, 3)) {
            return ERROR_STATUS;
        }

        int amount;

        try {
            amount = Integer.parseInt(operation[2]);
        } catch (NumberFormatException e) {
            return ERROR_STATUS;
        }

        String currency = operation[1];

        boolean isValid = Validator.isValidCurrency(currency) && Validator.isPositive(amount);

        if (isValid) {
            Optional<Set<Cash>> usedBanknotesForOperation = cashBank.get(currency, amount);

            if (usedBanknotesForOperation.isPresent()) {

                String stringRepresentationOfBanknotes = Converter.
                        convertSetOfCashToString(usedBanknotesForOperation.get(), NEW_LINE, false, true, true);

                return stringRepresentationOfBanknotes + OK_STATUS;
            }
        }
        return ERROR_STATUS;
    }
}

