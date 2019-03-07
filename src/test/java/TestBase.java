import com.belorechev.computer.Computer;
import com.belorechev.data.CashBank;
import com.belorechev.input_output.MessageOutput;
import com.belorechev.input_output.MessageOutputByConsole;
import com.belorechev.utility.Dictionary;
import org.junit.Before;

import java.util.Map;
import java.util.TreeMap;

public class TestBase {

    protected Computer computer;
    protected MessageOutput outputCom;
    protected CashBank cashBank;

    protected Map<String, Map<Integer, Integer>> expectedBank;
    protected Map<String, Map<Integer, Integer>> actualBank;
    protected Map<Integer, Integer> banknotesOfCurrency;

    protected String[] commands;
    protected String[] expectedOutputs;
    protected String command;

    protected String OK;
    protected String ERROR;
    protected String EXIT;

    @Before
    public void init(){
        computer = new Computer();
        outputCom = new MessageOutputByConsole();
        cashBank = new CashBank();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();

        Dictionary.NEW_LINE = "";
        OK = Dictionary.OK_STATUS;
        ERROR = Dictionary.ERROR_STATUS;
        EXIT = Dictionary.EXIT_STATUS;

    }

    protected void cleanValues(){

        cashBank = new CashBank();
        expectedBank = new TreeMap<>();
        banknotesOfCurrency = new TreeMap<>();
    }

    protected void putInExpectedBankNewCurrency(String currency, Integer value, Integer number){

        banknotesOfCurrency = new TreeMap<>();
        banknotesOfCurrency.put(value,number);
        expectedBank.put(currency, banknotesOfCurrency);
    }

    protected void putInExpectedBankExistingCurrency(Integer value, Integer number){

        banknotesOfCurrency.put(value,number);
    }
}
