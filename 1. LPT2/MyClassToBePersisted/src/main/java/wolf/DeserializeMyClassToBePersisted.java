package wolf;

import java.io.*;

public class DeserializeMyClassToBePersisted {

    public static void main(String[] args) {
        MyClassToBePersisted obj = null;

        try {
            InputStream in = new FileInputStream("./src/main/resources/objOut");

            ObjectInputStream objIn = new ObjectInputStream(in);

            obj = (MyClassToBePersisted) objIn.readObject();

            objIn.close();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(obj);
    }
}
