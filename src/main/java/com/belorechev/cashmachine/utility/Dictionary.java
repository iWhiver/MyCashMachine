package com.belorechev.cashmachine.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@ConfigurationProperties("my")
public final class Dictionary {

    public static final String OK_STATUS = "OK";
    public static final String ERROR_STATUS = "ERROR";
    public static final String EXIT_STATUS = "Bye!";

    public static final String NEW_LINE = "\r\n";
    public static final String HI_STATUS = "Hello! I am cash machine. Let's do it!";

    private static final List<Integer> tempList = new ArrayList<>();
    public static final List<Integer> VALID_BANKNOTES = Collections.unmodifiableList(tempList);

    private boolean canChangeProperties = true;

    public void setValidBanknotes(List<Integer> emailInput) {

        if (canChangeProperties) {
            tempList.addAll(emailInput);
            canChangeProperties = false;
        }
    }

    private Dictionary() {
        if (!canChangeProperties) {
            throw new IllegalStateException("Utility class");
        }
    }

}
