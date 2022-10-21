package wolf.vector.list;

import wolf.vector.list.LinkedListVector;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args) {
        LinkedListVector<Double> list = new LinkedListVector<>();
        for(int i = 0; i < 10; i++) {
            list.add(i + 0d);
        }

        // try to write
        try {
            OutputStream file = new FileOutputStream("./src/main/resources/data_of_list.txt");
            ObjectOutput objOut = new ObjectOutputStream(file);

            list.writeList(objOut);

            file.close();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        //try to read
        LinkedListVector<Double> newList = new LinkedListVector<>();
        try {
            InputStream file = new FileInputStream("./src/main/resources/data_of_list.txt");
            ObjectInput objIn = new ObjectInputStream(file);


            newList.readList(objIn);

            file.close();
            objIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        // compare
        System.out.println(newList.equals(list));
    }
}
