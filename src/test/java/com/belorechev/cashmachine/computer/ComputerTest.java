package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.data.CashBank;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static com.belorechev.cashmachine.utility.Dictionary.*;

import java.util.Optional;

import static org.junit.Assert.*;

public class ComputerTest {

    private Computer computer;

    @Before
    public void setUp() {

        CashBank cashBankMock = Mockito.mock(CashBank.class);
        Mockito.when(cashBankMock.getPrintForm()).thenReturn("");
        Mockito.when(cashBankMock.get("USD", 10)).thenReturn(Optional.of("10 1"));
        computer = new Computer(cashBankMock);
    }

    @Test
    public void test1() {

        assertEquals(ERROR_STATUS, computer.calculate(""));
        assertEquals(ERROR_STATUS, computer.calculate(null));

        assertEquals(EXIT_STATUS, computer.calculate("exit"));
        assertEquals(EXIT_STATUS, computer.calculate("EXIT"));
        assertEquals(EXIT_STATUS, computer.calculate("eXiT"));
    }

    @Test
    public void test2() {

        assertEquals(OK_STATUS, computer.calculate("?"));

        assertEquals(ERROR_STATUS, computer.calculate("?? "));
        assertEquals(ERROR_STATUS, computer.calculate(" ?"));
        assertEquals(ERROR_STATUS, computer.calculate(" ?"));
        assertEquals(ERROR_STATUS, computer.calculate("? USD 10"));
    }

    @Test
    public void test3() {

        assertEquals(OK_STATUS, computer.calculate("+ USD 10 50"));

        assertEquals(ERROR_STATUS, computer.calculate("Add USD 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ usd 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ u 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ us 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ usD 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ uSD 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USDD 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+  USD 10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+  uSD    10    50"));
        assertEquals(ERROR_STATUS, computer.calculate("+"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 50 + USD 10"));

        for (Integer banknote : VALID_BANKNOTES) {
            String command = String.format("+ USD %d 50", banknote);
            assertEquals(OK_STATUS, computer.calculate(command));
        }

        assertEquals(ERROR_STATUS, computer.calculate("+ USD -1 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 0 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 7 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 99999 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 1.0 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD -1.0 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 9.5 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10000 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10000 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD \r\n10 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD Hello 50"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD @Test 50"));

        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 0"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 -1"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 -100"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 1.0"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 9.5"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 \r\n9.5"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 Hello"));
        assertEquals(ERROR_STATUS, computer.calculate("+ USD 10 @Test"));
    }

    @Test
    public void test4() {

        assertEquals("10 1OK", computer.calculate("- USD 10"));

        assertEquals(ERROR_STATUS, computer.calculate("- USD 0"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD -1"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD -923"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD -1.0"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD 30+5"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD 10++"));

        assertEquals(ERROR_STATUS, computer.calculate("-  USD 100"));
        assertEquals(ERROR_STATUS, computer.calculate("- USDD 100"));
        assertEquals(ERROR_STATUS, computer.calculate("- usd 100"));
        assertEquals(ERROR_STATUS, computer.calculate("- USd 100"));
        assertEquals(ERROR_STATUS, computer.calculate("- 100"));
        assertEquals(ERROR_STATUS, computer.calculate("-"));
        assertEquals(ERROR_STATUS, computer.calculate("- 10 USD"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD 10.1"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD Hello"));
        assertEquals(ERROR_STATUS, computer.calculate("- USD " + NEW_LINE));
    }
}