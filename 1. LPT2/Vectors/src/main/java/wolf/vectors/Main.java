package wolf.vectors;

import java.io.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        try {
            FileReader reader = new FileReader("./src/main/resources/vector1_read");
            Vector<Double> v = Vectors.readVector(reader);
            reader.close();

            FileOutputStream out = new FileOutputStream("./src/main/resources/vector1_out");
            Vectors.outputVector(v, out);
            out.close();

            FileInputStream in = new FileInputStream("./src/main/resources/vector1_out");
            System.out.println(Vectors.inputVector(in));
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            FileReader reader = new FileReader("./src/main/resources/vector1_read");
            Vector<Double> v = Vectors.readVector(reader);
            reader.close();

            FileWriter writer = new FileWriter("./src/main/resources/vector1_write");
            Vectors.writeVector(v, writer);
            writer.close();

            FileReader reader2 = new FileReader("./src/main/resources/vector1_write");
            System.out.println(Vectors.readVector(reader2));
            reader2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
