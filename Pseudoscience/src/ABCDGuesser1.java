import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Project # 3 (Pseudoscience) program that asks the user what constant μ should
 * be approximated, then uses values w, x, y, and z to calculated it using the
 * array of exponents in "charmingTheory()" method.
 *
 * @author Wyatt Hackathorne
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @param input
     *            the initial user input
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out,
            String input) {
        double valid;
        String result = input;
        while (!FormatChecker.canParseDouble(result)
                || Double.parseDouble(result) <= 0) {
            out.println("invalid input, please enter a postive real number");
            result = in.nextLine();
        }
        valid = Double.parseDouble(result);
        return valid;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @param input
     *            the initial user input
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out, String input) {
        double valid;
        String result = input;
        while (!FormatChecker.canParseDouble(result)
                || Double.parseDouble(result) <= 0
                || Double.parseDouble(result) == 1) {
            out.println("invalid input, please enter a postive real number");
            result = in.nextLine();
        }
        valid = Double.parseDouble(result);
        return valid;
    }

    /**
     * Description of the method.
     *
     * @param in
     *            SimpleReader object for input
     * @param out
     *            SimpleWriter object for output
     * @param mu
     *            Number to be estimated using deJager method
     * @param w
     *            First #, used to estimate @param mu
     * @param x
     *            Second #, used to estimate @param mu
     * @param y
     *            Third #, used to estimate @param mu
     * @param z
     *            Fourth #, used to estimate @param mu
     */
    private static void charm(SimpleReader in, SimpleWriter out, double mu,
            double w, double x, double y, double z) {

        double[] charmA = { -5, -4, -3, -2, -1, -0.5, -1 / 3, -0.25, 0, 0.25,
                1 / 3, 0.5, 1, 2, 3, 4, 5 };
        //fix proper double division
        //so that 1/3 and -1/3 are not seen as expressions
        charmA[6] = 1.0 / 3.0;
        charmA[10] = -1.0 / 3.0;
        double bstEst = 0; //best estimate
        String bstInd = ""; //best index values
        double margin = 0;
        int i = 0, n = 0, j = 0, k = 0;
        while (i < charmA.length) {
            while (j < charmA.length) {
                while (k < charmA.length) {
                    while (n < charmA.length) {
                        double mx1 = (Math.pow(w, charmA[i]));
                        double mx2 = (Math.pow(x, charmA[j]));
                        double mx3 = (Math.pow(y, charmA[k]));
                        double mx4 = (Math.pow(z, charmA[n]));
                        double currentEstimate = mx1 * mx2 * mx3 * mx4;

                        if (Math.abs(mu - currentEstimate) <= Math
                                .abs(mu - bstEst)) {
                            bstEst = currentEstimate;
                            bstInd = "" + charmA[i] + ", " + charmA[j] + ", "
                                    + charmA[k] + ", " + charmA[n];
                            margin = ((Math.abs(mu - currentEstimate)) / mu);
                        }
                        n++;
                    }
                    n = 0;
                    k++;
                }
                n = 0;
                k = 0;
                j++;
            }
            n = 0;
            k = 0;
            j = 0;
            i++;
        }
        out.println("Best Estimate = " + bstEst);
        out.println("Best Indexes = " + bstInd);
        //final constant to avoid magic number warning
        //cPRECISION to adhere to naming conventions
        //4 for number of decimal places
        final int cPRECISION = 4;
        out.print("Error Margin = ");
        out.println(margin, cPRECISION, false);
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

        out.print("Please enter a postive real number for u ");
        String muInput = in.nextLine();
        double mu = getPositiveDouble(in, out, muInput);
        out.print(
                "Please enter a random postive real number not equal to 1.0 for w ");
        String inputW = in.nextLine();
        double validW = getPositiveDoubleNotOne(in, out, inputW);
        out.print(
                "Please enter a random postive real number not equal to 1.0 for x ");
        String inputX = in.nextLine();
        double validX = getPositiveDoubleNotOne(in, out, inputX);
        out.print(
                "Please enter a random postive real number not equal to 1.0 for y ");
        String inputY = in.nextLine();
        double validY = getPositiveDoubleNotOne(in, out, inputY);
        out.print(
                "Please enter a random postive real number not equal to 1.0 for z ");
        String inputZ = in.nextLine();
        double validZ = getPositiveDoubleNotOne(in, out, inputZ);

        charm(in, out, mu, validW, validX, validY, validZ);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
