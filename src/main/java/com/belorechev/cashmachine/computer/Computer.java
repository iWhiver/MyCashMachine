package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.computer.commands.*;
import com.belorechev.cashmachine.data.CashBank;
import com.belorechev.cashmachine.utility.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Computer {

    private final CashBank cashBank;

    @Autowired
    public Computer(@Qualifier("TreeMap") CashBank cashBank) {
        this.cashBank = cashBank;
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
