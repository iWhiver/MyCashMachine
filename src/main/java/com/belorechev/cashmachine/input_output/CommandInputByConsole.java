package com.belorechev.cashmachine.input_output;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component("ByConsole")
public class CommandInputByConsole implements CommandInput {

    private BufferedReader br;

    public CommandInputByConsole() {

        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("CommandInputByConsole has been created");
    }

    @Override
    public String getNext() throws IOException {

        return br.readLine();
    }
}
