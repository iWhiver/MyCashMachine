package com.belorechev.cashmachine.computer.commands;

public abstract class CommandTemplate {

    String identification = null;

    public boolean isSuited(String[] operation) {

        if (identification == null) {
            throw new IllegalStateException("Subclass must change value of variable \"identification\"");
        }

        if (operation != null && operation.length != 0 && operation[0] != null) {
            return operation[0].equals(identification) || operation[0].equals(identification.toUpperCase());
        }

        throw new IllegalArgumentException();
    }

    public abstract String apply(String[] operation);
}
