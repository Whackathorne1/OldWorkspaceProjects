import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Oddity {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Oddity() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final int microsPerDay = 24 * 60 * 60 * 1000 * 1000;
        final int millisPerDay = 24 * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);

        double x = 456.0;
        double y = 100.0 * 4.56;
        if (Double.compare(x, y) == 0) {
            out.println("equal");
        } else {
            out.println("not equal");
        }
        out.close();
    }
}
