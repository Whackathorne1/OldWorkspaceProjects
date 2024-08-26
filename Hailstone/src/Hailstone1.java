import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int term = n;
        out.print(term + " ");
        while (term > 0 && term != 1) {
            if (term % 2 == 0) {
                term = term / 2;
            } else { //odd
                term = term * 3 + 1;
            }
            out.print(term + " ");
        }

//        while (n % 2 == 0) {
//            n = (n * 3) + 1;
//            out.print(n);
//        }
//        if (n % 2 != 0) {
//            n = n / 2;
//            out.print(n);
//        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the starting number: ");
        int n = in.nextInteger();
        generateSeries(n, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
