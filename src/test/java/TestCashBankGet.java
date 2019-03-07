import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCashBankGet extends TestBase{

    @Test
    public void getLastValueEmptyCurrencyEmptyBank()
    {
        cashBank.add("USD", 1, 1);
        cashBank.get("USD", 1);
        actualBank = cashBank.getBank();

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValueBankEmptyCurrency()
    {
        cashBank.add("USD", 1, 1);
        cashBank.add("RUB", 1, 1);
        cashBank.get("USD", 1);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("RUB", 1, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValueNotEmptyCurrency()
    {
        cashBank.add("USD", 1, 1);
        cashBank.add("USD", 10, 1);
        cashBank.get("USD", 1);

        putInExpectedBankNewCurrency("USD", 10, 1);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }


    @Test
    public void getLastValueAnotherValueNotLast1()
    {
        cashBank.add("USD", 100, 1);
        cashBank.add("USD", 10, 1);
        cashBank.get("USD",109);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 1);
        putInExpectedBankExistingCurrency(10,1);

        assertEquals(expectedBank, actualBank);
    }


    @Test
    public void getLastValueAnotherValueNotLast2()
    {
        cashBank.add("USD", 100, 1);
        cashBank.add("USD", 10, 10);
        cashBank.get("USD",190);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 10, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastValueAnotherValueNotLast3()
    {
        cashBank.add("USD", 100, 10);
        cashBank.add("USD", 10, 1);
        cashBank.get("USD",110);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 9);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastFourValuesEmptyCurrencyEmptyBank1()
    {
        cashBank.add("USD", 1, 1);
        cashBank.add("USD", 5, 1);
        cashBank.add("USD", 10, 1);
        cashBank.add("USD", 50, 1);
        cashBank.get("USD",1 + 5 + 10 + 50);
        actualBank = cashBank.getBank();

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastFourValuesEmptyCurrencyEmptyBank2()
    {
        cashBank.add("USD", 100, 5);
        cashBank.add("USD", 10, 5);
        cashBank.add("USD", 1, 5);
        cashBank.get("USD",100*5 + 10*5 + 1*5);
        actualBank = cashBank.getBank();

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getLastFourValuesEmptyCurrencyNotEmptyBank()
    {

        cashBank.add("USD", 1, 1);
        cashBank.add("USD", 5, 1);
        cashBank.add("USD", 10, 1);
        cashBank.add("USD", 50, 1);
        cashBank.add("RUB", 1, 1);
        cashBank.get("USD",1 + 5 + 10 + 50);
        actualBank = cashBank.getBank();
        putInExpectedBankNewCurrency("RUB", 1, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEachFourValuesNotEmptyCurrency()
    {
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

    @Test
    public void getEmptyValueNeedMoreBanknotes()
    {
        cashBank.add("USD", 1, 1);
        cashBank.get("USD", 2);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 1, 1);

        actualBank = cashBank.getBank();
        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEmptyValueNeedMoreBanknotes2()
    {
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

    @Test
    public void getEmptyValueNeedAnotherValues1()
    {
        cashBank.add("USD", 50, 1);
        cashBank.get("USD",50 + 1);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 50, 1);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void getEmptyValueNeedAnotherValues2()
    {
        cashBank.add("USD", 100, 5);
        cashBank.add("USD", 10, 5);
        cashBank.get("USD",100*5 + 10*5 + 1 );
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 5);
        putInExpectedBankExistingCurrency(10,5);

        assertEquals(expectedBank, actualBank);
    }

    //TODO Low Performance
    @Test
    public void getAmountThereAreOpportunities()
    {
        cashBank.add("USD", 100, 1);
        cashBank.add("USD", 10, 10);
        cashBank.get("USD",100);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 10, 10);

        assertEquals(expectedBank, actualBank);
    }

    @Test
    public void test13()
    {
        cashBank.add("USD", 100, 30);
        cashBank.add("USD", 10, 50);
        cashBank.add("RUB", 10, 10);
        cashBank.get("USD",100*1 + 10*2);
        actualBank = cashBank.getBank();

        putInExpectedBankNewCurrency("USD", 100, 29);
        putInExpectedBankExistingCurrency(10,48);
        putInExpectedBankNewCurrency("RUB", 10, 10);

        assertEquals(expectedBank, actualBank);
    }

}
