package com.belorechev.cashmachine;

import com.belorechev.cashmachine.input_output.CommandInputByConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.belorechev.cashmachine.computer.Computer;
import com.belorechev.cashmachine.input_output.CommandInput;
import com.belorechev.cashmachine.input_output.MessageOutput;
import com.belorechev.cashmachine.input_output.MessageOutputByConsole;
import com.belorechev.cashmachine.utility.Dictionary;
import org.springframework.stereotype.Controller;

import java.io.IOException;

//TODO CheckNaming
//TODO add functional for change currency

@SpringBootApplication
@Controller
public class LifeCircle {

    @Autowired
    @Qualifier("ByConsole")
    private CommandInput commandInput;

    private MessageOutput outputCom;

    public static void main(String[] args) throws IOException{

        SpringApplication.run(LifeCircle.class, args);
    }

    public LifeCircle () throws IOException {

        Computer computer = new Computer();
        //TODO Injection
        outputCom = new MessageOutputByConsole();

        outputCom.printMessage(Dictionary.HI_STATUS);

        while (true) {
            String command = null;
            command = commandInput.getNext();

            String output = computer.calculate(command);
            outputCom.printMessage(output);


            if (output.equals(Dictionary.EXIT_STATUS)) {
                break;
            }
        }
    }

}
