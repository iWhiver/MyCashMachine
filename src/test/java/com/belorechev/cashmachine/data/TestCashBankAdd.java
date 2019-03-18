package com.belorechev.cashmachine.data;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCashBankAdd extends TestBaseForCashBank {

    @Test
    public void addOneValue() {

        cashBank.add("USD", 10, 10);

        putInExpectedBankNewCurrency("USD", 10, 10);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void addTwoValues() {

        cashBank.add("USD", 10, 10);
        cashBank.add("USD", 50, 5);

        putInExpectedBankNewCurrency("USD", 10, 10);
        putInExpectedBankExistingCurrency(50, 5);


        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void addOneValueByTwoSteps1() {

        cashBank.add("USD", 10, 5);
        cashBank.add("USD", 10, 10);

        putInExpectedBankNewCurrency("USD", 10, 10 + 5);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void addOneValueByTwoSteps2() {

        cashBank.add("USD", 10, 5);
        cashBank.add("USD", 10, 10);

        cashBank.add("USD", 100, 5);
        cashBank.add("USD", 100, 10);


        putInExpectedBankNewCurrency("USD", 10, 10 + 5);
        putInExpectedBankExistingCurrency(100, 10 + 5);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void addTwoCurrencies() {

        cashBank.add("RUB", 10, 5);
        cashBank.add("USD", 10, 5);

        putInExpectedBankNewCurrency("USD", 10, 5);
        putInExpectedBankNewCurrency("RUB", 10, 5);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }
}
