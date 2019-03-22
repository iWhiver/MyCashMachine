package com.belorechev.cashmachine.computer.commands

import com.belorechev.cashmachine.utility.Dictionary

class CommandExit extends CommandTemplate {

    CommandExit() {

        identification = "exit"
    }

    @Override
    String apply(String[] operation) {

        return Dictionary.EXIT_STATUS
    }
}
