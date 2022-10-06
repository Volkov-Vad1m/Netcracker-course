package ru.skillbench.tasks.basics.math;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumberImpl implements ComplexNumber{
    public final static double EPSILON = 10E-12;

    private double re;
    private double im;

    public ComplexNumberImpl() {}

    public ComplexNumberImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }
    /**
     * @return real part of this complex number
     */
    @Override
    public double getRe() {
        return re;
    }

    /**
     * @return imaginary part of this complex number
     */
    @Override
    public double getIm() {
        return im;
    }

    /**
     * @return true if this complex number has real part only (otherwise false)
     */
    @Override //done
    public boolean isReal() {
        return Double.compare(0, im) == 0;
    }

    /**
     * Sets both real and imaginary part of this number.
     *
     * @param re
     * @param im
     */
    @Override //done
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * Parses the given string value and sets the real and imaginary parts of this number accordingly.<br/>
     * The string format is "re+imi", where re and im are numbers (floating point or integer) and 'i' is a special symbol
     * denoting imaginary part (if present, it's always the last character in the string).<br/>
     * Both '+' and '-' symbols can be the first characters of re and im; but '*' symbol is NOT allowed.<br/>
     * Correct examples: "-5+2i", "1+i", "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j".<br/>
     * Note: explicit exception generation is an OPTIONAL requirement for this task,
     * but NumberFormatException can be thrown by {@link Double#parseDouble(String)}).<br/>
     * Note: it is not reasonable to use regex while implementing this method: the parsing logic is too complicated.
     *
     * @param value
     * @throws NumberFormatException if the given string value is incorrect
     */
    @Override
    public void set(String value) throws NumberFormatException {
        if (value.endsWith("i")) {
            Pattern pattern = Pattern.compile("([+-]?\\d*\\.?\\d*)([+-]?\\d*\\.?\\d*)i",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(value);

            if(matcher.matches()) {
                String reStr = matcher.group(1);
                String imStr = matcher.group(2);

                if (reStr.equals("-")) {
                    re = 0.0;
                    im = -1.0;
                } else if (reStr.equals("+")) {
                    re = 0.0;
                    im = 1.0;
                } else {
                    re = Double.parseDouble(reStr);

                    if (imStr.equals("")) {
                        im = re;
                        re = 0.0;
                    } else if (imStr.equals("-")) {
                        im = -1.0;
                    } else if (imStr.equals("+")) {
                        im = 1.0;
                    } else {
                        im = Double.parseDouble(imStr);
                    }
                }
            } else {
                throw new NumberFormatException();
            }
        } else {
            Pattern pattern = Pattern.compile("([+-]?\\d*\\.?\\d*)",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(value);

            if(matcher.matches()) {
                re = Double.parseDouble(matcher.group(1));
                im = 0.0;
            } else {
                throw new NumberFormatException();
            }
        }

    }

    /**
     * Creates and returns a copy of this object: <code>x.copy().equals(x)</code> but <code>x.copy()!=x</code>.
     *
     * @return the copy of this number
     * @see #clone()
     */
    @Override //done
    public ComplexNumber copy() {
        ComplexNumber number = new ComplexNumberImpl();
        number.set(this.re, this.im);
        return number;
    }

    /**
     * Creates and returns a copy of this object: the same as {@link #copy()}.<br/>
     * Note: when implemented in your class, this method overrides the {@link Object#clone()} method but changes
     * the visibility and the return type of the Object's method: the visibility modifier is changed
     * from protected to public, and the return type is narrowed from Object to ComplexNumber (see also
     * <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#d5e11368">covariant types example from JLS</a>).
     *
     * @return the copy of this number
     * @see Object#clone()
     */
    @Override //done
    public ComplexNumber clone() throws CloneNotSupportedException {
        return (ComplexNumber) super.clone();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ComplexNumber) {
            return (compareTo((ComplexNumber) other) == 0) ? true : false;
        } else {
            return false;
        }
    }
    
    /**
     * Compares this number with the other number by the absolute values of the numbers:
     * x < y if and only if |x| < |y| where |x| denotes absolute value (modulus) of x.<br/>
     * Can also compare the square of the absolute value which is defined as the sum
     * of the real part squared and the imaginary part squared: |re+imi|^2 = re^2 + im^2.
     *
     * @param other the object to be compared with this object.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the given object.
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(ComplexNumber other) {
        return Double.compare(re*re + im*im,
                other.getRe() * other.getRe() + other.getIm()*other.getIm());
    }

    /**
     * Sorts the given array in ascending order according to the comparison rule defined in
     * {@link #compareTo(ComplexNumber)}.<br/>
     * It's strongly recommended to use {@link Arrays} utility class here
     * (and do not transform the given array to a double[] array).<br/>
     * Note: this method could be static: it does not use this instance of the ComplexNumber.
     * Nevertheless, it is not static because static methods can't be overridden.
     *
     * @param array an array to sort
     */
    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    /**
     * Changes the sign of this number. Both real and imaginary parts change their sign here.
     *
     * @return this number (the result of negation)
     */
    @Override
    public ComplexNumber negate() {
        this.set(-this.re, -this.im);
        return this;
    }

    /**
     * Adds the given complex number arg2 to this number. Both real and imaginary parts are added.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the sum)
     */
    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.re += arg2.getRe();
        this.im += arg2.getIm();
        return this;
    }

    /**
     * Multiplies this number by the given complex number arg2. If this number is a+bi and arg2 is c+di then
     * the result of their multiplication is (a*c-b*d)+(b*c+a*d)i<br/>
     * The method should work correctly even if arg2==this.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the result of multiplication)
     */
    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double oldRe = this.re;
        double oldRe2 = arg2.getRe();
        this.re = this.re * arg2.getRe() - this.im * arg2.getIm();
        this.im = oldRe * arg2.getIm() + oldRe2 * this.im;
        return this;
    }
}
