package ru.skillbench.tasks.basics.math;

import java.util.Objects;
import java.util.Stack;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        ComplexNumber[] cmp = {new ComplexNumberImpl(17, 17), new ComplexNumberImpl(0, 0), new ComplexNumberImpl(10, 10)};
        ComplexNumber cmp1 = new ComplexNumberImpl();

        cmp1.sort(cmp);
        System.out.println(cmp[0].getRe());
    }
}
