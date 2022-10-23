package wolf.vector.array;

import wolf.vector.list.LinkedListVector;

import java.io.*;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayVector<String> array = new ArrayVector<>("Hi", "my", "name" ,"is", "Vadim") ;

        // try to write
        try {
            OutputStream file = new FileOutputStream("./src/main/resources/data_of_arr.txt");
            ObjectOutput objOut = new ObjectOutputStream(file);

            array.writeArray(objOut);

            file.close();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        //try to read
        ArrayVector<String> newArray = new ArrayVector<>();
        try {
            InputStream file = new FileInputStream("./src/main/resources/data_of_arr.txt");
            ObjectInput objIn = new ObjectInputStream(file);


            newArray.readArray(objIn);

            file.close();
            objIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();


        }
        // compare
        System.out.println(array);
        System.out.println(newArray);
    }
}
