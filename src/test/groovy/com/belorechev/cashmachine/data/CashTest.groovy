package com.belorechev.cashmachine.data

import org.junit.Test

import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class CashTest {

    private final Cash cashForTestingGetters = new Cash("USD", 100, 10)

    @Test
    void shouldWellWork_ForGetCurrency() {
        assertThat(cashForTestingGetters.getCurrency(), is("USD"))
    }

    @Test
    void shouldWellWork_ForGetValue() {
        assertThat(cashForTestingGetters.getValue(), is(100))
    }

    @Test
    void shouldWellWork_ForGetAmountOfNotes() {
        assertThat(cashForTestingGetters.getAmountOfNotes(), is(10))
    }

    @Test
    void shouldReturnTrue_ForEqualMethodForSameObject() {
        assertThat(cashForTestingGetters, is(cashForTestingGetters))
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectNotCash() {
        assertThat(cashForTestingGetters, is(not(new Object())))
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectWithOtherCurrency() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("RUB", 100, 10)

        assertThat(firstCash, is(not(secondCash)))
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectWithOtherValue() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 5, 10)

        assertThat(firstCash, is(not(secondCash)))
    }

    @Test
    void shouldReturnFalse_ForEqualMethodForObjectWithOtherAmountOfNotes() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 100, 1)

        assertThat(firstCash, is(not(secondCash)))
    }

    @Test
    void shouldReturnSameHashCodes_ForEqualsObjects() {
        assertThat(cashForTestingGetters.hashCode(), is(cashForTestingGetters.hashCode()))
    }

    @Test
    void shouldReturnDifferentHashCodes_ForDifferentObjects() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 100, 1)

        assertThat(firstCash.hashCode(), is(not(secondCash.hashCode())))
    }

    @Test
    void shouldCompareInFirstPlaceByCurrency() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("RUB", 5, 1)

        assertThat(firstCash <=> secondCash, is(firstCash.getCurrency() <=> secondCash.getCurrency()))
    }

    @Test
    void shouldCompareInSecondPlaceByValues() {

        Cash firstCash = new Cash("USD", 100, 10)
        Cash secondCash = new Cash("USD", 5, 1)

        assertThat(firstCash <=> secondCash, is(firstCash.getValue() <=> secondCash.getValue()))
    }

    @Test
    void shouldCompareInThirdPlaceByAmountOfNotes() {

        Cash firstCash = new Cash("USD", 100, 1)
        Cash secondCash = new Cash("USD", 100, 10)

        assertThat(firstCash <=> secondCash, is(firstCash.getAmountOfNotes() <=> secondCash.getAmountOfNotes()))
    }
}
