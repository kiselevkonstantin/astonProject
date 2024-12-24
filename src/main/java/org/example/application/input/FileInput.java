package org.example.application.input;

import org.example.application.models.Animal;
import org.example.application.models.Barrel;
import org.example.application.models.Person;
import org.example.application.utils.Validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput implements InputSource {
    @Override
    public <T> ArrayList<T> read(Class<? extends T> someClass, int length, Scanner scanner) {
        ArrayList<T> listObjects = new ArrayList<>();

        try {
            List<String> content = readFileContent(scanner);
            Validation.validateContentFileSize(content.size(), length);
            if (someClass.getSimpleName().equals("Animal")) {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createAnimal(content.get(i))));
                }
            } else if (someClass.getSimpleName().equals("Person")) {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createPerson(content.get(i))));
                }
            } else if (someClass.getSimpleName().equals("Barrel")) {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createBarrel(content.get(i))));
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listObjects;
    }

    private List<String> readFileContent(Scanner scanner) throws IOException {
        System.out.println("Укажите путь к файлу: ");
        String filePath = scanner.nextLine();
        Path path = Path.of(filePath);
        Validation.validateFilePath(filePath);
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.printf("Ошибка чтения файла. %s%n", e.getMessage());
        }

        return content;
    }

    private Animal createAnimal(String str) {
        str = str.trim();
        String[] values = str.split(",");
        Validation.validatePropertyLength(values.length);
        String type = values[0];
        String eyeColor = values[1];
        boolean hasFur = Boolean.parseBoolean(values[2]);
        Validation.validateBooleanProperty(String.valueOf(hasFur));
        return new Animal.AnimalBuilder()
                .setType(type)
                .setEyeColor(eyeColor)
                .setFur(hasFur)
                .build();
    }

    private Person createPerson(String str) {
        str = str.trim();
        String[] values = str.split(",");
        Validation.validatePropertyLength(values.length);
        String gender = values[0];
        Validation.validateSex(gender);
        int age = Integer.parseInt(values[1]);
        Validation.validateAge(age);
        String lastName = values[2];
        return new Person.PersonBuilder()
                .setGender(gender)
                .setAge(age)
                .setLastName(lastName)
                .build();
    }

    private Barrel createBarrel(String str) {
        str = str.trim();
        String[] values = str.split(",");
        Validation.validatePropertyLength(values.length);
        double volume = Double.parseDouble(values[0]);
        Validation.validateVolume(volume);
        String content = values[1];
        String material = values[2];
        return new Barrel.BarrelBuilder()
                .setVolume(volume)
                .setContent(content)
                .setMaterial(material)
                .build();
    }

}

