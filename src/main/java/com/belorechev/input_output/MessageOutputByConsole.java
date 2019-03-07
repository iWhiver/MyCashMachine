package com.belorechev.input_output;

public class MessageOutputByConsole implements MessageOutput {

    @Override
    public void printMessage(String output) {

        System.out.println(output);
    }
}
