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
        List<String> content = readFileContent(scanner);
        Validation.validateContentFileSize(content.size(), length);
        switch (someClass.getSimpleName()) {
            case "Animal" -> {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createAnimal(content.get(i))));
                }
            }
            case "Person" -> {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createPerson(content.get(i))));
                }
            }
            case "Barrel" -> {
                for (int i = 0; i < length; i++) {
                    listObjects.add(someClass.cast(createBarrel(content.get(i))));
                }
            }
            default -> {
                return null;
            }
        }
        return listObjects;
    }

    private List<String> readFileContent(Scanner scanner) {
        System.out.println("Укажите путь к файлу: ");
        String filePath = scanner.nextLine();
        Validation.validateFilePath(filePath);
        Path path = Path.of(filePath);
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(path);
        } catch (IOException _) {
        }
        return content;
    }

    private Animal createAnimal(String data) {
        String[] values = data.split(",");
        Validation.validatePropertyLength(values.length);
        String type = values[0];
        String eyeColor = values[1];
        String hasFur = values[2];
        Validation.validateBooleanProperty(hasFur);
        return new Animal.Builder()
                .setType(type)
                .setEyeColor(eyeColor)
                .setFur(Boolean.parseBoolean(hasFur))
                .build();
    }

    private Person createPerson(String data) {
        String[] values = data.split(",");
        Validation.validatePropertyLength(values.length);
        String gender = values[0];
        Validation.validateGender(gender);
        String age = values[1];
        Validation.validateAge(age);
        String lastName = values[2];
        Validation.validateLastName(lastName);
        return new Person.Builder()
                .setGender(gender)
                .setAge(Integer.parseInt(age))
                .setLastName(lastName)
                .build();
    }

    private Barrel createBarrel(String data) {
        String[] values = data.split(",");
        Validation.validatePropertyLength(values.length);
        String volume = values[0];
        Validation.validateVolume(volume);
        String content = values[1];
        String material = values[2];
        return new Barrel.Builder()
                .setVolume(Double.parseDouble(volume))
                .setContent(content)
                .setMaterial(material)
                .build();
    }
}



