package org.example.application.input;

import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
    private InputSource inputSource = null;
    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public <T> ArrayList<T> read(Class<? extends T> tClass, int length) {
        return inputSource.read(tClass, length, scanner);
    }
}
