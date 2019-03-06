package com.Belorechev.InputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputCommandByConsole implements InputCommand{

    private BufferedReader br;

    public InputCommandByConsole() {

        br = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public String getNext() throws IOException {

        return br.readLine();
    }
}
