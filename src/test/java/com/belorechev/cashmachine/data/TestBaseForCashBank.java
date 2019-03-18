package com.belorechev.cashmachine.data;

import org.junit.Assert;
import org.junit.Before;

import java.util.Map;
import java.util.TreeMap;

public abstract class TestBaseForCashBank {

    CashBankTreeMap cashBank;

    Map<String, Map<Integer, Integer>> expectedBank;
    Map<String, Map<Integer, Integer>> actualBank;
    private Map<Integer, Integer> banknotesOfCurrency;

    @Before
    public void init() {

        cashBank = new CashBankTreeMap();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();
    }

    void putInExpectedBankNewCurrency(String currency, Integer value, Integer number) {

        banknotesOfCurrency = new TreeMap<>();
        banknotesOfCurrency.put(value, number);
        expectedBank.put(currency, banknotesOfCurrency);
    }

    void putInExpectedBankExistingCurrency(Integer value, Integer number) {

        banknotesOfCurrency.put(value, number);
    }

    void assertEmpty(Map<String, Map<Integer, Integer>> bank) {

        Assert.assertEquals(new TreeMap<>(), bank);
    }
}
