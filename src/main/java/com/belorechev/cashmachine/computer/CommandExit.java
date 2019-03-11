package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.utility.Dictionary;

class CommandExit extends CommandTemplate {

    private String identification = "exit";

    @Override
    public boolean isSuited(String[] operation) {

        return operation[0].equals(identification);
    }

    @Override
    public String apply(String[] operation) {

        return Dictionary.EXIT_STATUS;
    }
}
