package org.example.application.input;

import org.example.application.models.Animal;
import org.example.application.models.Barrel;
import org.example.application.models.Person;
import org.example.application.utils.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInput implements InputSource {
    @Override
    public <T> ArrayList<T> read(Class<? extends T> someClass, int length, Scanner scanner) {
        ArrayList<T> listObjects = new ArrayList<>();

        switch (someClass.getSimpleName()) {
            case "Animal" -> {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createAnimal(scanner)));
                }
            }
            case "Person" -> {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createPerson(scanner)));
                }
            }
            case "Barrel" -> {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createBarrel(scanner)));
                }
            }
            default -> {
                return null;
            }
        }
        return listObjects;
    }

    public static Animal createAnimal(Scanner scanner) {
        System.out.println("Вид животного: ");
        String type = scanner.nextLine();
        System.out.println("Цвет глаз: ");
        String eyeColor = scanner.nextLine();
        System.out.println("Есть шерсть? (true/false): ");
        String hasFur = scanner.nextLine();
        Validation.validateBooleanProperty(hasFur);
        return new Animal.AnimalBuilder()
                .setType(type)
                .setEyeColor(eyeColor)
                .setFur(Boolean.parseBoolean(hasFur))
                .build();
    }

    public static Person createPerson(Scanner scanner) {
        System.out.println("Фамилия: ");
        String lastName = scanner.nextLine();
        Validation.validateName(lastName);
        System.out.println("Возраст: ");
        String age = scanner.nextLine();
        Validation.validateAge(age);
        System.out.println("Пол: ");
        String gender = scanner.nextLine();
        Validation.validateSex(gender);
        return new Person.PersonBuilder()
                .setGender(gender)
                .setAge(Integer.parseInt(age))
                .setLastName(lastName)
                .build();
    }

    public static Barrel createBarrel(Scanner scanner) {
        System.out.println("Введите объём: ");
        double volume = Double.parseDouble(scanner.nextLine());
        Validation.validateVolume(volume);
        System.out.println("Что внутри?: ");
        String content = scanner.nextLine();
        System.out.println("Какой материал бочки?: ");
        String material = scanner.nextLine();
        return new Barrel.BarrelBuilder()
                .setVolume(volume)
                .setContent(content)
                .setMaterial(material)
                .build();
    }
}
