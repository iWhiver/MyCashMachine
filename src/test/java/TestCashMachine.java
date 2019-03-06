import com.Belorechev.Computer.CashBank;
import com.Belorechev.Computer.Computer;
import com.Belorechev.InputOutput.OutputMessage;
import com.Belorechev.Utills.Dictionary;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class TestCashMachine {
    private Computer computer;
    private OutputMessage outputCom;
    private CashBank cashBank;

    private Map<String, Map<Integer, Integer>> expectedBank;
    private Map<String, Map<Integer, Integer>> actualBank;
    private Map<Integer, Integer> banknotesOfCurrency;

    private String[] commands;
    private String[] expectedOutputs;
    private String command;
    private String output;

    private String OK;
    private String ERROR;
    private String EXIT;

    @Before
    public void init(){
        computer = new Computer();
        outputCom = new OutputMessage();
        cashBank = new CashBank();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();

        Dictionary.NEW_LINE = "";
        OK = Dictionary.OK_STATUS;
        ERROR = Dictionary.ERROR_STATUS;
        EXIT = Dictionary.EXIT_STATUS;

    }

    @Test
    public void functionalTest(){

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
            assertEquals("Number of command = " + i, expectedOutputs[i], output);

        }

    }

    @Test
    public void testComputerCalculate(){

        assertEquals(ERROR, computer.calculate(""));
        assertEquals(ERROR, computer.calculate(null));

        assertEquals(EXIT, computer.calculate("exit"));

        {
            assertEquals(OK, computer.calculate("?"));
            assertEquals(ERROR, computer.calculate("?? "));
            assertEquals(ERROR, computer.calculate(" ?"));
            assertEquals(ERROR, computer.calculate(" ?"));
            assertEquals(ERROR, computer.calculate("? USD 10"));
        }

        {
            assertEquals(OK, computer.calculate("+ USD 10 50"));

            assertEquals(ERROR, computer.calculate("Add USD 10 50"));
            assertEquals(ERROR, computer.calculate("+ usd 10 50"));
            assertEquals(ERROR, computer.calculate("+ u 10 50"));
            assertEquals(ERROR, computer.calculate("+ us 10 50"));
            assertEquals(ERROR, computer.calculate("+ usD 10 50"));
            assertEquals(ERROR, computer.calculate("+ uSD 10 50"));
            assertEquals(ERROR, computer.calculate("+ USDD 10 50"));
            assertEquals(ERROR, computer.calculate("+  USD 10 50"));
            assertEquals(ERROR, computer.calculate("+  uSD    10    50"));
            assertEquals(ERROR, computer.calculate("+"));
            assertEquals(ERROR, computer.calculate("+ USD"));
            assertEquals(ERROR, computer.calculate("+ USD 10"));
            assertEquals(ERROR, computer.calculate("+ USD 10 50 + USD 10"));

            for (Integer banknote : Dictionary.VALID_BANKNOTES) {
                command = String.format("+ USD %d 50", banknote);
                assertEquals(OK, computer.calculate(command));
            }

            assertEquals(ERROR, computer.calculate("+ USD -1 50"));
            assertEquals(ERROR, computer.calculate("+ USD 0 50"));
            assertEquals(ERROR, computer.calculate("+ USD 7 50"));
            assertEquals(ERROR, computer.calculate("+ USD 99999 50"));
            assertEquals(ERROR, computer.calculate("+ USD 1.0 50"));
            assertEquals(ERROR, computer.calculate("+ USD -1.0 50"));
            assertEquals(ERROR, computer.calculate("+ USD 9.5 50"));
            assertEquals(ERROR, computer.calculate("+ USD 10000 50"));
            assertEquals(ERROR, computer.calculate("+ USD 10000 50"));
            assertEquals(ERROR, computer.calculate("+ USD \r\n10 50"));
            assertEquals(ERROR, computer.calculate("+ USD Hello 50"));
            assertEquals(ERROR, computer.calculate("+ USD @Test 50"));

            assertEquals(ERROR, computer.calculate("+ USD 10 0"));
            assertEquals(ERROR, computer.calculate("+ USD 10 -1"));
            assertEquals(ERROR, computer.calculate("+ USD 10 -100"));
            assertEquals(ERROR, computer.calculate("+ USD 10 1.0"));
            assertEquals(ERROR, computer.calculate("+ USD 10 9.5"));
            assertEquals(ERROR, computer.calculate("+ USD 10 \r\n9.5"));
            assertEquals(ERROR, computer.calculate("+ USD 10 Hello"));
            assertEquals(ERROR, computer.calculate("+ USD 10 @Test"));
        }

        {
            computer = new Computer();

            final String startValue = "+ USD 10 1";
            computer.calculate(startValue);

            assertEquals("10 1OK", computer.calculate("- USD 10"));

            assertEquals(ERROR, computer.calculate("- USD 0"));
            assertEquals(ERROR, computer.calculate("- USD -1"));
            assertEquals(ERROR, computer.calculate("- USD -923"));
            assertEquals(ERROR, computer.calculate("- USD -1.0"));
            assertEquals(ERROR, computer.calculate("- USD 30+5"));
            assertEquals(ERROR, computer.calculate("- USD 10++"));

            assertEquals(ERROR, computer.calculate("-  USD 100"));
            assertEquals(ERROR, computer.calculate("- USDD 100"));
            assertEquals(ERROR, computer.calculate("- usd 100"));
            assertEquals(ERROR, computer.calculate("- USd 100"));
            assertEquals(ERROR, computer.calculate("- 100"));
            assertEquals(ERROR, computer.calculate("-"));
            assertEquals(ERROR, computer.calculate("- 10 USD"));
            assertEquals(ERROR, computer.calculate("- USD 10.1"));
            assertEquals(ERROR, computer.calculate("- USD Hello"));
            assertEquals(ERROR, computer.calculate("- USD \r\n"));
        }

    }

    @Test
    public void testCashBankAdd(){

        {
            cleanValues();
            cashBank.add("USD", 10, 10);

            putInExpectedBankNewCurrency("USD", 10, 10);

            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 10, 10);
            cashBank.add("USD", 50, 5);

            putInExpectedBankNewCurrency("USD", 10, 10);
            putInExpectedBankExistingCurrency(50,5);


            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 10, 5);
            cashBank.add("USD", 10, 10);

            putInExpectedBankNewCurrency("USD", 10, 10 + 5);

            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("RUB", 10, 5);
            cashBank.add("USD", 10, 5);

            putInExpectedBankNewCurrency("USD", 10, 5);
            putInExpectedBankNewCurrency("RUB", 10, 5);

            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("EUR", 10, 5);

            putInExpectedBankNewCurrency("EUR", 10, 5);

            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }
    }


    @Test
    public void testCashBankGet(){

        {
            cleanValues();
            cashBank.add("USD", 1, 1);
            cashBank.get("USD", 1);
            actualBank = cashBank.getBank();

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 1, 1);
            cashBank.add("RUB", 1, 1);
            cashBank.get("USD", 1);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("RUB", 1, 1);

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 1, 1);
            cashBank.add("USD", 10, 1);
            cashBank.get("USD", 1);

            putInExpectedBankNewCurrency("USD", 10, 1);

            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 1, 1);
            cashBank.get("USD", 2);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 1, 1);

            actualBank = cashBank.getBank();
            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 1, 1);
            cashBank.add("USD", 5, 1);
            cashBank.add("USD", 10, 1);
            cashBank.add("USD", 50, 1);
            cashBank.get("USD",1 + 5 + 10 + 50);
            actualBank = cashBank.getBank();

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 1, 2);
            cashBank.add("USD", 5, 2);
            cashBank.add("USD", 10, 2);
            cashBank.add("USD", 50, 2);
            cashBank.get("USD",1 + 5 + 10 + 50);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 1, 1);
            putInExpectedBankExistingCurrency(5,1);
            putInExpectedBankExistingCurrency(10,1);
            putInExpectedBankExistingCurrency(50,1);

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 50, 1);
            cashBank.get("USD",50 + 1);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 50, 1);

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 100, 1);
            cashBank.add("USD", 10, 10);
            cashBank.get("USD",100);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 10, 10);

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 100, 1);
            cashBank.add("USD", 10, 1);
            cashBank.get("USD",109);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 100, 1);
            putInExpectedBankExistingCurrency(10,1);

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 100, 1);
            cashBank.add("USD", 10, 10);
            cashBank.get("USD",190);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 10, 1);

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 100, 5);
            cashBank.add("USD", 10, 5);
            cashBank.add("USD", 1, 5);
            cashBank.get("USD",100*5 + 10*5 + 1*5);
            actualBank = cashBank.getBank();

            assertEquals(expectedBank, actualBank);
        }

        {
            cleanValues();
            cashBank.add("USD", 100, 5);
            cashBank.add("USD", 10, 5);
            cashBank.add("USD", 1, 5);
            cashBank.get("USD",100*5 + 10*5 + 1*5 + 1);
            actualBank = cashBank.getBank();

            putInExpectedBankNewCurrency("USD", 100, 5);
            putInExpectedBankExistingCurrency(10,5);
            putInExpectedBankExistingCurrency(1,5);

            assertEquals(expectedBank, actualBank);
        }
    }

    private void cleanValues(){

        cashBank = new CashBank();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();
    }

    private void putInExpectedBankNewCurrency(String currency, Integer value, Integer number){

        banknotesOfCurrency = new TreeMap<>();
        banknotesOfCurrency.put(value,number);
        expectedBank.put(currency, banknotesOfCurrency);
    }

    private void putInExpectedBankExistingCurrency(Integer value, Integer number){

        banknotesOfCurrency.put(value,number);
    }

}
