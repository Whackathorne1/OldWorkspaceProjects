import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author wyatt h
 *
 */
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
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
        int count = 0;
        int max = n;
        out.print(term + " ");
        while (term > 0 && term != 1) {
            if (term % 2 == 0) {
                term = term / 2;
            } else { //odd
                term = term * 3 + 1;
            }
            if (term > max) {
                max = term;
            }
            count++;
            out.print(term + " ");
        }
        out.println();
        out.println("Series length is: " + count);
        out.print("Max term is: " + max);
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
