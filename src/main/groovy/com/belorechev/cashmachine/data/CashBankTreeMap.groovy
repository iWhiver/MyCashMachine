package com.belorechev.cashmachine.data

import com.belorechev.cashmachine.utility.Dictionary
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("TreeMap")
class CashBankTreeMap implements CashBank {

    private final Map<String, Map<Integer, Integer>> bank

    CashBankTreeMap() {

        bank = new TreeMap<>()
    }

    void add(String currency, Integer value, final Integer number) {

        Map<Integer, Integer> banknotesOfCurrency

        if (bank.containsKey(currency)) {

            banknotesOfCurrency = bank.get(currency)

            if (banknotesOfCurrency.containsKey(value)) {
                //banknotesOfCurrency.compute(value, (k, v) -> v == null ? number : v + number)
                //banknotesOfCurrency[value] = banknotesOfCurrency.value + number ?: number
                //banknotesOfCurrency.compute(value, (k, v) => v == null ? number : v + number)
                banknotesOfCurrency.put(value, banknotesOfCurrency.get(value) ? banknotesOfCurrency.get(value) + number : number)
            } else {
                banknotesOfCurrency.put(value, number)
            }

        } else {
            banknotesOfCurrency = new TreeMap<>()
            banknotesOfCurrency.put(value, number)
        }

        bank.put(currency, banknotesOfCurrency)
    }

    Set<Cash> get(String currency, int amount) {

        Set<Cash> emptySet = new TreeSet<>()

        if (!bank.containsKey(currency)) {
            return emptySet
        }

        Map<Integer, Integer> realBanknotesOfCurrency = bank.get(currency)
        Map<Integer, Integer> copyBanknotesOfCurrency = new TreeMap<>(realBanknotesOfCurrency)
        def banknotesForOutput = new TreeSet<>({ s1, s2 ->
            s2 <=> s1
        })

        while (amount != 0) {

            Integer biggestAvailableBanknoteValue = getBiggestAvailableBanknoteValue(amount, copyBanknotesOfCurrency)

            if (biggestAvailableBanknoteValue == 0) {
                return emptySet
            }

            int amountOfBanknotes = copyBanknotesOfCurrency.get(biggestAvailableBanknoteValue)
            int necessary = (int) (amount / biggestAvailableBanknoteValue)
            final int amountOfBanknotesOperation = necessary <= amountOfBanknotes ? necessary : amountOfBanknotes

            copyBanknotesOfCurrency.put(biggestAvailableBanknoteValue, copyBanknotesOfCurrency.get(biggestAvailableBanknoteValue) ? copyBanknotesOfCurrency.get(biggestAvailableBanknoteValue) - amountOfBanknotesOperation : 0)

            copyBanknotesOfCurrency.remove(biggestAvailableBanknoteValue, 0)

            amount -= biggestAvailableBanknoteValue * amountOfBanknotesOperation

            banknotesForOutput.add(new Cash(currency, biggestAvailableBanknoteValue, amountOfBanknotesOperation))
        }

        if (!banknotesForOutput.isEmpty()) {

            if (!copyBanknotesOfCurrency.isEmpty()) {
                bank.put(currency, copyBanknotesOfCurrency)
            } else {
                bank.remove(currency)
            }

            return banknotesForOutput
        }

        return emptySet
    }

    private static Integer getBiggestAvailableBanknoteValue(int amount, Map<Integer, Integer> copyBanknotesOfCurrency) {

        Set<Integer> allAvailableBanknotes = copyBanknotesOfCurrency.keySet()

        Integer biggestAvailableBanknoteValue = 0

        for (Integer valueOfBanknotes : allAvailableBanknotes) {

            if (biggestAvailableBanknoteValue <= valueOfBanknotes &&
                    valueOfBanknotes <= amount &&
                    copyBanknotesOfCurrency.get(valueOfBanknotes) > 0) {

                biggestAvailableBanknoteValue = valueOfBanknotes
            }
        }
        return biggestAvailableBanknoteValue
    }

    @Override
    String getPrintForm() {

        StringBuilder message = new StringBuilder()

        for (Map.Entry<String, Map<Integer, Integer>> bankEntry : bank.entrySet()) {

            String currency = bankEntry.getKey()
            Map<Integer, Integer> subBank = bankEntry.getValue()

            for (Map.Entry<Integer, Integer> currencyEntry : subBank.entrySet()) {

                Integer value = currencyEntry.getKey()
                Integer number = currencyEntry.getValue()

                String line = String.format("%s %d %d", currency, value, number)
                message.append(line)
                message.append(Dictionary.NEW_LINE)
            }
        }

        return message.toString()
    }

    Map<String, Map<Integer, Integer>> getBank() {
        return bank
    }

}
