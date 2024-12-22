package org.example.application.input;

import org.example.application.dataclass.Animal;
import org.example.application.dataclass.Barrel;
import org.example.application.dataclass.Person;
import org.example.application.utils.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInput implements InputSource {
    @Override
    public <T> ArrayList<T> read(Class<? extends T> tClass, int length, Scanner scanner) {
        ArrayList<T> list = new ArrayList<>(length);
        try {
            switch (tClass.getSimpleName()) {
                case "Animal" -> {
                    for (int i = 0; i < length; i++) {
                        list.add(tClass.cast(readAnimal(scanner)));
                    }
                }
                case "Barrel" -> {
                    for (int i = 0; i < length; i++) {
                        list.add(tClass.cast(readBarrel(scanner)));
                    }
                }
                case "Person" -> {
                    for (int i = 0; i < length; i++) {
                        list.add(tClass.cast(readPerson(scanner)));
                    }
                }
                default -> {
                    return null;
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static Animal readAnimal(Scanner scanner) {
        System.out.println("Укажите вид животного:");
        String kind = scanner.nextLine();
        System.out.println("Укажите цвет глаз:");
        String eyeColor = scanner.nextLine();
        System.out.println("Есть шерсть? (true/false)");
        String hasFur = scanner.nextLine();
        Validation.validateBooleanProperty(hasFur);
        return new Animal.Builder()
                .kind(kind)
                .eyeColor(eyeColor)
                .hasFur(Boolean.parseBoolean(hasFur))
                .build();
    }

    public static Barrel readBarrel(Scanner scanner) {
        System.out.println("Укажите объем бочки (целое число):");
        String volume = scanner.nextLine();
        Validation.validateVolume(volume);
        System.out.println("Укажите хранимый материал:");
        String content = scanner.nextLine();
        System.out.println("Укажите материал бочки:");
        String material = scanner.nextLine();
        return new Barrel.Builder()
                .volume(Integer.parseInt(volume))
                .content(content)
                .material(material)
                .build();
    }

    public static Person readPerson(Scanner scanner) {
        System.out.println("Укажите пол (м/ж):");
        String sex = scanner.nextLine();
        System.out.println("Укажите возраст (целое положительное число):");
        String age = scanner.nextLine();
        Validation.validateAge(age);
        System.out.println("Укажите фамилию:");
        String surname = scanner.nextLine();
        return new Person.Builder()
                .sex(sex)
                .age(Integer.parseInt(age))
                .surname(surname)
                .build();
    }
}
