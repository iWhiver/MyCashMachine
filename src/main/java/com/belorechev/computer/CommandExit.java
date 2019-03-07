package com.belorechev.computer;

import com.belorechev.utility.Dictionary;

class CommandExit implements Command {

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
