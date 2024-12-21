package org.example.application.dataclass;

import java.util.Comparator;

public class Barrel implements Comparable<Barrel> {
    private final int volume;
    private final String content;
    private final String material;

    public Barrel(Builder builder) {
        this.volume = builder.volume;
        this.content = builder.content;
        this.material = builder.material;
    }

    public int getVolume() {
        return volume;
    }

    public String getContent() {
        return content;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Barrel{" +
                "volume=" + volume +
                ", content='" + content + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

    @Override
    public int compareTo(Barrel o) {
        return Comparator.comparing(Barrel::getVolume)
                .thenComparing(Barrel::getContent)
                .thenComparing(Barrel::getMaterial)
                .compare(this, o);
    }

    public static class Builder {
        private int volume;
        private String content;
        private String material;

        public Builder volume(int volume) {
            this.volume = volume;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder material(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }
}
