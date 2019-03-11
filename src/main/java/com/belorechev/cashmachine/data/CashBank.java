package com.belorechev.cashmachine.data;

import java.util.Optional;

public abstract class CashBank {

    public abstract void add(String currency, Integer value, final Integer number);

    public abstract Optional<String> get(String currency, int amount);

    public abstract String toString();
}
