package com.belorechev.cashmachine.computer.commands.processors

import com.belorechev.cashmachine.computer.processors.Converter
import com.belorechev.cashmachine.computer.processors.ConverterStandardImplementation
import com.belorechev.cashmachine.data.Cash
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito

class ConverterStandardImplementationTest {

    private static Cash cashMock
    private static Set setOfCashMocks
    private static Converter converter

    @BeforeClass
    static void setUp() {

        cashMock = Mockito.mock(Cash.class)
        setOfCashMocks = new TreeSet()
        converter = new ConverterStandardImplementation()

        Mockito.when(cashMock.getCurrency()).thenReturn("USD")
        Mockito.when(cashMock.getValue()).thenReturn(100)
        Mockito.when(cashMock.getAmountOfNotes()).thenReturn(1)

        setOfCashMocks << cashMock
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfThreeFlagsAreFalse() {

        converter.convertSetOfCashToString(
                setOfCashMocks, "", false, false, false)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfLineSeparatorIsNull() {

        converter.convertSetOfCashToString(
                setOfCashMocks, null, true, true, true)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfCashSetIsNull() {

        converter.convertSetOfCashToString(
                null, "", true, true, true)
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowException_IfCashSetIsEmpty() {

        converter.convertSetOfCashToString(
                new TreeSet<Cash>(), "", true, true, true)
    }

    @Test
    void shouldWorkWell_ForUsingOnlyFlag_UseCurrency() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", true, false, false)

        assert "USD" == actual
    }

    @Test
    void shouldWorkWell_ForUsingOnlyFlag_UseValue() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", false, true, false)

        assert "100" == actual
    }

    @Test
    void shouldWorkWell_ForUsingOnlyFlag_UseAmountOfNotes() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", false, false, true)

        assert "1" == actual
    }

    @Test
    void shouldWorkWell_ForUsingOnlyFlags_UseCurrency_UseValue() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", true, true, false)

        assert "USD 100" == actual
    }

    @Test
    void shouldWorkWell_ForUsingOnlyFlags_UseCurrency_UseAmountOfNotes() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", true, false, true)

        assert "USD 1" == actual
    }

    @Test
    void shouldWorkWell_ForUsingOnlyFlags_UseValue_UseAmountOfNotes() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", false, true, true)

        assert "100 1" == actual
    }

    @Test
    void shouldWorkWell_ForUsingAllFlags() {

        String actual = converter.convertSetOfCashToString(
                setOfCashMocks, "", true, true, true)

        assert "USD 100 1" == actual
    }

    @Test
    void shouldWorkWell_ForSetOfCash_WhichHaveTwoCashValues() {

        Set<Cash> newSetOfCashMocks = new TreeSet()

        Cash firstCashMocks = Mockito.mock(Cash.class)

        Mockito.when(firstCashMocks.getCurrency()).thenReturn("USD")
        Mockito.when(firstCashMocks.getValue()).thenReturn(100)
        Mockito.when(firstCashMocks.getAmountOfNotes()).thenReturn(1)

        newSetOfCashMocks << firstCashMocks

        Cash secondCashMock = Mockito.mock(Cash.class)

        Mockito.when(secondCashMock.getCurrency()).thenReturn("RUB")
        Mockito.when(secondCashMock.getValue()).thenReturn(5)
        Mockito.when(secondCashMock.getAmountOfNotes()).thenReturn(10)

        newSetOfCashMocks << secondCashMock

        String actual = converter.convertSetOfCashToString(
                newSetOfCashMocks, "|", true, true, true)

        assert "USD 100 1|RUB 5 10|" == actual
    }

}
