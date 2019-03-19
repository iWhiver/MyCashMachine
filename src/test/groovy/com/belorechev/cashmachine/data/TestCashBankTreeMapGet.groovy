package com.belorechev.cashmachine.data

import org.junit.Test

import static junit.framework.TestCase.assertEquals

class TestCashBankTreeMapGet extends TestBaseForCashBankTreeMap {

    @Test
    void getLastValue_EmptyCurrency_EmptyBank() {
        cashBank.add("USD", 1, 1)
        cashBank.get("USD", 1)

        assert cashBank.getBank().isEmpty()
    }

    @Test
    void getLastValue_NotEmptyBank_EmptyCurrency() {
        cashBank.add("USD", 1, 1)
        cashBank.add("RUB", 1, 1)
        cashBank.get("USD", 1)

        putInExpectedBankNewCurrency("RUB", 1, 1)

        assertEquals(expectedBank, cashBank.getBank())
    }

    @Test
    void getLastValue_NotEmptyCurrency() {
        cashBank.add("USD", 1, 1)
        cashBank.add("USD", 10, 1)
        cashBank.get("USD", 1)

        putInExpectedBankNewCurrency("USD", 10, 1)

        assertEquals(expectedBank, cashBank.getBank())
    }

    @Test
    void getLastValue_AnotherValueNotLast1() {
        cashBank.add("USD", 100, 1)
        cashBank.add("USD", 10, 1)
        cashBank.get("USD", 109)

        putInExpectedBankNewCurrency("USD", 100, 1)
        putInExpectedBankExistingCurrency(10, 1)

        assertEquals(expectedBank, cashBank.getBank())
    }


    @Test
    void getLastValue_AnotherValueNotLast2() {
        cashBank.add("USD", 100, 1)
        cashBank.add("USD", 10, 10)
        cashBank.get("USD", 190)

        putInExpectedBankNewCurrency("USD", 10, 1)

        assertEquals(expectedBank, cashBank.getBank())
    }

    @Test
    void getLastValue_AnotherValueNotLast3() {
        cashBank.add("USD", 100, 10)
        cashBank.add("USD", 10, 1)
        cashBank.get("USD", 110)

        putInExpectedBankNewCurrency("USD", 100, 9)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getLastValues_EmptyCurrency_EmptyBank1() {
        cashBank.add("USD", 1, 1)
        cashBank.add("USD", 5, 1)
        cashBank.add("USD", 10, 1)
        cashBank.add("USD", 50, 1)
        cashBank.get("USD", 1 + 5 + 10 + 50)

        assert cashBank.getBank().isEmpty()
    }

    @Test
    void getLastValues_EmptyCurrency_EmptyBank2() {
        cashBank.add("USD", 100, 5)
        cashBank.add("USD", 10, 5)
        cashBank.add("USD", 1, 5)
        cashBank.get("USD", 100 * 5 + 10 * 5 + 5)

        assert cashBank.getBank().isEmpty()
    }

    @Test
    void getLastValues_EmptyCurrency_NotEmptyBank1() {

        cashBank.add("USD", 1, 1)
        cashBank.add("USD", 5, 1)
        cashBank.add("USD", 10, 1)
        cashBank.add("USD", 50, 1)
        cashBank.add("RUB", 1, 1)
        cashBank.get("USD", 1 + 5 + 10 + 50)
        putInExpectedBankNewCurrency("RUB", 1, 1)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getLastValues_NotEmptyCurrency_NotEmptyBank1() {
        cashBank.add("USD", 100, 30)
        cashBank.add("USD", 10, 50)
        cashBank.add("RUB", 10, 10)
        cashBank.get("USD", 100 + 10 * 2)

        putInExpectedBankNewCurrency("USD", 100, 29)
        putInExpectedBankExistingCurrency(10, 48)
        putInExpectedBankNewCurrency("RUB", 10, 10)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getEachValues_NotEmptyCurrency() {
        cashBank.add("USD", 1, 2)
        cashBank.add("USD", 5, 2)
        cashBank.add("USD", 10, 2)
        cashBank.add("USD", 50, 2)
        cashBank.get("USD", 1 + 5 + 10 + 50)

        putInExpectedBankNewCurrency("USD", 1, 1)
        putInExpectedBankExistingCurrency(5, 1)
        putInExpectedBankExistingCurrency(10, 1)
        putInExpectedBankExistingCurrency(50, 1)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getEmptyValue_NeedMoreBanknotes() {
        cashBank.add("USD", 1, 1)
        cashBank.get("USD", 2)

        putInExpectedBankNewCurrency("USD", 1, 1)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getEmptyValue_NeedMoreBanknotes2() {
        cashBank.add("USD", 100, 5)
        cashBank.add("USD", 10, 5)
        cashBank.add("USD", 1, 5)
        cashBank.get("USD", 100 * 5 + 10 * 5 + 5 + 1)

        putInExpectedBankNewCurrency("USD", 100, 5)
        putInExpectedBankExistingCurrency(10, 5)
        putInExpectedBankExistingCurrency(1, 5)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getEmptyValue_NeedAnotherValues1() {
        cashBank.add("USD", 50, 1)
        cashBank.get("USD", 50 + 1)

        putInExpectedBankNewCurrency("USD", 50, 1)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getEmptyValue_NeedAnotherValues2() {
        cashBank.add("USD", 100, 5)
        cashBank.add("USD", 10, 5)
        cashBank.get("USD", 100 * 5 + 10 * 5 + 1)

        putInExpectedBankNewCurrency("USD", 100, 5)
        putInExpectedBankExistingCurrency(10, 5)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void getAmount_ThereAreOpportunities() {
        cashBank.add("USD", 100, 1)
        cashBank.add("USD", 10, 10)
        cashBank.get("USD", 100)

        putInExpectedBankNewCurrency("USD", 10, 10)

        assert expectedBank == cashBank.getBank()

    }
}
