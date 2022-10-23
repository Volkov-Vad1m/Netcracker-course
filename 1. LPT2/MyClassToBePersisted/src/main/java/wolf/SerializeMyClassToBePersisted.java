package wolf;

import java.io.*;

public class SerializeMyClassToBePersisted {

    public static void main(String[] args) {

        MyClassToBePersisted obj = new MyClassToBePersisted("profile", "group");

        try {
            OutputStream out = new FileOutputStream("./src/main/resources/objOut");
            ObjectOutputStream objOut = new ObjectOutputStream(out);

            objOut.writeObject(obj);

            objOut.close();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
