package com.belorechev.cashmachine.data;

import java.util.Objects;

public class Cash implements Comparable<Cash> {

    private final String currency;
    private final Integer value;
    private final Integer amountOfNotes;

    Cash(String currency, Integer value, Integer amountOfNotes) {
        this.currency = currency;
        this.value = value;
        this.amountOfNotes = amountOfNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cash)) return false;
        Cash that = (Cash) o;
        return currency.equals(that.currency) &&
                value.equals(that.value) &&
                amountOfNotes.equals(that.amountOfNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value, amountOfNotes);
    }

    @Override
    public int compareTo(Cash cash) {

        if (this.currency.equals(cash.currency)) {

            if (this.value.equals(cash.value)) {
                return this.amountOfNotes.compareTo(cash.amountOfNotes);
            }

            return this.value.compareTo(cash.value);
        }

        return this.currency.compareTo(cash.currency);
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getAmountOfNotes() {
        return amountOfNotes;
    }
}
