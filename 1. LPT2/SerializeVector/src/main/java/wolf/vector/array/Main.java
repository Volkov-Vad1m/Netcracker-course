package wolf.vector.array;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args) {
        ArrayList<Double> arrayList = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            arrayList.add(i + 0d);
        }

        LinkedList<Double> linkedList = new LinkedList<>(arrayList);
        linkedList.add(10d);

        try {
            OutputStream arrOut = new FileOutputStream("./src/main/resources/arr_out");
            OutputStream listOut = new FileOutputStream("./src/main/resources/list_out");

            ObjectOutputStream objOut = new ObjectOutputStream(arrOut);
            objOut.writeObject(arrayList);

            objOut = new ObjectOutputStream(listOut);
            objOut.writeObject(linkedList);

            arrOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream arrIn = new FileInputStream("./src/main/resources/arr_out");
            InputStream listIn = new FileInputStream("./src/main/resources/list_out");

            ObjectInputStream objIn = new ObjectInputStream(arrIn);
            System.out.println("-----------");
            System.out.println(objIn.readObject());

            objIn = new ObjectInputStream(listIn);
            System.out.println("-----------");
            System.out.println(objIn.readObject());

            arrIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
