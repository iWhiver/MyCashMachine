package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;

import java.util.ArrayList;
import java.util.List;

//TODO Get Cash by small or big banknotes

public class Computer {

    private CashBank cashBank;

    public Computer() {

        cashBank = new CashBank();
    }

    public String calculate(String command) {

        if (command == null) {
            return Dictionary.ERROR_STATUS;
        }

        String[] operation = command.split(" ");

        List<Command> commandsImplementations = new ArrayList<>();

        commandsImplementations.add(new CommandAddNotes(cashBank));
        commandsImplementations.add(new CommandGetCash(cashBank));
        commandsImplementations.add(new CommandPrintCash(cashBank));
        commandsImplementations.add(new CommandExit());

        for(Command commandForRun : commandsImplementations){

            if (commandForRun.isSuited(operation)){

                return commandForRun.apply(operation);
            }
        }

        return Dictionary.ERROR_STATUS;
    }
}
