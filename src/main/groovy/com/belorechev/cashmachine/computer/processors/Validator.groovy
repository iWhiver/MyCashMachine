package com.belorechev.cashmachine.computer.processors

interface Validator {

    boolean isInvalidAmountOfArguments(String[] operation, int expectedAmountOfArguments);

    boolean isValidCurrency(String currency);

    boolean isValidValue(Integer value);

    boolean isPositive(Integer number);
}
