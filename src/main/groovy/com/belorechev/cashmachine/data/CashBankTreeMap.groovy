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

    void add(String currency, Integer value, Integer number) {

        Map<Integer, Integer> banknotesOfCurrency

        if (bank.containsKey(currency)) {

            banknotesOfCurrency = bank[currency]

            if (banknotesOfCurrency.containsKey(value)) {
                banknotesOfCurrency[value] = banknotesOfCurrency[value] ? banknotesOfCurrency[value] + number : number
            } else {
                banknotesOfCurrency[value] = number
            }

        } else {
            banknotesOfCurrency = new TreeMap<>()
            banknotesOfCurrency[value] = number
        }

        bank[currency] = banknotesOfCurrency
    }

    Set<Cash> get(String currency, int amount) {

        if (!bank.containsKey(currency)) {
            return new TreeSet<>()
        }

        Map<Integer, Integer> copyBanknotes = new TreeMap<>(bank[currency])
        Set<Cash> banknotesForOutput = new TreeSet<>({ s1, s2 ->
            s2 <=> s1
        })

        while (amount) {

            Integer biggestValue = getBiggestAvailableBanknoteValue(amount, copyBanknotes)

            if (biggestValue <= 0) {
                return new TreeSet<>()
            }

            int amountOfBanknotes = copyBanknotes[biggestValue]
            int necessary = (int) (amount / biggestValue)
            int amountOfBanknotesForOperation = necessary <= amountOfBanknotes ? necessary : amountOfBanknotes

            copyBanknotes[biggestValue] = copyBanknotes[biggestValue] ? copyBanknotes[biggestValue] - amountOfBanknotesForOperation : 0
            copyBanknotes.remove(biggestValue, 0)

            amount -= biggestValue * amountOfBanknotesForOperation

            banknotesForOutput << new Cash(currency, biggestValue, amountOfBanknotesForOperation)
        }

        if (!banknotesForOutput.isEmpty()) {
            if (!copyBanknotes.isEmpty()) {
                bank[currency] = copyBanknotes
            } else {
                bank.remove(currency)
            }

            return banknotesForOutput
        }

        return new TreeSet<>()
    }

    private static Integer getBiggestAvailableBanknoteValue(int amount, Map<Integer, Integer> copyBanknotesOfCurrency) {

        Integer biggestAvailableBanknoteValue = 0

        copyBanknotesOfCurrency.each { valueOfBanknotes, amountOfBanknotes ->

            if (biggestAvailableBanknoteValue <= valueOfBanknotes &&
                    valueOfBanknotes <= amount &&
                    copyBanknotesOfCurrency[valueOfBanknotes] > 0) {

                biggestAvailableBanknoteValue = valueOfBanknotes
            }
        }
        return biggestAvailableBanknoteValue
    }

    @Override
    String getPrintForm() {

        StringBuilder message = new StringBuilder()

        bank.each { currency, subBank ->
            subBank.each { value, amountOfNotes ->
                message << "$currency $value $amountOfNotes$Dictionary.NEW_LINE"
            }
        }

        return message
    }

    Map<String, Map<Integer, Integer>> getBank() {
        return bank
    }
}
