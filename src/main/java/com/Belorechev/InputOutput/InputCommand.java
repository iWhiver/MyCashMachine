package com.Belorechev.InputOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputCommand {

    private BufferedReader br;

    public InputCommand() {

        br = new BufferedReader(new InputStreamReader(System.in));

    }

    public String getNext() throws Exception {

        return br.readLine();
    }
}
