package com.belorechev.cashmachine.input_output;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ByConsole")
public class MessageOutputByConsole implements MessageOutput {

    @Override
    public void printMessage(String output) {

        if (output == null) {
            throw new IllegalArgumentException();
        }

        System.out.println(output);
    }
}
