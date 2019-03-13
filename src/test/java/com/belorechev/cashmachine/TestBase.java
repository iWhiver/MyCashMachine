package com.belorechev.cashmachine;

import com.belorechev.cashmachine.data.CashBankTreeMap;
import org.junit.Assert;
import org.junit.Before;

import java.util.Map;
import java.util.TreeMap;

public abstract class TestBase {

    protected CashBankTreeMap cashBank;

    protected Map<String, Map<Integer, Integer>> expectedBank;
    protected Map<String, Map<Integer, Integer>> actualBank;
    protected Map<Integer, Integer> banknotesOfCurrency;

    @Before
    public void init(){

        cashBank = new CashBankTreeMap();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();

    }

    protected void putInExpectedBankNewCurrency(String currency, Integer value, Integer number){

        banknotesOfCurrency = new TreeMap<>();
        banknotesOfCurrency.put(value,number);
        expectedBank.put(currency, banknotesOfCurrency);
    }

    protected void putInExpectedBankExistingCurrency(Integer value, Integer number){

        banknotesOfCurrency.put(value,number);
    }

    protected void assertEmpty(Map<String, Map<Integer, Integer>>  bank){

        Assert.assertEquals(new TreeMap<>(), bank);
    }
}
