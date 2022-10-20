package wolf.analysis;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;

public class WordAnalysis {

    public static Set<Character> charsInBoth (String s1, String s2) {
        Set<Character> chars = new LinkedHashSet<>();

        for(char c : s1.toCharArray()) {
            if(s2.contains(String.valueOf(c))) {
                chars.add(c);
            }
        }
        return chars;
    }

    public static Set<Character> charsInFirst (String s1, String s2) {
        Set<Character> chars = new LinkedHashSet<>();

        for(char c : s1.toCharArray()) {
            if(s2.contains(String.valueOf(c)) == false) {
                chars.add(c);
            }
        }
        return chars;
    }

    public static Set<Character> charsInOne(String s1, String s2) {
        Set<Character> chars = new LinkedHashSet<>();

        for(char c : s1.toCharArray()) {
                chars.add(c);
        }
        for(char c : s2.toCharArray()) {
            chars.add(c);
        }
        return chars;
    }

    

}
