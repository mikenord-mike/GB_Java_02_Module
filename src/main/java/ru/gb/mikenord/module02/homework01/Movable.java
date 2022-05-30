package ru.gb.mikenord.module02.homework01;

public interface Movable {
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";

    String SUCCESSFUL_JUMP_MSG = ANSI_GREEN + " успешно перепрыгнул стену " + ANSI_RESET;
    String FAILED_JUMP_MSG = ANSI_RED + " не смог перепрыгнуть стену " + ANSI_RESET;
    String SUCCESSFUL_RUN_MSG = ANSI_GREEN + " успешно пробежал беговую дорожку " + ANSI_RESET;
    String FAILED_RUN_MSG = ANSI_RED + " не смог пробежать беговую дорожку " + ANSI_RESET;

    boolean jump(Obstacle obstacle);

    boolean run(Obstacle obstacle);

}
