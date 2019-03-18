package com.belorechev.cashmachine.computer.commands;

import com.belorechev.cashmachine.utility.Dictionary;

public class CommandExit extends CommandTemplate {

    public CommandExit() {

        identification = "exit";
    }

    @Override
    public String apply(String[] operation) {

        return Dictionary.EXIT_STATUS;
    }
}
