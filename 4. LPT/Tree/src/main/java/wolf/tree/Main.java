package wolf.tree;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        Tree<Double> tree = new Tree<>();

        tree.add(10d);
        tree.add(5d);
        tree.add(3d);
        tree.add(20d);
        tree.add(15d);
        tree.add(7d);
        tree.delete(5d);
        tree.preorder().forEach(System.out::println);
    }
}
