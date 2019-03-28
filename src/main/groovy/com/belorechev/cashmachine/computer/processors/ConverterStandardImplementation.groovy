package com.belorechev.cashmachine.computer.processors

import com.belorechev.cashmachine.data.Cash

class ConverterStandardImplementation implements Converter {

    @Override
    String convertSetOfCashToString(Set<Cash> cashSet, String lineSeparator, boolean useCurrency, boolean useValue, boolean useAmountOfNotes) {

        checkValues(cashSet, lineSeparator, useCurrency, useValue, useAmountOfNotes)
        StringBuilder cashSetAsString = new StringBuilder()

        cashSet.each { cash ->

            List listOfVariables = new ArrayList()

            if (useCurrency) {
                listOfVariables << cash.currency
            }

            if (useValue) {
                listOfVariables << cash.value
            }

            if (useAmountOfNotes) {
                listOfVariables << cash.amountOfNotes
            }

            cashSetAsString << listOfVariables.join(' ') << lineSeparator
        }

        cashSetAsString
    }

    private static void checkValues(Set<Cash> cashSet, String lineSeparator, boolean useCurrency, boolean useValue, boolean useAmountOfNotes) {

        if (!(useCurrency || useValue || useAmountOfNotes)) {
            throw new IllegalArgumentException("You must convert at least one variable of Cash class")
        }

        if (lineSeparator == null) {
            throw new IllegalArgumentException("You must define not null lineSeparator")
        }

        if (cashSet == null) {
            throw new IllegalArgumentException("You must define not null Set of Cash")
        }

        if (cashSet.isEmpty()) {
            throw new IllegalArgumentException("You must define not empty Set of Cash")
        }
    }
}
