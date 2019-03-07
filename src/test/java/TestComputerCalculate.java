import com.belorechev.computer.Computer;
import com.belorechev.utility.Dictionary;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestComputerCalculate extends TestBase {

    @Test
    public void test1() {

        assertEquals(ERROR, computer.calculate(""));
        assertEquals(ERROR, computer.calculate(null));

        assertEquals(EXIT, computer.calculate("exit"));
    }

    @Test
    public void test2() {

        assertEquals(OK, computer.calculate("?"));
        assertEquals(ERROR, computer.calculate("?? "));
        assertEquals(ERROR, computer.calculate(" ?"));
        assertEquals(ERROR, computer.calculate(" ?"));
        assertEquals(ERROR, computer.calculate("? USD 10"));
    }

    @Test
    public void test3() {

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

    @Test
    public void test4() {

        String startValue = "+ USD 10 1";

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
