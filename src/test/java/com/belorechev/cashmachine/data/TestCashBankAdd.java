package com.belorechev.cashmachine.data;

import com.belorechev.cashmachine.TestBase;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCashBankAdd extends TestBase {

    @Test
    public void addOneValue() {

        cashBank.add("USD", 10, 10);

        putInExpectedBankNewCurrency("USD", 10, 10);

        actualBank = cashBank.getBank();
        assertEquals("123", expectedBank, actualBank);
    }

    @Test
    public void addTwoValues() {

        cashBank.add("USD", 10, 10);
        cashBank.add("USD", 50, 5);

        putInExpectedBankNewCurrency("USD", 10, 10);
        putInExpectedBankExistingCurrency(50,5);


        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void addOneValue_ByTwoSteps1() {

        cashBank.add("USD", 10, 5);
        cashBank.add("USD", 10, 10);

        putInExpectedBankNewCurrency("USD", 10, 10 + 5);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void addOneValue_ByTwoSteps2() {

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
