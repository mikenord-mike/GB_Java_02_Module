package ru.gb.mikenord.module02.homework02;

public class MyArraySizeException extends Exception {
    private static final String ANSI_RESET = MyStringArrayProcess.ANSI_RESET;
    private static final String ANSI_BRIGHT_MAGENTA = MyStringArrayProcess.ANSI_BRIGHT_MAGENTA;
    private static final String ARRAY_SIZE_EXCEPTION_MSG = "Передан массив размерности "
            + ANSI_BRIGHT_MAGENTA + "%s" + ANSI_RESET + " вместо 4х4";

    public MyArraySizeException(String message) {
        super(String.format(ARRAY_SIZE_EXCEPTION_MSG, message));
    }
}