package com.belorechev.cashmachine.computer.commands;

interface CommandTestTemplate {

    default String[] asArray(String... operation) {
        return operation;
    }
}
