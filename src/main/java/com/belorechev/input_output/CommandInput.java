package com.belorechev.input_output;

import java.io.IOException;

public interface CommandInput {

    String getNext() throws IOException;
}
