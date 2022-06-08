package ru.gb.mikenord.module02.homework01;

public class Wall implements Obstacle {
    private final String name;
    private final int height;

    public Wall(String name, int height) {
        this.name = name;
        if (height < 0) {
            height = 0;
        }
        this.height = height;
    }

    @Override
    public int size() {
        return height;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean performAction(Movable movable) {
        return movable.jump(this);
    }
}