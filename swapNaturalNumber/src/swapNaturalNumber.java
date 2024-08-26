import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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
public final class swapNaturalNumber {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private swapNaturalNumber() {
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber n5 = n.newInstance();
        n5.copyFrom(n);
        n.multiply(n5);

        out.print("n sqaured: " + n);
        out.close();
    }

    /**
     *
     * @param n1
     *            Number 1
     * @param n2
     *            Number 2
     */
    private static void swapNnTransferFrom(NaturalNumber n1, NaturalNumber n2) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber n4 = n1.newInstance();
        out.println("TrasferFrom: expected output");
        out.println(" n1: 1,  n2: 2");
        n4.transferFrom(n1);
        n1.transferFrom(n2);
        n2.transferFrom(n4);

        out.println("n1: " + n1);
        out.println("n2: " + n2);
        out.close();

    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNnCopyFrom(NaturalNumber n1, NaturalNumber n2) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("CopyFrom: expected output");
        out.println(" n1: 2,  n2: 1");
        //creates copy of n1 to copy for n2 after n1 was been copied from n2
        NaturalNumber n3 = n1.newInstance();
        n3.copyFrom(n1);
        n1.copyFrom(n2);
        n2.copyFrom(n3);
        //prints
        out.println("n1: " + n1);
        out.println("n2: " + n2);
        out.close();
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
        NaturalNumber n1 = new NaturalNumber1L(1);
        NaturalNumber n2 = new NaturalNumber1L(2);
        out.println("Original output: ");
        out.println("n1: " + n1);
        out.println("n2: " + n2);
        swapNnCopyFrom(n1, n2);
        swapNnTransferFrom(n1, n2);
        out.print("Please enter a natural number. ");
        int k = in.nextInteger();
        NaturalNumber n = new NaturalNumber2(k);
        square(n);

        in.close();
        out.close();
    }

}
