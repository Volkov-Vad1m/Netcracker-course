package wolf.analysis;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(WordAnalysis.charsInBoth("cggfdf", "ggcc"));
        System.out.println(WordAnalysis.charsInFirst("cggfdf", "ggcc"));
        printReverse(WordAnalysis.charsInOne("cg2gfdf", "gg4cc"));
        printHashShift(WordAnalysis.charsInOne("cg2gfdf", "gg4cc"),10 );
    }


    public static <T> void printReverse(Set<T> set) {
        List<T> list = new ArrayList<>(set);
        Collections.reverse(list);
        System.out.println(list);
    }

    public static void printHashShift(Set<Character> set, int n) {
        TreeSet<Character> sortedSet = new TreeSet<>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Integer.compare(shift(c1.hashCode(), n), shift(c2.hashCode(), n));
            }
        });

        sortedSet.addAll(set);
        System.out.println(sortedSet);
    }

    public static int shift(int value, int n) {
        return (value << (n % 32)) | (value >>> ((n % 32) - 32));
    }

}
