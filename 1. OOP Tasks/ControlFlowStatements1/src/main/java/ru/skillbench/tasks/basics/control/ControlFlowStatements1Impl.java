package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements1Impl implements ControlFlowStatements1{

    public ControlFlowStatements1Impl() {

    }

    /**
     * Для данного вещественного x найти значение следующей функции f, принимающей вещественные значения:<br/>
     * <pre>
     *        | 2*sin(x), если x>0,
     *  f(x)= |
     *        | 6-x, если x<=0.
     * </pre>
     *
     * @param x
     * @return значение f.
     */
    @Override
    public float getFunctionValue(float x) {
        if(x > 0) {
            return (float) (2*Math.sin(x));
        } else {
            return (6-x);
        }
    }

    /**
     * Дано целое число в диапазоне 1–7.
     * Вернуть строку — название дня недели, соответствующее этому числу:<br/>
     * 1 — Monday, 2 — Tuesday, 3 - Wednesday, 4 - Thursday, 5 - Friday, 6 - Saturday, 7 - Sunday.
     *
     * @param weekday
     * @return строковое представление weekday
     */
    @Override
    public String decodeWeekday(int weekday) {
        switch (weekday) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: return "test";
        }
    }

    /**
     * Создать двумерный массив, содержащий 8x5 целочисленных элементов,
     * и присвоить каждому элементу произведение его индексов: array[i][j] = i*j.
     *
     * @return массив.
     */
    @Override
    public int[][] initArray() {
        int[][] arr = new int[8][5];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = i * j;
            }
        }
        return arr;
    }

    /**
     * Найти минимальный элемент заданного двумерного массива.
     *
     * @param array массив, содержащий как минимум один элемент
     * @return минимальный элемент массива array.
     */
    @Override
    public int getMinValue(int[][] array) {
        int min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                min = Math.min(array[i][j], min);
            }
        }
        return min;
    }

    /**
     * Начальный размер вклада в банке равен $1000.<br/>
     * По окончанию каждого года размер вклада увеличивается на P процентов (вклад с капитализацией процентов).<br/>
     * По заданному P определить, через сколько лет размер вклада превысит $5000, а также итоговый размер вклада.
     *
     * @param P процент по вкладу
     * @return информация о вкладе (в виде экземпляра класса {@link BankDeposit}) после наступления вышеуказанного условия
     */
    @Override
    public BankDeposit calculateBankDeposit(double P) {
        double money = 1000;
        int years = 0;

        while( money < 5000) {
            years += 1;
            money += (money * P)/ 100;
        }

        BankDeposit result = new BankDeposit();
        result.amount = money;
        result.years = years;

        return result;
    }
}
