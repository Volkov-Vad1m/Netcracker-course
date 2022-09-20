package quadratic.equation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double a;
        double b;
        double c;

        Scanner in = new Scanner(System.in);
        System.out.println("enter the coefficients in the equation ax^2 + bx + c = 0");

        System.out.print("a: ");
        a = in.nextDouble();

        System.out.print("b: ");
        b = in.nextDouble();

        System.out.print("c: ");
        c = in.nextDouble();

        QuadraticSolver solver = new QuadraticSolver(a, b ,c);
        solver.solve();

        solver.printSolutions();
    }
}
