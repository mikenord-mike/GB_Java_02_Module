package ru.gb.mikenord.module02.homework02;

public class TestApp {
    private static final String ANSI_RESET = MyStringArrayProcess.ANSI_RESET;
    private static final String ANSI_GREEN = MyStringArrayProcess.ANSI_GREEN;
    private static final String TEST_MSG_01 = "\nПравильный массив 4х4, заполненный с ошибочной ячейкой";
    private static final String TEST_MSG_02 = "\nНеправильный массив 5х6, заполненный правильными строками, " +
            "содержащими числа";
    private static final String TEST_MSG_03 = "\nНеправильный массив (4х4, но с массивом array[1] другого размера), " +
            "заполненный правильными строками, содержащими числа";
    private static final String TEST_MSG_04 = "\nНеправильный массив (4х4, но с массивом array[1] другого размера), " +
            "с ошибочной ячейкой";
    private static final String TEST_MSG_05 = "\nПравильный массив 4х4, заполненный правильными строками," +
            " содержащими числа";

    public static void main(String[] args) throws InterruptedException {

        for (int t = 1; t < 6; t++) {
            Integer countSum = null;
            String[][] array = null;
            String tryMsg = "";

            switch (t) {
                case 1:
                    tryMsg = TEST_MSG_01;
                    array = testArray(4, 4);
                    array[2][3] = "df";
                    break;
                case 2:
                    tryMsg = TEST_MSG_02;
                    array = testArray(5, 6);
                    break;
                case 3:
                    tryMsg = TEST_MSG_03;
                    array = testArray(4, 4);
                    array[1] = new String[]{"15", "2", "8"};
                    break;
                case 4:
                    tryMsg = TEST_MSG_04;
                    array = testArray(4, 4);
                    array[1] = new String[]{"15", "t", "8"};
                    break;
                case 5:
                    tryMsg = TEST_MSG_05;
                    array = testArray(4, 4);
            }

            System.out.println(tryMsg);

            //  without this delay, exception messages are sometimes output before the message <tryMsg>
            Thread.sleep(50);       //   - костыль))

            try {
                MyStringArrayProcess myArrayProcess = new MyStringArrayProcess(array);
                countSum = myArrayProcess.getCellSum();
            } catch (MyArraySizeException | MyArrayDataException e) {
                e.printStackTrace();
            }

            if (countSum != null) {
                System.out.println("Сумма чисел, содержащихся в массиве, составляет: "
                        + ANSI_GREEN + countSum + ANSI_RESET);
            }
        }
    }

    static String[][] testArray(int xDim, int yDim) {
        String[][] array = new String[xDim][yDim];
        for (int i = 0; i < xDim; i++) {
            for (int j = 0; j < yDim; j++) {
                array[i][j] = String.valueOf(i + j);
            }
        }
        return array;
    }

}
