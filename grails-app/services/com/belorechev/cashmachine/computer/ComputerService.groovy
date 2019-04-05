package com.belorechev.cashmachine.computer

import com.belorechev.cashmachine.computer.commands.*
import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.data.CashBank
import org.springframework.beans.factory.annotation.Value

class ComputerService {

    CashBank cashBank
    Validator validator

    @Value('${dictionary.ERROR_STATUS}')
    String ERROR_STATUS

    String calculate(String command) {

        if (command == null) {
            return ERROR_STATUS
        }

        String[] operation = command.split(" ")

        List<CommandTemplate> commandsImplementations = new ArrayList<>()

        commandsImplementations << new CommandAddNotes(cashBank, validator)
        commandsImplementations << new CommandGetCash(cashBank, validator)
        commandsImplementations << new CommandPrintCash(cashBank, validator)
        commandsImplementations << new CommandExit()

        for (CommandTemplate commandForRun in commandsImplementations) {
            if (commandForRun.isSuited(operation)) {
                return commandForRun.apply(operation)
            }
        }

        return ERROR_STATUS
    }
}