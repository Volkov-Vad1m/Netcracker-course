package wolf.vectors;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Vectors {

    public static void multiply(List<Double> v, double d) {
        for(int i = 0; i < v.size(); i++) {
            v.set(i, v.get(i) * d);
        }
    }

    public static void sum(Vector<Double> v1, Vector<Double> v2) {
        int length = Integer.min(v1.size(), v2.size());

        for(int i = 0; i < length; i++) {
            v1.set(i, v1.get(i) + v2.get(i));
        }
    }

    public static double scalarMultiply(Vector<Double> v1, Vector<Double> v2) {
        int length = Integer.min(v1.size(), v2.size());
        int result = 0;

        for(int i = 0; i < length; i++) {
            result += v1.get(i) * v2.get(i);
        }

        return result;
    }

    public static void outputVector(Vector<Double> v, OutputStream out) throws IOException {

        ObjectOutputStream objectOut = new ObjectOutputStream(out);

        objectOut.writeInt(v.size());

        for(Double value : v) {
            objectOut.writeDouble(value);
        }

        objectOut.close();
    }

    public static Vector<Double> inputVector(InputStream in) throws IOException {

        ObjectInputStream objectIn = new ObjectInputStream(in);

        if(objectIn.available() == 0) {
            objectIn.close();
            throw new RuntimeException("bad input");
        } else {

            Vector<Double> v = new Vector<>(objectIn.readInt());

            while (objectIn.available() > 0) {
                v.add(objectIn.readDouble());
            }

            objectIn.close();
            return v;
        }
    }

    public static void writeVector(Vector<Double> v, Writer out) throws IOException {

        out.write(String.valueOf(v.size()));

        for (Double value : v) {
            out.write(" " + value);
        }

    }

    public static Vector<Double> readVector(Reader in) throws IOException {

            Vector<Double> v = new Vector<>();

            StreamTokenizer tokenizer = new StreamTokenizer(in);
            tokenizer.parseNumbers();

            tokenizer.nextToken();

            while(tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                v.add(tokenizer.nval);

            }

            return v;

    }



}
