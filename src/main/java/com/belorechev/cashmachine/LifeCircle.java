package com.belorechev.cashmachine;

import org.springframework.beans.factory.annotation.Autowired;
import com.belorechev.cashmachine.computer.Computer;
import com.belorechev.cashmachine.input_output.CommandInput;
import com.belorechev.cashmachine.input_output.MessageOutput;
import com.belorechev.cashmachine.utility.Dictionary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

//TODO CheckNaming
//TODO add functional for change currency

@Component
public class LifeCircle {

    @Autowired
    @Qualifier("ByConsole")
    private CommandInput commandInput;

    @Autowired
    @Qualifier("ByConsole")
    private MessageOutput outputCom;

    @Autowired
    private Computer computer;

    @PostConstruct
    private void start() throws IOException {

        outputCom.printMessage(Dictionary.HI_STATUS);

        while (true) {
            String command = commandInput.getNext();
            String output = computer.calculate(command);
            outputCom.printMessage(output);


            if (output.equals(Dictionary.EXIT_STATUS)) {
                break;
            }
        }
    }
}
