package org.example.application.input;

import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
    private InputSource inputType = null;
    private final Scanner scanner;

    public InputReader (Scanner scanner) {
        this.scanner = scanner;
    }

    public void setInputSource(InputSource inputType){
        this.inputType = inputType;
    }
    public <T> ArrayList<T> executeReading(Class<? extends T> someClass, int length, Scanner scanner){
        return inputType.read(someClass, length, scanner);
    }
}
