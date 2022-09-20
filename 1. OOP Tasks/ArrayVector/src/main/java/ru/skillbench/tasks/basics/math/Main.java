package ru.skillbench.tasks.basics.math;

public class Main {


    public static void main(String[] args) {
        ArrayVector vector = new ArrayVectorImpl();
        ArrayVector vector1 = new ArrayVectorImpl();
        ArrayVector vector2= new ArrayVectorImpl();
        ArrayVector vector3 = new ArrayVectorImpl();//10
        double[]  arr = new double[] {466, 1.01, 3, 7 ,8.12 , 9 , 17.1, 22.32, 10.5, 23.1};
        double[]  arr2 = new double[] {466, 1.01, 3, 7 ,8.12 , 9 , 17.1, 22.32, 10.5, 23.1, 66, 77};
        double[] arr3 = new double[] {2, 2, 2, 2};
        double[] arr4 = new double[] { 2, 2};
        vector.set(arr);
        vector1.set(arr);
        vector2.set(arr3);
        vector3.set(arr4);


        System.out.println(vector2.getNorm());
    }


    public static void dump(ArrayVector vector) {
        for(int i = 0; i < vector.getSize(); i++) {
            System.out.print(vector.get(i) + " ");
        }
        System.out.println("");
    }
    public static void dump(double[] vector) {
        for(int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + " ");
        }
        System.out.println("");
    }

}



