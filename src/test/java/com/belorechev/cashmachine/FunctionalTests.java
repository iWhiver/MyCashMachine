package com.belorechev.cashmachine;

import com.belorechev.cashmachine.computer.Computer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static com.belorechev.cashmachine.utility.Dictionary.NEW_LINE;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FunctionalTests{

    @Autowired
    Computer computer;

    @Test
    public void sampleSession(){

        String[] commands = new String[]{
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

        String[] expectedOutputs = new String[]{
                "OK",
                "OK",
                "ERROR",
                "OK",
                "OK",
                "CHF 100 5" + NEW_LINE +  "USD 10 50" + NEW_LINE + "USD 100 30" + NEW_LINE + "OK",
                "100 1" + NEW_LINE +  "10 2" + NEW_LINE + "OK",
                "ERROR",
                "ERROR",
                "CHF 100 5" + NEW_LINE + "USD 10 48" + NEW_LINE + "USD 100 29" + NEW_LINE + "OK",
                "ERROR",
                "100 4"+ NEW_LINE + "OK",
                "OK",
                "CHF 10 50" + NEW_LINE + "CHF 100 1" + NEW_LINE + "USD 10 48" + NEW_LINE + "USD 100 29" + NEW_LINE + "OK"
        };

        assertEquals("Count of commands and expected outputs must be same in testing", expectedOutputs.length, commands.length);

        for (int i = 0; i < commands.length; i++) {
            String output = computer.calculate(commands[i]);
            assertEquals("The command = " + commands[i] + "; Number = " + (i + 1), expectedOutputs[i], output);

        }

    }
}
