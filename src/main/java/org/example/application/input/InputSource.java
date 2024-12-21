package org.example.application.input;

import java.util.ArrayList;
import java.util.Scanner;

public interface InputSource {
    <T> ArrayList<T> read(Class<? extends T> tClass, int length, Scanner scanner);
}
