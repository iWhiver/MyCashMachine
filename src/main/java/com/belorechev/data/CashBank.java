package com.belorechev.data;

import com.belorechev.utility.Dictionary;

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
                banknotesOfCurrency.compute(value,  (k, v) ->  v + number);
            } else {
                banknotesOfCurrency.put(value, number);
            }

        } else {
            banknotesOfCurrency = new TreeMap<>();
            banknotesOfCurrency.put(value, number);
        }

        bank.put(currency, banknotesOfCurrency);
    }



    //TODO Test
    //TODO take banknotes with packs without each banknotes

    public Optional<String> get(String currency, int amount) {

        if (!bank.containsKey(currency)) {
            return Optional.empty();
        }

        Map<Integer, Integer> realBanknotesOfCurrency = bank.get(currency);

        Map<Integer, Integer> copyBanknotesOfCurrency = new TreeMap<>(realBanknotesOfCurrency);

        Map<Integer, Integer> banknotesForOutput = new TreeMap<>(Collections.reverseOrder());

        while (amount != 0) {

            //TODO - put function to this method?
            Optional<Integer> biggestAvailableBanknote = getBiggestAvailableBanknote(copyBanknotesOfCurrency, amount);

            if (biggestAvailableBanknote.isPresent()) {

                //TODO - take while more when one banknote
                Integer biggestAvailableBanknoteValue = biggestAvailableBanknote.get();
                copyBanknotesOfCurrency.compute(
                        biggestAvailableBanknoteValue,
                        (k, v) -> v - 1 );

                copyBanknotesOfCurrency.remove(biggestAvailableBanknoteValue, 0);

                amount -= biggestAvailableBanknoteValue;

                banknotesForOutput.computeIfPresent(biggestAvailableBanknoteValue,
                        (k, v) ->  v + 1 );

                banknotesForOutput.putIfAbsent(biggestAvailableBanknoteValue, 1);

            } else {
                return Optional.empty();
            }
        }

        if (!banknotesForOutput.isEmpty()) {

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

    //TODO return count of banknotes
    private Optional<Integer> getBiggestAvailableBanknote(Map<Integer, Integer> allBanknotesOfCurrency, int amount) {

        Set<Integer> allAvailableBanknotes = allBanknotesOfCurrency.keySet();

        int max = 0;

        for(Integer valueOfBanknotes : allAvailableBanknotes){

            if (max <= valueOfBanknotes &&
                    valueOfBanknotes <= amount &&
                    allBanknotesOfCurrency.get(valueOfBanknotes) > 0) {

                max = valueOfBanknotes;
            }
        }

        return max != 0 ? Optional.of(max) : Optional.empty();
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
    public String toString() {

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

    public Map<String, Map<Integer, Integer>> getBank() {

        return bank;
    }

}
