import java.util.Objects;

public class ExtendedClass {

    private byte b;
    private int i;
    private double d;
    private String s;

    // ************************************************************************
    // getters and setters
    // ************************************************************************

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    // ************************************************************************

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExtendedClass that = (ExtendedClass) o;
        return b == that.b && i == that.i && Double.compare(that.d, d) == 0 && Objects.equals(s, that.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(b, i, d, s);
    }

    @Override
    public String toString() {
        return "ExtendedClass{" +
                "b=" + b +
                ", i=" + i +
                ", d=" + d +
                ", s='" + s + '\'' +
                '}';
    }
}