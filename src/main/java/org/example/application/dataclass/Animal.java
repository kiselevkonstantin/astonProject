package org.example.application.dataclass;

import java.util.Comparator;

public class Animal implements Comparable<Animal> {
    private final String kind;
    private final String eyeColor;
    private final boolean hasFur;

    public Animal(Builder builder) {
        this.kind = builder.kind;
        this.eyeColor = builder.eyeColor;
        this.hasFur = builder.hasFur;
    }

    public String getKind() {
        return kind;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean hasFur() {
        return hasFur;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "kind='" + kind + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hasFur=" + hasFur +
                '}';
    }

    @Override
    public int compareTo(Animal o) {
        return Comparator.comparing(Animal::getKind)
                .thenComparing(Animal::getEyeColor)
                .thenComparing(Animal::hasFur)
                .compare(this, o);
    }

    public static class Builder {
        private String kind;
        private String eyeColor;
        private boolean hasFur;

        public Builder kind(String kind) {
            this.kind = kind;
            return this;
        }

        public Builder eyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder hasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}
