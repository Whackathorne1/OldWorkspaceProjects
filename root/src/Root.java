import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Wyatt Hackathorne
 *
 */
public final class Root {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Root() {
    }
    private static NaturalNumber sum(NaturalNumber count) {
        NaturalNumber secondOne = new NaturalNumber2(1);
        NaturalNumber result = new NaturalNumber2(1);
        
        while (secondOne.compareTo(count) < 0) {
            result.multiply(secondOne);
            secondOne.increment();
        }
        return result;
    }
    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int result = 1, count = 0;
        while (count < p) {
            result *= n;
            count++;
        }
        return result;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int lowEnough = 0;
        int tooHigh = n + 1;
        //sets boundaries for answer ( 0 - 64 )
        int guess = (lowEnough + tooHigh) / 2;
        //sets guess to middle ( 32 )
        while (tooHigh - lowEnough > 1) {
            //if tooHigh and lowEnough are one away from each other stop
            if (n < power(guess, r)) {
                //if (our guess is too high)
                //(n)^1/r < guess
                //n < guess^r
                //move our higher boundary down
                tooHigh = guess;
            } else {
                //if (our guess is too low)
                //move our lower boundary up
                lowEnough = guess;
            }
            guess = (lowEnough + tooHigh) / 2;
            //update our guess now that we have new bounds
        }
        return guess;
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
        //example on homework: 82^1/3 = 4
        NaturalNumber n = new NaturalNumber2(10);
        n = sum(n);
        out.println(n);
//        NaturalNumber a = new NaturalNumber2(5);
//        NaturalNumber b = new NaturalNumber2(3);
//        //
//        a.subtract(b);
//        //
//        out.println();
//        b.transferFrom(a);
//        //
//        out.println();
//        NaturalNumber c = b;
//        //
//        NaturalNumber d = new NaturalNumber2(b);
//        out.println("a: " + a);
//        out.println("b: " + b);
//        //
//        out.println("before = c: " + c);
//        out.println("before = d: " + d);
//        out.println();
//        c.increment();
//        out.println("c after increment:" + c);
//        out.println();
//        //
//        d.multiply(b);
//        out.println("d after multi:" + d);
//        out.println("b after multi:" + b);
//        out.println();
//        //
//        c = a;
//        out.println("c after = :" + c);
//        out.println("a after = :" + a);
//        out.println();
//        //
//        a.copyFrom(d);
//        out.println("a after copyFrom :" + a);
//        out.println("d after copyFrom:" + d);
//        out.println();
//        //
//        c = d.divide(b);
//        out.println("FINAL = a: " + a);
//        out.println("FINAL = b: " + b);
//        out.println("FINAL = c: " + c);
//        out.println("FINAL = d: " + d);
        out.println("Root is: " + root(64, 2));
        //
        in.close();
        out.close();
    }

}
