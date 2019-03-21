package com.belorechev.cashmachine.computer.processors;

import com.belorechev.cashmachine.data.Cash;

import java.util.Set;

//TODO Change to interface

public final class Converter {

    public static String convertSetOfCashToString(Set<Cash> cashSet, String lineSeparator, boolean useCurrency, boolean useValue, boolean useAmountOfNotes) {

        checkValues(cashSet, lineSeparator, useCurrency, useValue, useAmountOfNotes);

        StringBuilder cashSetAsString = new StringBuilder();

        for (Cash cashForOperation : cashSet) {

            boolean addedVariableEarlier = false;

            if (useCurrency) {

                cashSetAsString.append(cashForOperation.getCurrency());
                addedVariableEarlier = true;
            }

            if (useValue) {

                if (addedVariableEarlier) {
                    cashSetAsString.append(" ");
                }

                cashSetAsString.append(cashForOperation.getValue());
                addedVariableEarlier = true;
            }

            if (useAmountOfNotes) {

                if (addedVariableEarlier) {
                    cashSetAsString.append(" ");
                }

                cashSetAsString.append(cashForOperation.getAmountOfNotes());
            }

            cashSetAsString.append(lineSeparator);
        }

        return cashSetAsString.toString();
    }

    private static void checkValues(Set<Cash> cashSet, String lineSeparator, boolean useCurrency, boolean useValue, boolean useAmountOfNotes) {

        if (!(useCurrency || useValue || useAmountOfNotes))
            throw new IllegalArgumentException("You must convert at least one variable of Cash class");

        if (lineSeparator == null)
            throw new IllegalArgumentException("You must define not null lineSeparator");

        if (cashSet == null)
            throw new IllegalArgumentException("You must define not null Set of Cash");

        if (cashSet.isEmpty())
            throw new IllegalArgumentException("You must define not empty Set of Cash");
    }

    private Converter() {
        throw new IllegalStateException("Utility class");
    }
}
