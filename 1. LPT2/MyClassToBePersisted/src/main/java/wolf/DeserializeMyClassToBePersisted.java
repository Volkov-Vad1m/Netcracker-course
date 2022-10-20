package wolf;

import java.io.*;

public class DeserializeMyClassToBePersisted {

    public static void main(String[] args) {


        try {
            InputStream in = new FileInputStream("./src/main/resources/objOut");

            ObjectInputStream objIn = new ObjectInputStream(in);

            MyClassToBePersisted obj = (MyClassToBePersisted) objIn.readObject();

            System.out.println(obj);


            in.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
