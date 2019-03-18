package com.belorechev.cashmachine.computer;

import com.belorechev.cashmachine.data.CashBank;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.belorechev.cashmachine.utility.Dictionary.*;
import static org.junit.Assert.*;


public class ComputerTest {

    private final CashBank cashBankMock = Mockito.mock(CashBank.class);
    private final Computer computer = new Computer(cashBankMock);

    @Test
    public void shouldReturnErrorStatus_ForEmptyCommand() {
        assertEquals(ERROR_STATUS, computer.calculate(""));
    }

    @Test
    public void shouldReturnErrorStatus_ForNullCommand() {
        assertEquals(ERROR_STATUS, computer.calculate(null));
    }

    @Test
    public void shouldReturnExitStatus_ForUppercaseCommandExit() {
        assertEquals(EXIT_STATUS, computer.calculate("EXIT"));
    }

    @Test
    public void shouldReturnExitStatus_ForLowercaseCommandExit() {
        assertEquals(EXIT_STATUS, computer.calculate("exit"));
    }

    @Test
    public void shouldReturnOkStatus_ForCommandPrintCash() {
        Mockito.when(cashBankMock.getPrintForm()).thenReturn("");
        assertEquals(OK_STATUS, computer.calculate("?"));
    }

    @Test
    public void shouldReturnOkStatus_ForCommandAddNotes() {
        assertEquals(OK_STATUS, computer.calculate("+ USD 10 50"));
    }

    @Test
    public void shouldReturnOkStatus_ForCommandGetCash() {
        Mockito.when(cashBankMock.get(Mockito.anyString(), Mockito.anyInt())).thenReturn(Optional.of(""));
        assertEquals(OK_STATUS, computer.calculate("- USD 10"));
    }
}