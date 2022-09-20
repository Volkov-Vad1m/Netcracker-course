package ru.skillbench.tasks.basics.math;

public class ArrayVectorImpl implements ArrayVector{

    double[] array;

    public ArrayVectorImpl() {
        array = new double[1];
    }


    /**
     * Задает все элементы вектора (определяет длину вектора).
     * Передаваемый массив не клонируется.
     *
     * @param elements Не равен null
     */
    @Override
    public void set(double... elements) { //done
        array = new double[elements.length];
        System.arraycopy(elements, 0, array, 0, elements.length);
    }

    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     */
    @Override
    public double[] get() { //done
        double[] outArray = new double[array.length];
        System.arraycopy(array, 0, outArray, 0, array.length);
        return outArray;
    }

    /**
     * Возвращает копию вектора (такую, изменение элементов
     * в которой не приводит к изменению элементов данного вектора).<br/>
     * Рекомендуется вызвать метод clone() у самого массива или использовать
     * {@link System#arraycopy(Object, int, Object, int, int)}.
     */
    @Override
    public ArrayVector clone() { // done
        ArrayVector newArray = new ArrayVectorImpl();
        newArray.set(this.array);
        return newArray;
    }

    /**
     * Возвращает число элементов вектора.
     */
    @Override //done
    public int getSize() {
        return array.length;
    }

    /**
     * Изменяет элемент по индексу.
     *
     * @param index В случае выхода индекса за пределы массива:<br/>
     *              а) если index<0, ничего не происходит;<br/>
     *              б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
     * @param value
     */
    @Override //done
    public void set(int index, double value) {
        if(index < 0) {
            return;
        } else if(index >= array.length) {
            double[] newArray = new double[index + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.set(newArray);
            array[index] = value;
        } else {
            array[index] = value;
        }



    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index В случае выхода индекса за пределы массива
     *              должно генерироваться ArrayIndexOutOfBoundsException
     */
    @Override // done
    public double get(int index) throws ArrayIndexOutOfBoundsException {

        if(index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return array[index];
    }

    /**
     * Возвращает максимальный элемент вектора.
     */
    @Override //done
    public double getMax() {

        double max = array[0];
        for(int i = 0; i < array.length; i++){
            if(array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Возвращает минимальный элемент вектора.
     */
    @Override //done
    public double getMin() {
        double min = array[0];

        for(int i = 0; i < array.length; i++) {

            if(array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Сортирует элементы вектора в порядке возрастания.
     */
    @Override // done
    public void sortAscending() {
        for (int i = 0; i<array.length - 1; i++){

            for(int j = 0; j<array.length - i - 1; j++){

                if(array[j+1] < array[j]){

                    double swap = array[j];
                    array[j] = array[j+1];
                    array[j+1] = swap;
                }
            }
        }
    }

    /**
     * Умножает вектор на число.<br/>
     * Замечание: не пытайтесь использовать безиндексный цикл foreach:
     * для изменения элемента массива нужно знать его индекс.
     *
     * @param factor
     */
    @Override // done
    public void mult(double factor) {
        for(int i = 0; i < array.length; i++){
            array[i] = array[i] * factor;
        }
    }

    /**
     * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются<br/>
     * (если данный вектор - больший, его размер менять не надо, просто не меняйте последние элементы).
     *
     * @param anotherVector Не равен null
     * @return Ссылка на себя (результат сложения)
     */
    @Override // done
    public ArrayVector sum(ArrayVector anotherVector) {

        int len = Math.min(anotherVector.getSize(), array.length);

        for(int i = 0; i < len; i++) {
            array[i] += anotherVector.get(i);
        }
        return this;
    }

    /**
     * Возвращает скалярное произведение двух векторов.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются.
     *
     * @param anotherVector Не равен null
     */
    @Override // done
    public double scalarMult(ArrayVector anotherVector) {
        int len = Math.min(anotherVector.getSize(), array.length);
        double result = 0;

        for(int i = 0; i < len; i++) {
            result += anotherVector.get(i) * array[i];
        }

        return result;
    }

    /**
     * Возвращает евклидову норму вектора (длину вектора
     * в n-мерном евклидовом пространстве, n={@link #getSize()}).
     * Это можно подсчитать как корень квадратный от скалярного произведения вектора на себя.
     */
    @Override
    public double getNorm() {
        return Math.sqrt(scalarMult(this));
    }
}
