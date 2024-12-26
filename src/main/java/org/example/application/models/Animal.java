package org.example.application.models;

public class Animal implements Comparable<Animal> {
    private final String type; // Вид животного
    private final String eyeColor; // Цвет глаз
    private final boolean fur; // Есть ли шерсть

    //Геттеры для получения информации по полям
    public String getType() {
        return type;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean isFur() {
        return fur;
    }

    //Корнструктор класса Animal для использования Builder
    private Animal(Builder builder) {
        this.type = builder.type;
        this.eyeColor = builder.eyeColor;
        this.fur = builder.fur;
    }

    // Вложенный статический класс Builder
    public static class Builder {
        private String type;
        private String eyeColor;
        private boolean fur;

        // Метод для присвоения вида животного
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        // Метод для присвоения цвета глаз животного
        public Builder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        // Метод для определения есть ли шерсть у животного? (true/false)
        public Builder setFur(boolean fur) {
            this.fur = fur;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }

    // Реализация Компаратора
    @Override
    public int compareTo(Animal o) {
        int result = this.type.compareTo(o.type);
        if (result == 0) {
            result = this.eyeColor.compareTo(o.eyeColor);
        }
        if (result == 0) {
            result = Boolean.compare(this.fur, o.fur);
        }
        return result;
    }


    @Override
    public String toString() {
        return "Животное: " + type + ", цвет глаз - "
                + eyeColor + ", шерстяной покров - " + fur + ".";
    }
}