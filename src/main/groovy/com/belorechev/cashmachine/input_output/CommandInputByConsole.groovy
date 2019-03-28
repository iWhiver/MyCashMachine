package com.belorechev.cashmachine.input_output

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component()
@Qualifier("ByConsole")
class CommandInputByConsole implements CommandInput {

    private final BufferedReader br

    CommandInputByConsole() {
        br = new BufferedReader(new InputStreamReader(System.in))
    }

    @Override
    String getNext() throws IOException {
        br.readLine()
    }
}
