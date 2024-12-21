package org.example.application.dataclass;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    private final String sex;
    private final int age;
    private final String surname;

    public Person(Builder builder) {
        this.sex = builder.sex;
        this.age = builder.age;
        this.surname = builder.surname;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex='" + sex + '\'' +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Comparator.comparing(Person::getSex)
                .thenComparing(Person::getAge)
                .thenComparing(Person::getSurname)
                .compare(this, o);
    }

    public static class Builder {
        private String sex;
        private int age;
        private String surname;

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
