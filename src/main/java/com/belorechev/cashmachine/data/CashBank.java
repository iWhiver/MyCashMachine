package com.belorechev.cashmachine.data;

import java.util.Optional;

public interface CashBank {

    void add(String currency, Integer value, final Integer number);

    Optional<String> get(String currency, int amount);

    String getPrintForm();
}
