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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */

    private CoinChange1(int n) {

    }

    private static void makeChange(int cents, SimpleWriter out) {
        int dollar = 0, halfDollar = 0, quarter = 0, dime = 0, nickel = 0,
                penny = 0;
        if (cents >= 100) {
            dollar = cents / 100;
            cents = cents - (dollar * 100);
        }
        if (cents >= 50) {
            halfDollar = cents / 50;
            cents = cents - (halfDollar * 50);
        }
        if (cents >= 25) {
            quarter = cents / 25;
            cents = cents - (quarter * 25);
        }
        if (cents >= 10) {
            dime = cents / 10;
            cents = cents - (dime * 10);
        }
        if (cents >= 5) {
            nickel = cents / 5;
            cents = cents - (nickel * 5);
        }
        if (cents >= 1) {
            penny = cents / 1;
            cents = cents - (penny * 1);
        }
        out.println("Your change is " + dollar + " dollars, " + halfDollar
                + " half dollars, " + quarter + " quarters, " + dime
                + " dimes, " + nickel + " nickels, and " + penny + " pennies.");
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
