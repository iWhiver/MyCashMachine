package com.belorechev.cashmachine.input_output

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("ByConsole")
class MessageOutputByConsole implements MessageOutput {

    @Override
    void printMessage(String output) {

        if (output == null) {
            throw new IllegalArgumentException()
        }

        println output
    }
}
