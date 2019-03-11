package com.belorechev.cashmachine.utility;

public final class Dictionary {

    public static final String OK_STATUS = "OK";
    public static final String ERROR_STATUS = "ERROR";
    public static final String EXIT_STATUS = "Bye!";

    //TODO change value before initialization
    //public static final String NEW_LINE = "" + Integer.MAX_VALUE;

    public static String NEW_LINE = "\r\n";

    public static final String HI_STATUS = NEW_LINE + NEW_LINE + NEW_LINE + "Hello! I am cash machine. Let's do it!";

    public static final Integer[] VALID_BANKNOTES = {1, 10, 100, 1000, 5, 50, 500, 5000};

    private Dictionary(){
        throw new IllegalStateException("Utility class");
    }
}
