package ru.gb.mikenord.module02.homework01;

public class Treadmill implements Obstacle {
    private final String name;
    private final int length;

    public Treadmill(String name, int length) {
        this.name = name;
        if (length < 0) {
            length = 0;
        }
        this.length = length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean performAction(Movable movable) {
      return movable.run(this);
    }
}