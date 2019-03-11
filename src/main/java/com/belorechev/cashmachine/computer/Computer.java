package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.data.CashBankTreeMap;
import com.belorechev.cashmachine.utility.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    private CashBank cashBank;

    public Computer() {

        //TODO Injection
        cashBank = new CashBankTreeMap();
    }

    public String calculate(String command) {

        if (command == null) {
            return Dictionary.ERROR_STATUS;
        }

        String[] operation = command.split(" ");

        List<CommandTemplate> commandsImplementations = new ArrayList<>();

        commandsImplementations.add(new CommandAddNotes(cashBank));
        commandsImplementations.add(new CommandGetCash(cashBank));
        commandsImplementations.add(new CommandPrintCash(cashBank));
        commandsImplementations.add(new CommandExit());

        for(CommandTemplate commandForRun : commandsImplementations){

            if (commandForRun.isSuited(operation)){

                return commandForRun.apply(operation);
            }
        }

        return Dictionary.ERROR_STATUS;
    }
}
