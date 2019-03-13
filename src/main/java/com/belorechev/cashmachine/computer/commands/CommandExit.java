package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.utility.Dictionary;

public class CommandExit implements CommandTemplate {

    @Override
    public boolean isSuited(String[] operation) {

        String identification = "exit";

        return operation[0].equalsIgnoreCase(identification);
    }

    @Override
    public String apply(String[] operation) {

        return Dictionary.EXIT_STATUS;
    }
}
