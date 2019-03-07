package com.belorechev;

import com.belorechev.computer.Computer;
import com.belorechev.input_output.CommandInput;
import com.belorechev.input_output.CommandInputByConsole;
import com.belorechev.input_output.MessageOutputByConsole;
import com.belorechev.input_output.MessageOutput;
import com.belorechev.utility.Dictionary;

import java.io.IOException;

//TODO CheckNaming
//TODO add functional for change currency

public class LifeCircle {

    public static void main(String[] args) throws IOException {

        CommandInput commandInput = new CommandInputByConsole();
        Computer computer = new Computer();
        MessageOutput outputCom = new MessageOutputByConsole();

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
