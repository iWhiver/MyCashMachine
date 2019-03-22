package com.belorechev.cashmachine.computer.commands

abstract class CommandTemplate {

    String identification = null

    boolean isSuited(String[] operation) {

        if (identification == null) {
            throw new IllegalStateException("Subclass must change value of variable \"identification\"")
        }

        if (operation != null && operation.length != 0 && operation[0] != null) {
            return operation[0] == identification || operation[0] == identification.toUpperCase()
        }

        throw new IllegalArgumentException()
    }

    abstract String apply(String[] operation);
}
