package com.belorechev.cashmachine.data

import org.junit.Test

class CashTest {

    private final Cash cashForTestingGetters = new Cash("USD", 100, 10)

    @Test
    void shouldWellWork_ForGetCurrency() {

        assert cashForTestingGetters.getCurrency() == "USD"
    }

    @Test
    void shouldWellWork_ForGetValue() {

        assert cashForTestingGetters.getValue() == 100
    }

    @Test
    void shouldWellWork_ForGetAmountOfNotes() {

        assert cashForTestingGetters.getAmountOfNotes() == 10
    }

    @Test
    void shouldReturnTrue_ForEqualMethodForSameObject() {

        assert cashForTestingGetters.equals(cashForTestingGetters)
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectNotCash() {

        assert !cashForTestingGetters.equals(new Object())
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectWithOtherCurrency() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("RUB", 100, 10)

        assert !firstCash.equals(secondCash)
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectWithOtherValue() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 5, 10)

        assert !firstCash.equals(secondCash)
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectWithOtherAmountOfNotes() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 100, 1)

        assert !firstCash.equals(secondCash)
    }

    @Test
    void shouldReturnSameHashCodes_ForEqualsObjects() {

        assert cashForTestingGetters.hashCode() == cashForTestingGetters.hashCode()
    }

    @Test
    void shouldReturnDifferentHashCodes_ForDifferentObjects() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 100, 1)

        assert firstCash.hashCode() != secondCash.hashCode()
    }

    @Test
    void shouldCompareInFirstPlaceByCurrency() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("RUB", 5, 1)

        assert firstCash.compareTo(secondCash) == firstCash.getCurrency().compareTo(secondCash.getCurrency())
    }

    @Test
    void shouldCompareInSecondPlaceByValues() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 5, 1)

        assert firstCash.compareTo(secondCash) == firstCash.getValue().compareTo(secondCash.getValue())
    }

    @Test
    void shouldCompareInThirdPlaceByAmountOfNotes() {

        Cash firstCash = new Cash("USD", 100, 1)
        Cash secondCash = new Cash("USD", 100, 10)

        assert firstCash.compareTo(secondCash) == firstCash.getAmountOfNotes().compareTo(secondCash.getAmountOfNotes())
    }
}
