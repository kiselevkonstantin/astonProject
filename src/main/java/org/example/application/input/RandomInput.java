package org.example.application.input;

import com.github.javafaker.Faker;
import org.example.application.models.Animal;
import org.example.application.models.Barrel;
import org.example.application.models.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class RandomInput implements InputSource {
    @Override
    public <T> ArrayList<T> read(Class<? extends T> someClass, int length, Scanner scanner) {
        Faker faker = new Faker();
        ArrayList<T> listObjects = new ArrayList<>();

        if (someClass.getSimpleName().equals("Animal")) {
            for (int i = 0; i < length; i++) {
                listObjects
                        .add(someClass.cast(createAnimal(faker)));
            }
        } else if (someClass.getSimpleName().equals("Person")) {
            for (int i = 0; i < length; i++) {
                listObjects
                        .add(someClass.cast(createPerson(faker)));
            }
        } else if (someClass.getSimpleName().equals("Barrel")) {
            for (int i = 0; i < length; i++) {
                listObjects
                        .add(someClass.cast(createBarrel(faker)));
            }
        } else {
            return null;
        }

        return listObjects;
    }

    private Animal createAnimal(Faker faker){
        return new Animal.AnimalBuilder()
                .setType(faker.animal().name())
                .setEyeColor(faker.color().name())
                .setFur(faker.random().nextBoolean())
                .build();
    }

    private Person createPerson(Faker faker) {
        return new Person.PersonBuilder()
                .setGender(faker.regexify("м|ж"))
                .setAge(faker.random().nextInt(10, 100))
                .setLastName(faker.name().lastName())
                .build();
    }
    private Barrel createBarrel(Faker faker) {
        return new Barrel.BarrelBuilder()
                .setVolume(Double.valueOf(faker.random().nextInt(100, 1000)))
                .setContent(faker.commerce().productName())
                .setMaterial(faker.commerce().material())
                .build();
    }

}
