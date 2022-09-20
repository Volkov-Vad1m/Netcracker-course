package quadratic.equation;
import org.apache.commons.math3.complex.*;

public class QuadraticSolver {
    public final static double EPSILON = 10E-6;

    // ax^2 + bx + c = 0
    private double a;
    private double b;
    private double c;

    private Complex z1, z2;
    /////////////////////////////////////////////////////////////////////
    public QuadraticSolver(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /////////////////////////////////////////////////////////////////////

    public Complex getZ1() {
        return z1;
    }

    public Complex getZ2() {
        return z2;
    }

    /////////////////////////////////////////////////////////////////////
    class Discriminant {
        private double value;
        public Discriminant() {
            this.value = b * b - 4 * a * c;
        }

    }

    public void solve() {
        if(isZero(a)) { //linear equation
            z1 = new Complex(-(c/b));
            return;
        }

        Discriminant discriminant = this.new Discriminant();

        if(isZero(discriminant.value)) {
            z1 = new Complex(-b / (2 * a) );
        } else if (discriminant.value > 0) {
            z1 = new Complex((-b + Math.sqrt(discriminant.value) ) / (2 * a));
            z2 = new Complex((-b - Math.sqrt(discriminant.value) ) / (2 * a));
        } else {
            z1 = new Complex(-b / (2 * a), Math.sqrt( Math.abs(discriminant.value) ) / (2 * a));
            z2 = z1.conjugate();
        }
    }

    public void printSolutions() {
        if(z1 == null) {
            System.out.println("equation not yet solved");
        } else if (z2 == null){
            System.out.println(z1.getReal() + " + i" + z1.getImaginary());
        } else {
            System.out.println(z1.getReal() + " + i" + z1.getImaginary());
            System.out.println(z2.getReal() + " + i" + z2.getImaginary());
        }
    }

    private boolean isZero(double number) {
        return Math.abs(number) < EPSILON;
    }

    private int compare(double d1, double d2) {
        if(d1 < d2) {
            return -1;
        } else if (d1 > d2) {
            return 1;
        } else 
    }
}
