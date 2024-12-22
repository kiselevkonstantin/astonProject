package org.example.application;

import org.example.application.dataclass.Animal;
import org.example.application.dataclass.Barrel;
import org.example.application.dataclass.Person;
import org.example.application.input.ConsoleInput;
import org.example.application.input.FileInput;
import org.example.application.input.InputReader;
import org.example.application.input.RandomInput;
import org.example.application.utils.TimSort;
import org.example.application.utils.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Class<?> tClass = null;
        ArrayList<?> list = new ArrayList<>();
        while (running) {
            try {
                printStartMenu();
                String choice = scanner.next();
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
                    case 4 -> list = sortList(list, tClass);
                    case 5 -> System.out.println(findElement(list, tClass) ? "Элемент найден" : "Элемент не найден");
                    case 6 -> running = false;
                    default -> System.out.println("Неверный выбор. Попробуйте еще раз.");
                }
                System.out.println(list);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private void printStartMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Заполнить данные (Животные)");
        System.out.println("2. Заполнить данные (Бочки)");
        System.out.println("3. Заполнить данные (Люди)");
        System.out.println("4. Сортировать данные");
        System.out.println("5. Искать элемент");
        System.out.println("6. Выход");
    }

    private void printInputSourceMenu() {
        System.out.println("Заполнить данные:");
        System.out.println("1. Вручную");
        System.out.println("2. Из файла");
        System.out.println("3. Случайным образом");
    }

    private int selectInputSource(Scanner scanner) {
        printInputSourceMenu();
        String inputSource = scanner.next();
        Validation.validateInputSource(inputSource);
        return Integer.parseInt(inputSource);
    }

    private int getLength(Scanner scanner) {
        System.out.println("Укажите количество объектов (целое положительное число):");
        String length = scanner.next();
        Validation.validateLength(length);
        return Integer.parseInt(length);
    }

    private ArrayList<?> getList(Class<?> tClass, Scanner scanner) {
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
        return inputReader.read(tClass, length);
    }

    private ArrayList<?> sortList(ArrayList<?> list, Class<?> tClass) {
        Validation.validateListNotEmpty(list);
        switch (tClass.getSimpleName()) {
            case "Animal" -> {
                Animal[] animals = list.toArray(new Animal[0]);
                new TimSort<Animal>().sort(animals, Animal::compareTo);
                list = new ArrayList<>(Arrays.stream(animals).toList());
            }
            case "Barrel" -> {
                Barrel[] barrels = list.toArray(new Barrel[0]);
                new TimSort<Barrel>().sort(barrels, Barrel::compareTo);
                list = new ArrayList<>(Arrays.stream(barrels).toList());
            }
            case "Person" -> {
                Person[] persons = list.toArray(new Person[0]);
                new TimSort<Person>().sort(persons, Person::compareTo);
                list = new ArrayList<>(Arrays.stream(persons).toList());
            }
            default -> {
            }
        }
        return list;
    }

    private boolean findElement(ArrayList<?> list, Class<?> tClass) {
        Validation.validateListNotEmpty(list);
        return true;
    }
}
