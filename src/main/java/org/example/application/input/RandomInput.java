package org.example.application.input;

import com.github.javafaker.Faker;
import org.example.application.dataclass.Animal;
import org.example.application.dataclass.Barrel;
import org.example.application.dataclass.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class RandomInput implements InputSource {
    @Override
    public <T> ArrayList<T> read(Class<? extends T> tClass, int length, Scanner scanner) {
        Faker faker = new Faker();
        ArrayList<T> list = new ArrayList<>();
        switch (tClass.getSimpleName()) {
            case "Animal" -> {
                for (int i = 0; i < length; i++) {
                    list.add(tClass.cast(readAnimal(faker)));
                }
            }
            case "Barrel" -> {
                for (int i = 0; i < length; i++) {
                    list.add(tClass.cast(readBarrel(faker)));
                }
            }
            case "Person" -> {
                for (int i = 0; i < length; i++) {
                    list.add(tClass.cast(readPerson(faker)));
                }
            }
            default -> {
                return null;
            }
        }
        return list;
    }

    private Animal readAnimal(Faker faker) {
        return new Animal.Builder()
                .kind(faker.animal().name())
                .eyeColor(faker.color().name())
                .hasFur(faker.random().nextBoolean())
                .build();
    }

    private Barrel readBarrel(Faker faker) {
        return new Barrel.Builder()
                .volume(faker.random().nextInt(100, 1000))
                .content(faker.commerce().productName())
                .material(faker.commerce().material())
                .build();
    }

    private Person readPerson(Faker faker) {
        return new Person.Builder()
                .sex(faker.regexify("м|ж"))
                .age(faker.random().nextInt(10, 100))
                .surname(faker.name().lastName())
                .build();
    }
}
