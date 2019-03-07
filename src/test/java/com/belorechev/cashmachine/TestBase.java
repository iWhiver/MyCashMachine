package com.belorechev.cashmachine;

import com.belorechev.cashmachine.computer.Computer;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;
import org.junit.Before;

import java.util.Map;
import java.util.TreeMap;

public class TestBase {

    Computer computer;
    CashBank cashBank;

    Map<String, Map<Integer, Integer>> expectedBank;
    Map<String, Map<Integer, Integer>> actualBank;
    Map<Integer, Integer> banknotesOfCurrency;

    String[] commands;
    String[] expectedOutputs;
    String command;

    String OK;
    String ERROR;
    String EXIT;

    @Before
    public void init(){
        computer = new Computer();
        cashBank = new CashBank();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();

        Dictionary.NEW_LINE = "";
        OK = Dictionary.OK_STATUS;
        ERROR = Dictionary.ERROR_STATUS;
        EXIT = Dictionary.EXIT_STATUS;

    }

    void putInExpectedBankNewCurrency(String currency, Integer value, Integer number){

        banknotesOfCurrency = new TreeMap<>();
        banknotesOfCurrency.put(value,number);
        expectedBank.put(currency, banknotesOfCurrency);
    }

    void putInExpectedBankExistingCurrency(Integer value, Integer number){

        banknotesOfCurrency.put(value,number);
    }
}
