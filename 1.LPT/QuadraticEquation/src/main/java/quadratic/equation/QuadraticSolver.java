package quadratic.equation;
import org.apache.commons.math3.complex.*;

public class QuadraticSolver {
    public final static double EPSILON = 10E-6; // for comparing

    // ax^2 + bx + c = 0
    private double a;
    private double b;
    private double c;

    //roots
    private Complex z1, z2;
    // ************************************************************************
    public QuadraticSolver(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    // ************************************************************************
    // getters and setters
    // ************************************************************************
    public double getA() {
        return a;
    }

    public void setA(double a) {
        z1 = null;
        z2 = null;
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        z1 = null;
        z2 = null;
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        z1 = null;
        z2 = null;
        this.c = c;
    }

    public Complex getZ1() {
        return z1;
    }

    public Complex getZ2() {
        return z2;
    }

    // ************************************************************************
    // nested class
    // ************************************************************************
    class Discriminant {
        private double value;
        public Discriminant() {
            this.value = b * b - 4 * a * c;
        }

    }
    // ************************************************************************
    // methods
    // ************************************************************************
    public void solve() {
        //linear equation
        if(isZero(a)) {
            z1 = new Complex(-(c/b));
            return;
        }
        // quadratic equation
        Discriminant discriminant = this.new Discriminant();

        if(isZero(discriminant.value)) {
            z1 = new Complex(-b / (2 * a) );
            z2 = new Complex(-b / (2 * a) );
        } else if (discriminant.value > 0) {
            z1 = new Complex((-b + Math.sqrt(discriminant.value) ) / (2 * a));
            z2 = new Complex((-b - Math.sqrt(discriminant.value) ) / (2 * a));
        } else {
            z1 = new Complex(-b / (2 * a), Math.sqrt( Math.abs(discriminant.value) ) / (2 * a));
            z2 = z1.conjugate();
        }
    }


    public void printSolutions() {
        if(z1 == null) { // equation not yet solved

            System.out.println("equation not yet solved");

        } else if(z2 == null) { // linear equation
            System.out.println("linear equation, one root:");
            System.out.println(z1.getReal());

        } else if (z1.equals(z2)) { // identical roots

            if(isZero(z1.getImaginary())) {
                System.out.println("two identical roots:");
                System.out.print(z1.getReal());
            } else  {
                if(z1.getImaginary() < 0) {
                    System.out.println(z1.getReal() + " - i" + Math.abs(z1.getImaginary()));
                } else  {
                    System.out.println(z1.getReal() + " + i" + z1.getImaginary());
                }

            }

        } else {

            System.out.println("two roots: ");

            if(isZero(z1.getImaginary())) {

                System.out.println(z1.getReal());
                System.out.print(z2.getReal());

            } else if(isZero(z1.getReal())) {

                System.out.println("i" + z1.getImaginary());
                System.out.println("-i" + z1.getImaginary());

            } else {
                if(z1.getImaginary() < 0) {
                    System.out.println(z1.getReal() + " - i" + Math.abs(z1.getImaginary()));
                    System.out.print(z2.getReal() + " + i" + z2.getImaginary());
                } else {
                    System.out.println(z1.getReal() + " + i" + z1.getImaginary());
                    System.out.print(z2.getReal() + " - i" + Math.abs(z2.getImaginary()));
                }
            }
        }
    }

    private boolean isZero(double number) {
        return Math.abs(number) < EPSILON;
    }


}
