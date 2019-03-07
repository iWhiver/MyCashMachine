package com.belorechev.cashmachine.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandInputByConsole implements CommandInput {

    private BufferedReader br;

    public CommandInputByConsole() {

        br = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public String getNext() throws IOException {

        return br.readLine();
    }
}
