package com.belorechev.cashmachine.data

import com.belorechev.cashmachine.Currency
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Value

@Transactional
class CashBankGORMService implements CashBank {

    @Value('${dictionary.NEW_LINE}')
    String NEW_LINE

    @Override
    void add(String currency, Integer value, Integer number) {

        String sValue = Integer.toString(value)

        Currency currencyEntity = Currency.findByVCurrency(currency)

        Map banknotes = [:]

        if (currencyEntity) {
            banknotes.putAll(currencyEntity.VBanknotes)

            if (banknotes.containsKey(value as String)) {
                number += banknotes[sValue] as Integer
            }

            banknotes[sValue] = number as String
            currencyEntity.VBanknotes = banknotes

        } else {
            banknotes[sValue] = number as String
            currencyEntity = new Currency(vCurrency: currency, VBanknotes: banknotes)
        }

        currencyEntity.save()
    }

    Set<Cash> get(String currency, int amount) {

        if (!Currency.all.any { it.vCurrency == currency }) {
            return new TreeSet<>()
        }
        Map<String, String> copyBanknotes = new TreeMap<>(Currency.findByVCurrency(currency).VBanknotes)

        Set<Cash> banknotesForOutput = new TreeSet<>({ s1, s2 ->
            s2 <=> s1
        })

        while (amount) {

            String biggestValue = getBiggestAvailableBanknoteValue(amount, copyBanknotes)

            if ((biggestValue as Integer) <= 0) {
                return new TreeSet<>()
            }

            int amountOfBanknotes = copyBanknotes[biggestValue] as Integer
            int necessary = (int) (amount / (biggestValue as Integer))
            int amountOfBanknotesForOperation = necessary <= amountOfBanknotes ? necessary : amountOfBanknotes

            copyBanknotes[biggestValue] = copyBanknotes[biggestValue] ? (((copyBanknotes[biggestValue] as Integer) - amountOfBanknotesForOperation) as String) : '0'
            copyBanknotes.remove(biggestValue, '0')

            amount -= (biggestValue as Integer) * amountOfBanknotesForOperation

            banknotesForOutput << new Cash(currency, biggestValue as Integer, amountOfBanknotesForOperation)
        }

        if (!copyBanknotes.isEmpty()) {
            Currency.findByVCurrency(currency).setVBanknotes(copyBanknotes)
        } else {
            Currency.findByVCurrency(currency).delete()
        }

        return banknotesForOutput
    }

    private static String getBiggestAvailableBanknoteValue(int amount, Map<String, String> copyBanknotesOfCurrency) {

        Integer biggestValue = 0

        copyBanknotesOfCurrency.each { String valueOfBanknotesS, String amountOfBanknotesS ->

            Integer valueOfBanknotes = valueOfBanknotesS as Integer

            if (biggestValue <= valueOfBanknotes &&
                    valueOfBanknotes <= amount &&
                    (copyBanknotesOfCurrency[valueOfBanknotesS] as Integer) > 0) {

                biggestValue = valueOfBanknotes
            }
        }
        biggestValue as String
    }

    @Override
    String getPrintForm() {

        StringBuilder message = new StringBuilder()

        for (currency in Currency.all.sort()) {
            Map banknotes = new TreeMap({ String k1, String k2 ->
                (k1 as Integer) <=> (k2 as Integer)
            })
            banknotes.putAll(currency.VBanknotes)
            for (Map.Entry banknote in banknotes) {
                message << "$currency.vCurrency $banknote.key $banknote.value$NEW_LINE"
            }
        }

        return message
    }
}
