package com.belorechev.cashmachine.data

import org.junit.Test

class TestCashBankTreeMapAdd extends TestBaseForCashBankTreeMap {

    @Test
    void addOneValue() {

        cashBank.add("USD", 10, 10)

        putInExpectedBankNewCurrency("USD", 10, 10)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void addTwoValues() {

        cashBank.add("USD", 10, 10)
        cashBank.add("USD", 50, 5)

        putInExpectedBankNewCurrency("USD", 10, 10)
        putInExpectedBankExistingCurrency(50, 5)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void addOneValueByTwoSteps1() {

        cashBank.add("USD", 10, 5)
        cashBank.add("USD", 10, 10)

        putInExpectedBankNewCurrency("USD", 10, 10 + 5)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void addOneValueByTwoSteps2() {

        cashBank.add("USD", 10, 5)
        cashBank.add("USD", 10, 10)

        cashBank.add("USD", 100, 5)
        cashBank.add("USD", 100, 10)


        putInExpectedBankNewCurrency("USD", 10, 10 + 5)
        putInExpectedBankExistingCurrency(100, 10 + 5)

        assert expectedBank == cashBank.getBank()
    }

    @Test
    void addTwoCurrencies() {

        cashBank.add("RUB", 10, 5)
        cashBank.add("USD", 10, 5)

        putInExpectedBankNewCurrency("USD", 10, 5)
        putInExpectedBankNewCurrency("RUB", 10, 5)

        assert expectedBank == cashBank.getBank()
    }
}
