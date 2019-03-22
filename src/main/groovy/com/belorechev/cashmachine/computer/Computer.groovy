package com.belorechev.cashmachine.computer

import com.belorechev.cashmachine.computer.commands.*
import com.belorechev.cashmachine.computer.processors.Validator
import com.belorechev.cashmachine.computer.processors.ValidatorStandardImplementation
import com.belorechev.cashmachine.data.CashBank
import com.belorechev.cashmachine.utility.Dictionary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class Computer {

    private final CashBank cashBank
    private final Validator validator

    @Autowired
    Computer(@Qualifier("TreeMap") CashBank cashBank) {
        this(cashBank, new ValidatorStandardImplementation())
    }

    Computer(CashBank cashBank, Validator validator) {
        this.cashBank = cashBank
        this.validator = validator
    }

    String calculate(String command) {

        if (command == null) {
            return Dictionary.ERROR_STATUS
        }

        String[] operation = command.split(" ")

        List<CommandTemplate> commandsImplementations = new ArrayList<>()

        commandsImplementations.add(new CommandAddNotes(cashBank, validator))
        commandsImplementations.add(new CommandGetCash(cashBank, validator))
        commandsImplementations.add(new CommandPrintCash(cashBank, validator))
        commandsImplementations.add(new CommandExit())

        for (CommandTemplate commandForRun : commandsImplementations) {

            if (commandForRun.isSuited(operation)) {

                return commandForRun.apply(operation)
            }
        }

        return Dictionary.ERROR_STATUS
    }
}
