package ru.gb.mikenord.module02.homework02;

public class MyArrayDataException extends Exception {
    private static final String ANSI_RESET = MyStringArrayProcess.ANSI_RESET;
    private static final String ANSI_BRIGHT_MAGENTA = MyStringArrayProcess.ANSI_BRIGHT_MAGENTA;
    private static final String ARRAY_DATA_EXCEPTION_MSG = "Ячейка " +
            ANSI_BRIGHT_MAGENTA + "[%d][%d]" + ANSI_RESET + " не содержит строкового представления целого числа";

    public MyArrayDataException(int xCell, int yCell) {
        super(String.format(ARRAY_DATA_EXCEPTION_MSG, xCell, yCell));
    }

}