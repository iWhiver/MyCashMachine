package com.belorechev.cashmachine.data;

import com.belorechev.cashmachine.utility.Dictionary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Qualifier("TreeMap")
public class CashBankTreeMap implements CashBank {

    private final Map<String, Map<Integer, Integer>> bank;

    public CashBankTreeMap() {

        bank = new TreeMap<>();
    }

    public void add(String currency, Integer value, final Integer number) {

        Map<Integer, Integer> banknotesOfCurrency;

        if (bank.containsKey(currency)) {

            banknotesOfCurrency = bank.get(currency);

            if (banknotesOfCurrency.containsKey(value)) {
                banknotesOfCurrency.compute(value, (k, v) -> v == null ? number : v + number);
            } else {
                banknotesOfCurrency.put(value, number);
            }

        } else {
            banknotesOfCurrency = new TreeMap<>();
            banknotesOfCurrency.put(value, number);
        }

        bank.put(currency, banknotesOfCurrency);
    }

    public Optional<String> get(String currency, int amount) {

        if (!bank.containsKey(currency)) {
            return Optional.empty();
        }

        Map<Integer, Integer> realBanknotesOfCurrency = bank.get(currency);
        Map<Integer, Integer> copyBanknotesOfCurrency = new TreeMap<>(realBanknotesOfCurrency);
        Map<Integer, Integer> banknotesForOutput = new TreeMap<>(Collections.reverseOrder());

        while (amount != 0) {

            Integer biggestAvailableBanknoteValue = getBiggestAvailableBanknoteValue(amount, copyBanknotesOfCurrency);

            if (biggestAvailableBanknoteValue == 0) {
                return Optional.empty();
            }

            int amountOfBanknotes = copyBanknotesOfCurrency.get(biggestAvailableBanknoteValue);
            int necessary = amount / biggestAvailableBanknoteValue;
            final int amountOfBanknotesOperation = necessary <= amountOfBanknotes ? necessary : amountOfBanknotes;

            copyBanknotesOfCurrency.compute(
                    biggestAvailableBanknoteValue,
                    (k, v) -> v == null ? 0 : v - amountOfBanknotesOperation);

            copyBanknotesOfCurrency.remove(biggestAvailableBanknoteValue, 0);

            amount -= biggestAvailableBanknoteValue * amountOfBanknotesOperation;

            banknotesForOutput.putIfAbsent(biggestAvailableBanknoteValue, amountOfBanknotesOperation);
        }

        if (!banknotesForOutput.isEmpty()) {

            if (!copyBanknotesOfCurrency.isEmpty()) {
                bank.put(currency, copyBanknotesOfCurrency);
            } else {
                bank.remove(currency);
            }

            return Optional.of(
                    banknotesToString(banknotesForOutput));
        }

        return Optional.empty();
    }

    private Integer getBiggestAvailableBanknoteValue(int amount, Map<Integer, Integer> copyBanknotesOfCurrency) {

        Set<Integer> allAvailableBanknotes = copyBanknotesOfCurrency.keySet();

        Integer biggestAvailableBanknoteValue = 0;

        for (Integer valueOfBanknotes : allAvailableBanknotes) {

            if (biggestAvailableBanknoteValue <= valueOfBanknotes &&
                    valueOfBanknotes <= amount &&
                    copyBanknotesOfCurrency.get(valueOfBanknotes) > 0) {

                biggestAvailableBanknoteValue = valueOfBanknotes;
            }
        }
        return biggestAvailableBanknoteValue;
    }

    private String banknotesToString(Map<Integer, Integer> banknotesForOutput) {

        StringBuilder message = new StringBuilder();

        for (Map.Entry<Integer, Integer> banknotes : banknotesForOutput.entrySet()) {

            Integer value = banknotes.getKey();
            Integer number = banknotes.getValue();

            String line = String.format("%d %d", value, number);
            message.append(line);
            message.append(Dictionary.NEW_LINE);
        }

        return message.toString();
    }

    @Override
    public String getPrintForm() {

        StringBuilder message = new StringBuilder();

        for (Map.Entry<String, Map<Integer, Integer>> bankEntry : bank.entrySet()) {

            String currency = bankEntry.getKey();
            Map<Integer, Integer> subBank = bankEntry.getValue();

            for (Map.Entry<Integer, Integer> currencyEntry : subBank.entrySet()) {

                Integer value = currencyEntry.getKey();
                Integer number = currencyEntry.getValue();

                String line = String.format("%s %d %d", currency, value, number);
                message.append(line);
                message.append(Dictionary.NEW_LINE);
            }
        }

        return message.toString();
    }

    Map<String, Map<Integer, Integer>> getBank() {

        return bank;
    }

}
