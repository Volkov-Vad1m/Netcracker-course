package wolf.vectors;

import java.io.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        File file1 = new File("/home/vadim/курсы netcracker/задачи/1. LPT2/Vectors/src/main/resources/vector1_read");
        File file1Out = new File("/home/vadim/курсы netcracker/задачи/1. LPT2/Vectors/src/main/resources/vector1_out");
        try {

            FileReader reader = new FileReader(file1);
            Vector<Double> v = Vectors.readVector(reader);

            FileOutputStream writer = new FileOutputStream(file1Out);
            Vectors.outputVector(v, writer);

            FileInputStream readerFile = new FileInputStream(file1Out);
            System.out.println(Vectors.inputVector(readerFile));



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

}
