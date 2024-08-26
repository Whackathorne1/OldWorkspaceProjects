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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */

    private CoinChange2(int n) {

    }

    private static void makeChange(int cents, SimpleWriter out) {
        int[] denoms = { 100, 50, 25, 10, 5, 1 };
        int[] coinCount = { 0, 0, 0, 0, 0, 0 };
        //iterating through entire array with index i
        //for arrays, the length is a private field so we just do .length
        //length(array) - not a method for arrays
        for (int i = 0; i < denoms.length; i++) {
            //generalizing body of if statement on coinchange1
            coinCount[i] = cents / denoms[i];
            cents = cents - (coinCount[i] * denoms[i]);
            out.println("You have " + coinCount[i] + " coins of value "
                    + denoms[i] + ".");
        }
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
        out.print("Please enter the change amount: ");
        int n = in.nextInteger();
        makeChange(n, out);
        in.close();
        out.close();
    }
}
