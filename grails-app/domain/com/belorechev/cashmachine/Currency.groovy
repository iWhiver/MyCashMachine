package com.belorechev.cashmachine

class Currency implements Comparable<Currency> {

    String vCurrency

    Map VBanknotes

    static constraints = {
        vCurrency blank: false, unique: true
    }

    @Override
    int compareTo(Currency o) {
        this.vCurrency <=> o.vCurrency
    }
}
