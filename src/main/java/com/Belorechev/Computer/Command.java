package com.Belorechev.Computer.Commands;

public interface Command {

    String apply (String[] operation) throws NumberFormatException;
}
