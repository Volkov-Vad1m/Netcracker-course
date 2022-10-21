package wolf.vector.list;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class LinkedListVector<T> {

    private LinkedList<T> list;

    public LinkedListVector() {
        this.list = new LinkedList<>();
    }

    public LinkedListVector(Collection<? extends T> c) {
        this.list = new LinkedList<>(c);
    }

    public void writeList(ObjectOutput out) throws IOException {
        out.writeObject(list);
    }

    public void readList(ObjectInput in) throws IOException, ClassNotFoundException {
        this.list = (LinkedList<T>) in.readObject();
    }

    public boolean add(T elem) {
        return list.add(elem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListVector<?> that = (LinkedListVector<?>) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
