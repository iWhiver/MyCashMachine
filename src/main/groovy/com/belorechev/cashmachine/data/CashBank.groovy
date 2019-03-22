package com.belorechev.cashmachine.data

interface CashBank {

    void add(String currency, Integer value, final Integer number);

    Set<Cash> get(String currency, int amount);

    String getPrintForm();
}
