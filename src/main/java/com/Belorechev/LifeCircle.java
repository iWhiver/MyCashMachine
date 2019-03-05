package com.Belorechev;

import com.Belorechev.Computer.Computer;
import com.Belorechev.InputOutput.InputCommand;
import com.Belorechev.InputOutput.OutputMessage;
import com.Belorechev.Utills.Dictionary;

//TODO CheckNaming
//TODO add functional for change currency

public class LifeCircle {

    public static void main(String[] args) throws Exception {

        InputCommand inputCom = new InputCommand();
        Computer computer = new Computer();
        OutputMessage outputCom = new OutputMessage();

        outputCom.printMessage(Dictionary.HI_STATUS);

        while (true) {
            String command = inputCom.getNext();
            String output = computer.calculate(command);
            outputCom.printMessage(output);


            if (output.equals(Dictionary.EXIT_STATUS))
                break;
        }
    }
}
