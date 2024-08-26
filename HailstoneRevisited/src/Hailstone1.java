import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber max = new NaturalNumber2(n);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);
        int count = 0;
        out.print(n + " ");
        while (!n.equals(one)) {

            NaturalNumber copy = new NaturalNumber2(n);
            if (copy.divide(two).isZero()) {
                n.divide(two);
            } else {
                n.multiply(three);
                n.add(one);
            }
            if (n.compareTo(copy) == 1) {
                max = n;
            }
            count++;
            out.print(n + ", ");
        }
        out.println();
        out.println("Series length is: " + count);
        out.print("Max term is: " + max);

    }

    private static NaturalNumber hailSum(NaturalNumber x, NaturalNumber n) {
        NaturalNumber sum = new NaturalNumber2();
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);

        NaturalNumber currentTerm = new NaturalNumber2(x);
        NaturalNumber termsLeft = new NaturalNumber2(n);
        while (!termsLeft.isZero()) {
            sum.add(currentTerm);
            NaturalNumber remainder = new NaturalNumber2(x.divide(two));
            if (remainder.isZero()) {
                currentTerm.increment();
                currentTerm.multiply(two);

                currentTerm.increment();
                currentTerm.multiply(three);
            }
            termsLeft.decrement();
        }
        return sum;
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
        NaturalNumber x = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(3);
        out.print(hailSum(x, n));
//        out.print("Please enter a natural number. ");
//        int k;
//        k = in.nextInteger();
//        NaturalNumber n = new NaturalNumber2(k);
//
//        generateSeries(n, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
