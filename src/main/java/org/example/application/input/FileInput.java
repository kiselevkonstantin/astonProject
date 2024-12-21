package org.example.application.input;

import org.example.application.dataclass.Animal;
import org.example.application.dataclass.Barrel;
import org.example.application.dataclass.Person;
import org.example.application.utils.Validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput implements InputSource {
    @Override
    public <T> ArrayList<T> read(Class<? extends T> tClass, int length, Scanner scanner) {
        ArrayList<T> list = new ArrayList<>();
        try {
            List<String> fileContent = getFileContent(scanner);
            Validation.validateContentFileSize(fileContent.size(), length);
            switch (tClass.getSimpleName()) {
                case "Animal" -> {
                    for (int i = 0; i < length; i++) {
                        list.add(tClass.cast(readAnimal(fileContent.get(i))));
                    }
                }
                case "Barrel" -> {
                    for (int i = 0; i < length; i++) {
                        list.add(tClass.cast(readBarrel(fileContent.get(i))));
                    }
                }
                case "Person" -> {
                    for (int i = 0; i < length; i++) {
                        list.add(tClass.cast(readPerson(fileContent.get(i))));
                    }
                }
                default -> {
                    return null;
                }
            }
        } catch (RuntimeException e) {
            System.out.printf("Ошибка чтения файла. %s%n", e.getMessage());
        }
        return list;
    }

    private List<String> getFileContent(Scanner scanner) {
        System.out.println("Укажите полное имя файла: ");
        String filePath = scanner.next();
        Validation.validateFilePath(filePath);
        Path path = Path.of(filePath);
        List<String> content = null;
        try {
            content = Files.readAllLines(path);
        } catch (IOException _) {
        }
        return content;
    }

    private Animal readAnimal(String line) {
        line = line.trim();
        String[] split = line.split(",");
        Validation.validatePropertyLength(split.length);
        String kind = split[0];
        String eyeColor = split[1];
        String hasFur = split[2];
        Validation.validateBooleanProperty(hasFur);
        return new Animal.Builder()
                .kind(kind)
                .eyeColor(eyeColor)
                .hasFur(Boolean.parseBoolean(hasFur))
                .build();
    }

    private Barrel readBarrel(String line) {
        line = line.trim();
        String[] split = line.split(",");
        Validation.validatePropertyLength(split.length);
        String volume = split[0];
        Validation.validateVolume(volume);
        String content = split[1];
        String material = split[2];
        return new Barrel.Builder()
                .volume(Integer.parseInt(volume))
                .content(content)
                .material(material)
                .build();
    }

    private Person readPerson(String line) {
        line = line.trim();
        String[] split = line.split(",");
        Validation.validatePropertyLength(split.length);
        String sex = split[0];
        Validation.validateSex(sex);
        String age = split[1];
        Validation.validateAge(age);
        String surname = split[2];
        return new Person.Builder()
                .sex(sex)
                .age(Integer.parseInt(age))
                .surname(surname)
                .build();
    }
}
