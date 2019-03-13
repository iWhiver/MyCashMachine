package com.belorechev.cashmachine.input_output;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component()
@Qualifier("ByConsole")
public class CommandInputByConsole implements CommandInput {

    private final BufferedReader br;

    public CommandInputByConsole() {

        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getNext() throws IOException {

        return br.readLine();
    }
}
