package org.example.application.input;

import java.util.ArrayList;
import java.util.Scanner;

public interface InputSource {
    public <T>ArrayList <T> read (Class<? extends T> someClass, int length, Scanner scanner);
}
