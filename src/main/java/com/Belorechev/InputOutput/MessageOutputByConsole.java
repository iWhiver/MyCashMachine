package com.Belorechev.InputOutput;

public class OutputMessageByConsole implements OutputMessage {

    @Override
    public void printMessage(String output) {

        System.out.println(output);
    }
}
