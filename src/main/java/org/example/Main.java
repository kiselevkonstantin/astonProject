package org.example;

import org.example.application.input.*;
import org.example.application.models.Animal;
import org.example.application.models.Barrel;
import org.example.application.models.Person;
import org.example.application.utils.BinSearch;
import org.example.application.utils.TimSort;
import org.example.application.utils.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Class<?> tClass = null;
        ArrayList<?> list = new ArrayList<>();
        while (running) {
            try {
                printStartMenu();
                String choice = scanner.nextLine();
                Validation.validateChoice(choice);

                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        tClass = Animal.class;
                        list = getList(tClass, scanner);
                    }
                    case 2 -> {
                        tClass = Barrel.class;
                        list = getList(tClass, scanner);
                    }
                    case 3 -> {
                        tClass = Person.class;
                        list = getList(tClass, scanner);
                    }
                    case 4 -> sortList(list, tClass);
                    case 5 -> {
                        int index = findElement(list, tClass, scanner);
                        System.out.println(index != -1 ? "Список предварительно отсортирован.\nЭлемент найден под индексом %d".formatted(index) : "Элемент не найден");
                    }
                    case 6 -> printList(list);
                    case 7 -> running = false;
                    default -> System.out.println("Неверный выбор. Попробуйте еще раз.");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printStartMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Заполнить данные (Животные)");
        System.out.println("2. Заполнить данные (Бочки)");
        System.out.println("3. Заполнить данные (Люди)");
        System.out.println("4. Сортировать данные");
        System.out.println("5. Искать элемент");
        System.out.println("6. Печать данных");
        System.out.println("7. Выход");
    }

    private static void printInputSourceMenu() {
        System.out.println("Заполнить данные:");
        System.out.println("1. Вручную");
        System.out.println("2. Из файла");
        System.out.println("3. Случайным образом");
    }

    private static int selectInputSource(Scanner scanner) {
        printInputSourceMenu();
        String inputSource = scanner.nextLine();
        Validation.validateInputSource(inputSource);
        return Integer.parseInt(inputSource);
    }

    private static int getLength(Scanner scanner) {
        System.out.println("Укажите количество объектов (целое положительное число):");
        String length = scanner.nextLine();
        Validation.validateLength(length);
        return Integer.parseInt(length);
    }

    private static ArrayList<?> getList(Class<?> tClass, Scanner scanner) {
        int inputSource = selectInputSource(scanner);
        InputReader inputReader = new InputReader(scanner);
        switch (inputSource) {
            case 1 -> inputReader.setInputSource(new ConsoleInput());
            case 2 -> inputReader.setInputSource(new FileInput());
            case 3 -> inputReader.setInputSource(new RandomInput());
            default -> {
                System.out.println("Неверный выбор. Попробуйте еще раз.");
                return null;
            }
        }
        int length = getLength(scanner);
        return inputReader.executeReading(tClass, length, scanner);
    }

    private static void sortList(ArrayList<?> list, Class<?> tClass) {
        Validation.validateListNotEmpty(list);
        switch (tClass.getSimpleName()) {
            case "Animal" -> TimSort.run((ArrayList<Animal>) list, Animal::compareTo);
            case "Barrel" -> TimSort.run((ArrayList<Barrel>) list, Barrel::compareTo);
            case "Person" -> TimSort.run((ArrayList<Person>) list, Person::compareTo);
            default -> {
            }
        }
    }

    private static int findElement(ArrayList<?> list, Class<?> tClass, Scanner scanner) {
        Validation.validateListNotEmpty(list);
        switch (tClass.getSimpleName()) {
            case "Animal" -> {
                Animal key = ConsoleInput.createAnimal(scanner);
                TimSort.run((ArrayList<Animal>) list, Animal::compareTo);
                return BinSearch.run((ArrayList<Animal>) list, key, Animal::compareTo);
            }
            case "Barrel" -> {
                Barrel key = ConsoleInput.createBarrel(scanner);
                TimSort.run((ArrayList<Barrel>) list, Barrel::compareTo);
                return BinSearch.run((ArrayList<Barrel>) list, key, Barrel::compareTo);
            }
            case "Person" -> {
                Person key = ConsoleInput.createPerson(scanner);
                TimSort.run((ArrayList<Person>) list, Person::compareTo);
                return BinSearch.run((ArrayList<Person>) list, key, Person::compareTo);
            }
        }
        return -1;
    }

    private static void printList(ArrayList<?> list) {
        System.out.println(list == null || list.isEmpty() ? "[]" : list);
    }
}




