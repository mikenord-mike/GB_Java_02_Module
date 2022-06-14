package ru.gb.mikenord.module02.homework01;

public class Human implements Movable {
    private final String CLASS_NAME = "Человек";

    private final String name;
    private final int maxLength;
    private final int maxHeight;

    public Human(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
    }

    @Override
    public String toString() {
        return CLASS_NAME + " " + name;
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        boolean result = obstacle.size() <= maxHeight;
        String resultMessage = result ? SUCCESSFUL_JUMP_MSG : FAILED_JUMP_MSG;
        System.out.println(this + resultMessage + obstacle);
        return result;
    }

    @Override
    public boolean run(Obstacle obstacle) {
        boolean result = obstacle.size() <= maxLength;
        String resultMessage = result ? SUCCESSFUL_RUN_MSG : FAILED_RUN_MSG ;
        System.out.println(this + resultMessage + obstacle);
        return result;
    }
}
