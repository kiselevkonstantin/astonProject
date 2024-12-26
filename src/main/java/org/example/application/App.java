package org.example.application;

import org.example.application.input.ConsoleInput;
import org.example.application.input.FileInput;
import org.example.application.input.InputReader;
import org.example.application.input.RandomInput;
import org.example.application.models.Animal;
import org.example.application.models.Barrel;
import org.example.application.models.Person;
import org.example.application.output.OutputWriter;
import org.example.application.utils.BinSearch;
import org.example.application.utils.TimSort;
import org.example.application.utils.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static final String LOG_FILENAME = "log.txt";

    public void run() {
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
                    case 4 -> {
                        Validation.validateListNotEmpty(list);
                        sortList(list, tClass);
                        OutputWriter.append(LOG_FILENAME, list.toString());
                    }
                    case 5 -> {
                        Validation.validateListNotEmpty(list);
                        Object element = getElement(tClass, scanner);
                        int index = findElement(list, tClass, element);
                        String result = index == -1 ?
                                "В отсортированных данных элемент %s не найден".formatted(element) :
                                "В отсортированных данных элемент %s найден под индексом %d".formatted(element, index);
                        System.out.println(result);
                        OutputWriter.append(LOG_FILENAME, result);
                    }
                    case 6 -> {
                        Validation.validateListNotEmpty(list);
                        printList(list);
                    }
                    case 7 -> running = false;
                    default -> System.out.println("Неверный выбор. Попробуйте еще раз.");
                }
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
        System.out.println("6. Печать данных");
        System.out.println("7. Выход");
    }

    private void printInputSourceMenu() {
        System.out.println("Заполнить данные:");
        System.out.println("1. Вручную");
        System.out.println("2. Из файла");
        System.out.println("3. Случайным образом");
    }

    private int selectInputSource(Scanner scanner) {
        printInputSourceMenu();
        String inputSource = scanner.nextLine();
        Validation.validateInputSource(inputSource);
        return Integer.parseInt(inputSource);
    }

    private int getLength(Scanner scanner) {
        System.out.println("Укажите количество объектов (целое положительное число):");
        String length = scanner.nextLine();
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

    private void sortList(ArrayList<?> list, Class<?> tClass) {
        switch (tClass.getSimpleName()) {
            case "Animal" -> TimSort.run((ArrayList<Animal>) list, Animal::compareTo);
            case "Barrel" -> TimSort.run((ArrayList<Barrel>) list, Barrel::compareTo);
            case "Person" -> TimSort.run((ArrayList<Person>) list, Person::compareTo);
            default -> {
            }
        }
    }

    private Object getElement(Class<?> tClass, Scanner scanner) {
        Object element = null;
        switch (tClass.getSimpleName()) {
            case "Animal" -> element = ConsoleInput.createAnimal(scanner);
            case "Barrel" -> element = ConsoleInput.createBarrel(scanner);
            case "Person" -> element = ConsoleInput.createPerson(scanner);
        }
        return element;
    }

    private int findElement(ArrayList<?> list, Class<?> tClass, Object key) {
        switch (tClass.getSimpleName()) {
            case "Animal" -> {
                TimSort.run((ArrayList<Animal>) list, Animal::compareTo);
                return BinSearch.run((ArrayList<Animal>) list, (Animal) key, Animal::compareTo);
            }
            case "Barrel" -> {
                TimSort.run((ArrayList<Barrel>) list, Barrel::compareTo);
                return BinSearch.run((ArrayList<Barrel>) list, (Barrel) key, Barrel::compareTo);
            }
            case "Person" -> {
                TimSort.run((ArrayList<Person>) list, Person::compareTo);
                return BinSearch.run((ArrayList<Person>) list, (Person) key, Person::compareTo);
            }
        }
        return -1;
    }

    private void printList(ArrayList<?> list) {
        System.out.println(list == null || list.isEmpty() ? "[]" : list);
    }
}
