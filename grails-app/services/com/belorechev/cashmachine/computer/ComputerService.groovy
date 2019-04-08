package com.belorechev.cashmachine.computer


import com.belorechev.cashmachine.computer.commands.CommandAddNotesService
import com.belorechev.cashmachine.computer.commands.CommandGetCashService
import com.belorechev.cashmachine.computer.commands.CommandPrintCashService
import com.belorechev.cashmachine.computer.commands.CommandTemplate
import com.belorechev.cashmachine.computer.processors.Validator
import org.springframework.beans.factory.annotation.Value

class ComputerService {

    Validator validator

    CommandAddNotesService commandAddNotesService
    CommandGetCashService commandGetCashService
    CommandPrintCashService commandPrintCashService

    @Value('${dictionary.ERROR_STATUS}')
    String ERROR_STATUS

    String calculate(String command) {

        if (command == null) {
            return ERROR_STATUS
        }

        String[] operation = command.split(" ")

        List<CommandTemplate> commandsImplementations = new ArrayList<>()

        commandsImplementations << commandAddNotesService
        commandsImplementations << commandGetCashService
        commandsImplementations << commandPrintCashService

        for (CommandTemplate commandForRun in commandsImplementations) {
            if (commandForRun.isSuited(operation)) {
                return commandForRun.apply(operation)
            }
        }

        return ERROR_STATUS
    }
}