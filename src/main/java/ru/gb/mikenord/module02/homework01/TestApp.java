package ru.gb.mikenord.module02.homework01;

import java.util.Random;

public class TestApp {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Random random = new Random();

        Movable[] persArray = new Movable[random.nextInt(20) + 5];
        boolean[] persResult = new boolean[persArray.length];
        int[] persCnt = new int[3];

        Obstacle[] barrierArray = new Obstacle[random.nextInt(20) + 5];

        System.out.printf("Персонажей - %d, препятствий - %d\n\n", persArray.length, barrierArray.length);

        for (int i = 0; i < persArray.length; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    persCnt[0]++;
                    persArray[i] = new Human("Адам " + persCnt[0] + "-й",
                            random.nextInt(3000) + 10,
                            random.nextInt(30) + 1);
                    break;
                case 1:
                    persCnt[1]++;
                    persArray[i] = new Robot("Бендер " + persCnt[1] + "-й",
                            random.nextInt(3000) + 10,
                            random.nextInt(30) + 1);
                    break;
                case 2:
                    persCnt[2]++;
                    persArray[i] = new Cat("Мурзик " + persCnt[2] + "-й",
                            random.nextInt(3000) + 10,
                            random.nextInt(30) + 1);
            }
            persResult[i] = true;
        }

        int wallCnt = 0;
        for (int i = 0; i < barrierArray.length; i++) {
            switch (random.nextInt(2)) {
                case 0:
                    wallCnt++;
                    barrierArray[i] = new Wall("Стена " + wallCnt + "-я",
                            random.nextInt(30) + 5);
                    break;
                case 1:
                    barrierArray[i] = new Treadmill("Дорожка " + ((i + 1) - wallCnt) + "-я",
                            random.nextInt(3000) + 50);
            }
        }

        for (int i = 0; i < (barrierArray.length + 1); i++) {
            for (int j = 0; j < persArray.length; j++) {
                if (persResult[j]) {
                    if (i < barrierArray.length) {
                        persResult[j] &= barrierArray[i].performAction(persArray[j]);
                    } else {
                        System.out.println(persArray[j] + ANSI_YELLOW + " прошел ВСЕ препятствия!" + ANSI_RESET);
                    }
                }
            }
        }

    }


}
