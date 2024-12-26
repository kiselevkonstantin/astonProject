package org.example.application.models;

public class Person implements Comparable<Person> {
    private final String gender; // Пол человека
    private final int age; // Возраст человека
    private final String lastName; // Фамилия

    //Геттеры для получения информации по полям
    public String getGendere() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    //Корнструктор класса Person для использования Builder
    private Person(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.lastName = builder.lastName;
    }

    // Вложенный статический класс Builder
    public static class Builder {
        private String gender;
        private int age;
        private String lastName;

        // Метод для присвоения пола человека
        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        // Метод для присвоения возраста человека
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        // Метод для присвоения фамилии человека
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    // Реализация Компаратора
    @Override
    public int compareTo(Person o) {
        int result = this.gender.compareTo(o.gender);
        if (result == 0) {
            result = Integer.compare(this.age, o.age);
        }
        if (result == 0) {
            result = this.lastName.compareTo(o.lastName);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Человек " + gender + " пола, возраст - "
                + age + ", по фамилии " + lastName + ".";
    }
}
