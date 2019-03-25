package com.belorechev.cashmachine.logic

import com.belorechev.cashmachine.computer.Computer
import com.belorechev.cashmachine.input_output.CommandInput
import com.belorechev.cashmachine.input_output.MessageOutput
import com.belorechev.cashmachine.utility.Dictionary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class LifeCircle {

    private final CommandInput commandInput
    private final MessageOutput outputCom
    private final Computer computer

    @Autowired
    LifeCircle(@Qualifier("ByConsole") CommandInput commandInput,
               @Qualifier("ByConsole") MessageOutput outputCom,
               Computer computer, Dictionary dictionaryInput) {

        this.commandInput = commandInput
        this.outputCom = outputCom
        this.computer = computer
    }

    @PostConstruct
    private void start() throws IOException {

        outputCom.printMessage(Dictionary.HI_STATUS)

        int flagAntiHang = 0

        while (++flagAntiHang < 1000) {
            String command = commandInput.getNext()
            String output = computer.calculate(command)
            outputCom.printMessage(output)

            if (output == Dictionary.EXIT_STATUS) {
                break
            } else {
                flagAntiHang = 0
            }
        }
    }
}
