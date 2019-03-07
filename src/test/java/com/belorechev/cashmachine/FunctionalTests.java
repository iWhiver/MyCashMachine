package com.belorechev.cashmachine;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class FunctionalTests extends TestBase{

    @Test
    public void sampleSession(){

        commands = new String[]{
                "?",
                "+ USD 100 30",
                "+ RUR 250 10",
                "+ CHF 100 5",
                "+ USD 10 50",
                "?",
                "- USD 120",
                "- RUR 500",
                "-  CHF 250",
                "?",
                "+ eur 100 5",
                "- CHF 400",
                "+ CHF 10 50", "?"};

        expectedOutputs = new String[]{
                "OK",
                "OK",
                "ERROR",
                "OK",
                "OK",
                "CHF 100 5" + "USD 10 50" + "USD 100 30" + "OK",
                "100 1" +  "10 2" + "OK",
                "ERROR",
                "ERROR",
                "CHF 100 5" + "USD 10 48" + "USD 100 29" + "OK",
                "ERROR",
                "100 4" + "OK",
                "OK",
                "CHF 10 50" + "CHF 100 1" + "USD 10 48" + "USD 100 29" + "OK"
        };

        if (commands.length != expectedOutputs.length)
            System.err.printf("Count of commands and expected outputs must be same in testing: commands = %d, expectedOutputs = %d", commands.length, expectedOutputs.length);

        for (int i = 0; i < commands.length; i++) {
            String output = computer.calculate(commands[i]);
            assertEquals("The command = " + commands[i], expectedOutputs[i], output);

        }

    }
}
