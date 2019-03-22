package com.belorechev.cashmachine.computer.processors

import com.belorechev.cashmachine.data.Cash

interface Converter {

    String convertSetOfCashToString(Set<Cash> cashSet, String lineSeparator, boolean useCurrency, boolean useValue, boolean useAmountOfNotes);
}
