package com.belorechev.cashmachine.utility

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
@ConfigurationProperties("my")
final class Dictionary {

    public static final String OK_STATUS = "OK"
    public static final String ERROR_STATUS = "ERROR"
    public static final String EXIT_STATUS = "Bye!"

    public static final String NEW_LINE = "\r\n"
    public static final String HI_STATUS = "Hello! I am cash machine. Let's do it!"

    private static final List<Integer> tempList = new ArrayList<>()
    private static final List<Integer> validBanknotes = Collections.unmodifiableList(tempList)

    private boolean canChangeProperties = true
    private static Dictionary dictionary

    static List<Integer> getValidBanknotes() {
        return validBanknotes
    }

    void setValidBanknotes(List<Integer> banknotesFromYML) {
        if (canChangeProperties) {
            tempList.addAll(banknotesFromYML)
            canChangeProperties = false
        }
    }

    private Dictionary() {
        if (!canChangeProperties) {
            throw new IllegalStateException("Utility class")
        }
    }

    @PostConstruct
    private void init() {
        dictionary = this
    }

}