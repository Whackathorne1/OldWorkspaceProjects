import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program description; Newton's iteration: calculates a estimation for the root
 * of a number "root".
 *
 * @author Wyatt Hackathorne
 */
public class Newton1 {
    /*
     * main method
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        System.out.println("Please enter a postive root to be calculated:");
        double root = in.nextDouble();

        if (root == 0) {
            out.print("ERROR: division by 0");
        } else {
            double actual = Math.sqrt(root);

            System.out.println("The estimation is: " + sqrt(root));
            System.out.println("The actual sqrt is: " + actual);

            out.println();
            out.println("Would you like to try again? (y/n)");
            while (in.nextLine().equals("y") || in.nextLine().equals("y")) {
                System.out.println(
                        "Please enter a postive root to be calculated:");
                root = in.nextDouble();
                actual = Math.sqrt(root);

                System.out.println("The estimation is: " + sqrt(root));
                System.out.println("The actual sqrt is: " + actual);

                out.println();
                out.println("Would you like to try again? (y/n)");
            }
            out.print("Program will now exit...");
        }
        in.close();
        out.close();
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     *
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = 1;
        double epsilon = 0.0001;
        //newton's iteration method
        while ((Math.abs(r * r - x)) / x > (epsilon * epsilon)) {
            r = 0.5 * (r + (x / r));
        }
        return r;
    }
}
