package org.example.application.models;

public class Barrel implements Comparable<Barrel> {
    private final double volume; // Объем
    private final String content; // Хранимый материал
    private final String material; // Материал из которого изготовлена

    //Геттеры для получения информации по полям
    public Double getVolume() {
        return volume;
    }

    public String getContent() {
        return content;
    }

    public String getMaterial() {
        return material;
    }

    //Корнструктор класса Barrel для использования Builder
    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.content = builder.content;
        this.material = builder.material;
    }

    // Вложенный статический класс Builder
    public static class Builder {
        private double volume;
        private String content;
        private String material;

        // Метод для присвоения бочке объёма
        public Builder setVolume(Double volume) {
            this.volume = volume;
            return this;
        }

        // Метод для присвоения бочке хранимого материала
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        // Метод для присвоения материала из которого бочка изготовлена
        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }

    // Реализация Компаратора
    @Override
    public int compareTo(Barrel o) {
        int result = Double.compare(this.volume, o.volume);
        if (result == 0) {
            result = this.content.compareTo(o.content);
        }
        if (result == 0) {
            result = this.material.compareTo(o.material);
        }
        return result;
    }

    @Override
    public String toString() {
        return "В бочке из " + material + " объёмом " + volume +
                " хранится - " + content + ".";
    }
}