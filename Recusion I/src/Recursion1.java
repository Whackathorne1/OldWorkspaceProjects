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
public final class Recursion1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Recursion1() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        int total = 0;
        if (!n.isZero()) {
            n.divideBy10();
            total++;
            total += numberOfDigits(n);
        }
        return total;
    }
    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits2(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber sum = new NaturalNumber2(n);
        if (n.compareTo(zero) > 0) {
            NaturalNumber x = new NaturalNumber2(n.divideBy10());
            sum.divideBy10();
            x.add(sumOfDigits2(sum));
        }
        return sum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {

    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean ans = false;
        if (s.length() <= 1) {
            ans = true;
        } else {
            char first = s.charAt(0);
            char last = s.charAt(s.length() - 1);
            String center = s.substring(1, s.length() - 1);
            if (first == last && isPalindrome(center)) {
                ans = true;
            }

        }
        return ans;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        in.close();
        out.close();
    }
}
