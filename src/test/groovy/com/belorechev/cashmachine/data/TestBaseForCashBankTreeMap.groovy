package com.belorechev.cashmachine.data

import org.junit.Before

abstract class TestBaseForCashBankTreeMap {

    protected CashBankTreeMap cashBank

    protected Map<String, Map<Integer, Integer>> expectedBank
    protected Map<Integer, Integer> banknotesOfCurrency

    @Before
    void init() {

        cashBank = new CashBankTreeMap()
        expectedBank = new TreeMap<>()
        banknotesOfCurrency = new TreeMap<>()
    }

    void putInExpectedBankNewCurrency(String currency, Integer value, Integer number) {

        banknotesOfCurrency = new TreeMap<>()
        banknotesOfCurrency[value] = number
        expectedBank[currency] = banknotesOfCurrency
    }

    void putInExpectedBankExistingCurrency(Integer value, Integer number) {

        banknotesOfCurrency[value] = number
    }
}
