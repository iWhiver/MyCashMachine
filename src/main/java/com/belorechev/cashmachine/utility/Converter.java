package com.belorechev.cashmachine.utility;

import com.belorechev.cashmachine.data.Cash;

import java.util.Set;

//TODO add tests

public final class Converter {

    public static String convertSetOfCashToString(Set<Cash> cashSet, String lineSeparator, boolean useCurrency, boolean useValue, boolean useAmountOfNotes) {

        if (!(useCurrency || useValue || useAmountOfNotes))
            throw new IllegalArgumentException("You must convert at least one value of Cash class");

        StringBuilder message = new StringBuilder();

        for (Cash cashForOperation : cashSet) {

            if (useCurrency) {
                message.append(cashForOperation.getCurrency()).append(" ");
            }

            if (useValue) {
                message.append(cashForOperation.getValue()).append(" ");
            }

            if (useAmountOfNotes) {
                message.append(cashForOperation.getAmountOfNotes());
            }

            message.append(lineSeparator);
        }

        return message.toString();
    }

    private Converter() {
        throw new IllegalStateException("Utility class");
    }
}
