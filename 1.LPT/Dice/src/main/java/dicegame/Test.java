package dicegame;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        moveRight(arr, arr.length - 2);

        for(int a: arr) {
            System.out.print(a + " ");
        }

    }

    public static void moveRight(int[] array, int positions) {
        int size = array.length;
        for (int i = 0; i < positions; i++) {
            int temp = array[size - 1];
            for (int j = size - 1; j > 0; j--) {
                array[j] = array[j-1];
            }
            array[0] = temp;
        }
    }
}
