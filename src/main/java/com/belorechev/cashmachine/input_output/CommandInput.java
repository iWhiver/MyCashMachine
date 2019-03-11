package com.belorechev.cashmachine.input_output;

import java.io.IOException;

public interface CommandInput {

    String getNext() throws IOException;

}
