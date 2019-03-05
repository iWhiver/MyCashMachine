package com.Belorechev.Computer;

import com.Belorechev.Utills.Dictionary;

import java.util.Optional;

public class Computer {

    private CashBank cashBank;

    public Computer() {

        cashBank = new CashBank();
    }

    public String calculate(String command) {

        if (command == null)
            return Dictionary.ERROR_STATUS;

        String[] operation = command.split(" ");
        String status = null;

        switch (operation[0]) {
            case "+":
                status = addNotes(operation);
                break;

            case "-":
                status = getCash(operation);
                break;

            case "?":
                status = printCash(operation);
                break;

            case "exit":
                status = Dictionary.EXIT_STATUS;
                break;

            default:
                status = Dictionary.ERROR_STATUS;
        }

        return status;
    }

    private String addNotes(String[] operation) {

        if (!isValidCountArguments(operation, 4))
            return Dictionary.ERROR_STATUS;

        String currency = operation[1];
        Integer value = Integer.parseInt(operation[2]);
        Integer number = Integer.parseInt(operation[3]);


        boolean isValid = isValidValue(value) & isValidCurrency(currency) & isPositive(number);

        if (isValid) {
            cashBank.add(currency, value, number);
            return Dictionary.OK_STATUS;
        }

        return Dictionary.ERROR_STATUS;

    }

    private String getCash(String[] operation) {

        if (!isValidCountArguments(operation, 3))
            return Dictionary.ERROR_STATUS;

        String currency = operation[1];
        Integer amount = Integer.parseInt(operation[2]);

        boolean isValid = isValidCurrency(currency) & isPositive(amount);

        if (isValid) {

            Optional<String> extraStatus = cashBank.get(currency, amount);
            if (extraStatus.isPresent())
                return extraStatus.get() + Dictionary.OK_STATUS;
        }

        return Dictionary.ERROR_STATUS;
    }

    private String printCash(String[] operation) {

        if (!isValidCountArguments(operation, 1))
            return Dictionary.ERROR_STATUS;

        String extraStatus = cashBank.toString();
        return extraStatus + Dictionary.OK_STATUS;
    }

    private boolean isValidCountArguments(String[] operation, int expectedCount) {

        return operation.length == expectedCount;
    }

    private boolean isValidCurrency(String currency) {

        return currency.equals(currency.toUpperCase()) && currency.length() == 3;
    }

    private boolean isValidValue(Integer value) {

        for (Integer validValue : Dictionary.VALID_BANKNOTES) {
            if (value.equals(validValue))
                return true;
        }

        return false;
    }


    private boolean isPositive(Integer number) {
        return number > 0;
    }

}
