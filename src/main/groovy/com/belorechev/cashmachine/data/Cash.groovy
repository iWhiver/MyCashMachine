package com.belorechev.cashmachine.data

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Cash implements Comparable<Cash> {

    final String currency
    final Integer value
    final Integer amountOfNotes

    Cash(currency, value, amountOfNotes) {
        this.currency = currency
        this.value = value
        this.amountOfNotes = amountOfNotes
    }

    @Override
    int compareTo(Cash cash) {

        this.currency <=> cash.currency
                ?: this.value <=> cash.value
                ?: this.amountOfNotes <=> cash.amountOfNotes
    }

    String getCurrency() {
        currency
    }

    Integer getValue() {
        value
    }

    Integer getAmountOfNotes() {
        amountOfNotes
    }
}
