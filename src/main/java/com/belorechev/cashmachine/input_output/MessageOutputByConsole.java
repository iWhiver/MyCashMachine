package com.belorechev.cashmachine.input_output;

public class MessageOutputByConsole implements MessageOutput {

    @Override
    public void printMessage(String output) {

        System.out.println(output);
    }
}
