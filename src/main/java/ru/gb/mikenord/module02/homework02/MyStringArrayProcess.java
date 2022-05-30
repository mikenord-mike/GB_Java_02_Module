package ru.gb.mikenord.module02.homework02;

public class MyStringArrayProcess {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_MAGENTA = "\u001B[95m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private int cellSum;

    public int getCellSum() {
        return cellSum;
    }

    public MyStringArrayProcess(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int xDim = array.length;
        int yDim = array[0].length;

        boolean yNotIdenticalValues = false;
        if (yDim > 1) {
            for (int i = 1; i < xDim; i++) {
                yNotIdenticalValues |= array[i].length != yDim;
            }
        }

        if (xDim != 4 || yDim != 4 || yNotIdenticalValues) {
            String msg = xDim + "x" + (yNotIdenticalValues ? "<неизвестно>" : yDim);
            throw new MyArraySizeException(msg);
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    cellSum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
    }
}
