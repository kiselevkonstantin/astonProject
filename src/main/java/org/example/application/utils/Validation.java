package org.example.application.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Validation {
    public static void validateLength(String length) throws RuntimeException {
        try {
            if (Integer.parseInt(length) <= 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException _) {
            throw new RuntimeException("Неверное количество объектов (целое положительное число).");
        }
    }

    public static void validateChoice(String choice) throws RuntimeException {
        try {
            Integer.parseInt(choice);
        } catch (RuntimeException _) {
            throw new RuntimeException("Неверный выбор. Попробуйте еще раз.");
        }
    }

    public static void validateAge(int age) throws RuntimeException {
        try {
            if (age <= 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException _) {
            throw new RuntimeException("Неверный возраст (целое положительное число).");
        }
    }

    public static void validateSex(String sex) throws RuntimeException {
        if (!sex.equals("м") && !sex.equals("ж")) {
            throw new RuntimeException("Неверный пол (м/ж)");
        }
    }

    public static void validateVolume(double volume) throws RuntimeException {
        try {
            if (volume < 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException _) {
            throw new RuntimeException("Неверный объем бочки (целое число)");
        }
    }

    public static void validatePropertyLength(int length) throws RuntimeException {
        if (length != 3) {
            throw new RuntimeException("Неверное количество свойств.");
        }
    }

    public static void validateBooleanProperty(String value) throws RuntimeException {
        if (!value.equals("true") && !value.equals("false")) {
            throw new RuntimeException("Неверное указано значение свойста Шерсть (true/false).");
        }
    }

    public static void validateContentFileSize(int contentFileSize, int expectedSize) throws RuntimeException {
        if (contentFileSize < expectedSize) {
            throw new RuntimeException("Недостаточно объектов в файле: %d, ожидается %d".formatted(contentFileSize, expectedSize));
        }
    }

    public static void validateFilePath(String filePath) throws RuntimeException {
        if (!Files.exists(Path.of(filePath))) {
            throw new RuntimeException("Файл не найден.");
        }
    }

    public static void validateListNotEmpty(List<?> list) throws RuntimeException {
        if (list.isEmpty()) {
            throw new RuntimeException("Невозможно выполнить сейчас. Список пуст.");
        }
    }

    public static void validateInputSource(String inputSource) throws RuntimeException {
        try {
            Integer.parseInt(inputSource);
        } catch (RuntimeException _) {
            throw new RuntimeException("Неверный выбор. Попробуйте еще раз.");
        }
    }

    public Validation() {
    }
}
