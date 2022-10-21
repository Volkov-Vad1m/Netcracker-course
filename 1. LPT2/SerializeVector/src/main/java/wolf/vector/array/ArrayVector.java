package wolf.vector.array;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

public class ArrayVector<T> {
    private T[] array;

    public ArrayVector() {
    }

    public ArrayVector(T... values) {
        array = values;
    }

    public void writeArray(ObjectOutput out) throws IOException {
        out.writeObject(array);
    }

    public void readList(ObjectInput in) throws IOException, ClassNotFoundException {
        this.array =  (T[])in.readObject();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[ ");
        for(T val : array) {
            s.append(val).append(" ");
        }
        s.append(" ]");
        return s.toString();
    }

}
