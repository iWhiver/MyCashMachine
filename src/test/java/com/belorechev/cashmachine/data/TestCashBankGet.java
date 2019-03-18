package com.belorechev.cashmachine.data;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCashBankGet extends TestBaseForCashBank {

    @Test
    public void getLastValue_EmptyCurrency_EmptyBank() {
        cashBank.add("USD", 1, 1);
        cashBank.get("USD", 1);
        actualBank = cashBank.getBank();

        assertEmpty(actualBank);
    }

    @Test
    public void getLastValue_NotEmptyBank_EmptyCurrency() {
        cashBank.add("USD", 1, 1);
        cashBank.add("RUB", 1, 1);
        cashBank.get("USD", 1);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("RUB", 1, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValue_NotEmptyCurrency() {
        cashBank.add("USD", 1, 1);
        cashBank.add("USD", 10, 1);
        cashBank.get("USD", 1);

        putInExpectedBankNewCurrency("USD", 10, 1);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }


    @Test
    public void getLastValue_AnotherValueNotLast1() {
        cashBank.add("USD", 100, 1);
        cashBank.add("USD", 10, 1);
        cashBank.get("USD", 109);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 1);
        putInExpectedBankExistingCurrency(10, 1);

        assertEquals(expectedBank, actualBank);
    }


    @Test
    public void getLastValue_AnotherValueNotLast2() {
        cashBank.add("USD", 100, 1);
        cashBank.add("USD", 10, 10);
        cashBank.get("USD", 190);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 10, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValue_AnotherValueNotLast3() {
        cashBank.add("USD", 100, 10);
        cashBank.add("USD", 10, 1);
        cashBank.get("USD", 110);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 9);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValues_EmptyCurrency_EmptyBank1() {
        cashBank.add("USD", 1, 1);
        cashBank.add("USD", 5, 1);
        cashBank.add("USD", 10, 1);
        cashBank.add("USD", 50, 1);
        cashBank.get("USD", 1 + 5 + 10 + 50);
        actualBank = cashBank.getBank();

        assertEmpty(actualBank);
    }

    @Test
    public void getLastValues_EmptyCurrency_EmptyBank2() {
        cashBank.add("USD", 100, 5);
        cashBank.add("USD", 10, 5);
        cashBank.add("USD", 1, 5);
        cashBank.get("USD", 100 * 5 + 10 * 5 + 5);
        actualBank = cashBank.getBank();

        assertEmpty(actualBank);
    }

    @Test
    public void getLastValues_EmptyCurrency_NotEmptyBank1() {

        cashBank.add("USD", 1, 1);
        cashBank.add("USD", 5, 1);
        cashBank.add("USD", 10, 1);
        cashBank.add("USD", 50, 1);
        cashBank.add("RUB", 1, 1);
        cashBank.get("USD", 1 + 5 + 10 + 50);
        actualBank = cashBank.getBank();
        putInExpectedBankNewCurrency("RUB", 1, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValues_NotEmptyCurrency_NotEmptyBank1() {
        cashBank.add("USD", 100, 30);
        cashBank.add("USD", 10, 50);
        cashBank.add("RUB", 10, 10);
        cashBank.get("USD", 100 + 10 * 2);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 29);
        putInExpectedBankExistingCurrency(10, 48);
        putInExpectedBankNewCurrency("RUB", 10, 10);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEachValues_NotEmptyCurrency() {
        cashBank.add("USD", 1, 2);
        cashBank.add("USD", 5, 2);
        cashBank.add("USD", 10, 2);
        cashBank.add("USD", 50, 2);
        cashBank.get("USD", 1 + 5 + 10 + 50);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 1, 1);
        putInExpectedBankExistingCurrency(5, 1);
        putInExpectedBankExistingCurrency(10, 1);
        putInExpectedBankExistingCurrency(50, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEmptyValue_NeedMoreBanknotes() {
        cashBank.add("USD", 1, 1);
        cashBank.get("USD", 2);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 1, 1);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEmptyValue_NeedMoreBanknotes2() {
        cashBank.add("USD", 100, 5);
        cashBank.add("USD", 10, 5);
        cashBank.add("USD", 1, 5);
        cashBank.get("USD", 100 * 5 + 10 * 5 + 5 + 1);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 5);
        putInExpectedBankExistingCurrency(10, 5);
        putInExpectedBankExistingCurrency(1, 5);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEmptyValue_NeedAnotherValues1() {
        cashBank.add("USD", 50, 1);
        cashBank.get("USD", 50 + 1);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 50, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEmptyValue_NeedAnotherValues2() {
        cashBank.add("USD", 100, 5);
        cashBank.add("USD", 10, 5);
        cashBank.get("USD", 100 * 5 + 10 * 5 + 1);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 5);
        putInExpectedBankExistingCurrency(10, 5);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getAmount_ThereAreOpportunities() {
        cashBank.add("USD", 100, 1);
        cashBank.add("USD", 10, 10);
        cashBank.get("USD", 100);

        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 10, 10);

        assertEquals(expectedBank, actualBank);

    }
}
