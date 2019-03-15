package com.belorechev.cashmachine.computer.commands;

public interface CommandTemplate {

    boolean isSuited(String[] operation);

    String apply(String[] operation);
}
