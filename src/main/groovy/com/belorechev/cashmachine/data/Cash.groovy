package com.belorechev.cashmachine.data

class Cash implements Comparable<Cash> {

    private final String currency
    private final Integer value
    private final Integer amountOfNotes

    Cash(String currency, Integer value, Integer amountOfNotes) {
        this.currency = currency
        this.value = value
        this.amountOfNotes = amountOfNotes
    }

    @Override
    boolean equals(Object o) {
        if (this == o) {
            return true
        }

        if (!(o instanceof Cash)) {
            return false
        }

        Cash that = (Cash) o

        return currency == that.currency &&
                value == that.value &&
                amountOfNotes == that.amountOfNotes
    }

    @Override
    int hashCode() {
        return Objects.hash(currency, value, amountOfNotes)
    }

    @Override
    int compareTo(Cash cash) {

        if (this.currency == cash.currency) {

            if (this.value == cash.value) {
                return this.amountOfNotes <=> cash.amountOfNotes
            }

            return this.value <=> cash.value
        }

        return this.currency <=> cash.currency
    }

    String getCurrency() {
        return currency
    }

    Integer getValue() {
        return value
    }

    Integer getAmountOfNotes() {
        return amountOfNotes
    }
}
