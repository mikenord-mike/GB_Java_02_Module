package ru.gb.mikenord.module02.homework05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreadTest {
    static final int SIZE = 10_000_000;
    static final int PASS_COUNT = 10;   //the number of repetitions to calculate the average time value
    static final String PASS_MSG = "\nNumber of passes - " + PASS_COUNT;
    static final String SINGLE_THREAD_MSG = "\nSingle thread pass progress: ";
    static final int THREADS_COUNT = 20;  // the number of threads if multiThreadArrayProcessing(0) calling
    static final String THREADS_MSG = "\nNumber of simultaneous threads - %d. Pass progress: ";

    public static void main(String[] args) {
        System.out.println(PASS_MSG);

        simpleArrayProcessing();
        multiThreadArrayProcessing(0);

        // the test for selecting the best number of threads
//        betterNumOfThreadsSearch(1, 2000, 100);
    }

    public static float mathTransform(int i, float cellValue) {
        return (float) (cellValue * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    public static void simpleArrayProcessing() {
        float[] arr = new float[SIZE];
        long processTime = 0;

        System.out.print(SINGLE_THREAD_MSG);

        for (int j = 0; j < PASS_COUNT; j++) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = 1.0f;
            }

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = mathTransform(i, arr[i]);
            }
            processTime += System.currentTimeMillis() - startTime;
            System.out.print("*");
        }
        processTime /= PASS_COUNT;
        System.out.println("\nSimple thread average time: " + processTime + " ms");
    }

    public static long multiThreadArrayProcessing(int threadsCount) {
        float[] arr = new float[SIZE];
        float[] arrEnd = new float[SIZE];
        long processTime = 0;

        if (threadsCount == 0) {
            threadsCount = THREADS_COUNT;
        }

        System.out.printf(THREADS_MSG, threadsCount);

        for (int j = 0; j < PASS_COUNT; j++) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = 1.0f;
            }

            long startTime = System.currentTimeMillis();

            int subArraySize = SIZE / threadsCount;
            List<Thread> threadList = new ArrayList<>();
            float[][] split = new float[threadsCount][subArraySize];

            for (int i = 0; i < threadsCount; i++) {

                System.arraycopy(arr, i * subArraySize, split[i], 0, subArraySize);

                int finalI = i;

                threadList.add(new Thread(() -> {
                    for (int k = 0; k < subArraySize; k++) {
                        split[finalI][k] = mathTransform(k, split[finalI][k]);
                    }
                }));
            }

            for (Thread thread : threadList) {
                thread.start();
            }
            for (Thread thread : threadList) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < threadsCount; i++) {
                System.arraycopy(split[i], 0, arrEnd, i * subArraySize, subArraySize);
            }

            processTime += System.currentTimeMillis() - startTime;
            System.out.print("*");
        }
        processTime /= PASS_COUNT;
        System.out.println("\nMultithread average time: " + processTime + " ms");
        return processTime;
    }

    public static void betterNumOfThreadsSearch(int startNum, int endNum, int stepNum) {
        long maxMeasure = Long.MIN_VALUE, minMeasure = Long.MAX_VALUE;
        int minCnt = 0, maxCnt = 0;

        if (endNum < startNum || stepNum > (endNum - startNum) || startNum < 0) {
            startNum = 1;
            endNum = 100;
            stepNum = 5;
        }

        System.out.printf("\nSearch for the fastest number of threads, starting " +
                "from %d to %d in steps of %d\n", startNum, endNum, stepNum);

        for (int i = startNum; i < endNum; i += stepNum) {

            long tmpMeasure = multiThreadArrayProcessing(i);

            if (tmpMeasure > maxMeasure) {
                maxMeasure = tmpMeasure;
                maxCnt = i;
            } else if (tmpMeasure < minMeasure) {
                minMeasure = tmpMeasure;
                minCnt = i;
            }
        }

        System.out.printf("\nMaximum time - '%d' with the number of threads - '%d'\n", maxMeasure, maxCnt);
        System.out.printf("Minimum time - '%d' with the number of threads - '%d'\n", minMeasure, minCnt);
    }
}

