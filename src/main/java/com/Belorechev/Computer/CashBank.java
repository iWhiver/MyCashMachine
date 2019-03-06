package com.Belorechev.Computer;

import com.Belorechev.Utills.Dictionary;

import java.util.*;

public class CashBank {

    private Map<String, Map<Integer, Integer>> bank;

    public CashBank() {

        bank = new TreeMap<>();
    }

    public void add(String currency, Integer value, final Integer number) {

        Map<Integer, Integer> banknotesOfCurrency;

        if (bank.containsKey(currency)) {

            banknotesOfCurrency = bank.get(currency);

            if (banknotesOfCurrency.containsKey(value)) {
                banknotesOfCurrency.compute(value, (k, v) -> v + number);
            } else {
                banknotesOfCurrency.put(value, number);
            }

        } else {
            banknotesOfCurrency = new TreeMap();
            banknotesOfCurrency.put(value, number);
        }

        bank.put(currency, banknotesOfCurrency);
    }

    //TODO Test
    //TODO take banknotes with packs without each banknotes
    public Optional<String> get(String currency, Integer amount) {

        if (!bank.containsKey(currency))
            return Optional.empty();

        Map<Integer, Integer> realBanknotesOfCurrency = bank.get(currency);

        Map<Integer, Integer> copyBanknotesOfCurrency = new TreeMap<>(realBanknotesOfCurrency);

        Map<Integer, Integer> banknotesForOutput = new TreeMap<>(Collections.reverseOrder());

        while (amount != 0) {

            Optional<Integer> biggestAvailableBanknote = getBiggestAvailableBanknote(copyBanknotesOfCurrency, amount);

            if (biggestAvailableBanknote.isPresent()) {

                Integer biggestAvailableBanknoteValue = biggestAvailableBanknote.get();
                copyBanknotesOfCurrency.compute(
                        biggestAvailableBanknoteValue,
                        (k, v) -> v - 1);

                if (copyBanknotesOfCurrency.get(biggestAvailableBanknoteValue) == 0)
                    copyBanknotesOfCurrency.remove(biggestAvailableBanknoteValue);

                amount -= biggestAvailableBanknoteValue;

                if (banknotesForOutput.containsKey(biggestAvailableBanknoteValue)) {

                    banknotesForOutput.compute(
                            biggestAvailableBanknoteValue,
                            (k, v) -> v + 1);
                } else {
                    banknotesForOutput.put(biggestAvailableBanknoteValue, 1);
                }


            } else {
                return Optional.empty();
            }
        }

        if (!banknotesForOutput.isEmpty()) {

            copyBanknotesOfCurrency = cleanZeroCountBanknotes(copyBanknotesOfCurrency);

            if (!copyBanknotesOfCurrency.isEmpty()){
                bank.put(currency, copyBanknotesOfCurrency);
            }
            else{
                bank.remove(currency);
            }

            return Optional.of(
                    banknotesToString(banknotesForOutput));

        }

        return Optional.empty();
    }

    private Map<Integer, Integer> cleanZeroCountBanknotes(Map<Integer, Integer> allBanknotesOfCurrency) {

        Set<Integer> allAvailableBanknotes = allBanknotesOfCurrency.keySet();

        Iterator<Integer> iter = allAvailableBanknotes.iterator();

        while (iter.hasNext()) {

            int valueOfBanknotes = iter.next();

            if (allBanknotesOfCurrency.get(valueOfBanknotes) == 0) {
                allBanknotesOfCurrency.remove(valueOfBanknotes);
            }

        }

        return allBanknotesOfCurrency;
    }

    private Optional<Integer> getBiggestAvailableBanknote(Map<Integer, Integer> allBanknotesOfCurrency, int amount) {

        Set<Integer> allAvailableBanknotes = allBanknotesOfCurrency.keySet();

        int max = Integer.MIN_VALUE;

        Iterator<Integer> iter = allAvailableBanknotes.iterator();

        while (iter.hasNext()) {

            int valueOfBanknotes = iter.next();
            if (max <= valueOfBanknotes && valueOfBanknotes <= amount && allBanknotesOfCurrency.get(valueOfBanknotes) > 0) {
                max = valueOfBanknotes;
            }
        }

        Optional<Integer> returnValue = max != Integer.MIN_VALUE ? Optional.of(max) : Optional.empty();

        return returnValue;
    }

    private String banknotesToString(Map<Integer, Integer> banknotesForOutput) {

        StringBuilder message = new StringBuilder();

        for (Integer value : banknotesForOutput.keySet()) {

            Integer number = banknotesForOutput.get(value);
            String line = String.format("%d %d", value, number);
            message.append(line);
            message.append(Dictionary.NEW_LINE);
        }

        return message.toString();
    }

    @Override
    public String toString() {

        StringBuilder message = new StringBuilder();

        for (String currency : bank.keySet()) {
            Map<Integer, Integer> subBank = bank.get(currency);

            for (Integer value : subBank.keySet()) {
                Integer number = subBank.get(value);
                String line = String.format("%s %d %d", currency, value, number);
                message.append(line);
                message.append(Dictionary.NEW_LINE);
            }
        }

        return message.toString();
    }

    public Map<String, Map<Integer, Integer>> getBank() {

        return bank;
    }

}
