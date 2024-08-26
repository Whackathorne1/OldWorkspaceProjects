import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program description; Newton's iteration: calculates a estimation for the root
 * of a number "root"
 *
 * @author Wyatt Hackathorne
 */
public class NewtonsIteration {
    /*
     * The Final program after Newton.java 1 - 4
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        System.out.println("Please enter a root to be calculated: ");
        double root = in.nextDouble();

        if (root == 0) {
            out.print("ERROR: division by 0");
        } else {
            System.out.println("Please enter what epsilon is: ");
            double eps = in.nextDouble();

            double actual = Math.sqrt(root);

            System.out.println("The estimation is: " + sqrt(root, eps));
            System.out.println("The actual sqrt is: " + actual);

            out.println();
            out.print("Please enter a root to be calculated");
            out.println(" or a negative number to quit: ");
            root = in.nextDouble();
            while (root >= 0) {
                System.out.println("Please enter what epsilon is: ");
                eps = in.nextDouble();

                actual = Math.sqrt(root);

                System.out.println("The estimation is: " + sqrt(root, eps));
                System.out.println("The actual sqrt is: " + actual);

                out.print("Please enter a root to be calculated");
                out.println(" or a negative number to quit: ");
                root = in.nextDouble();
            }
            out.print("Program will now exit...");
        }
        in.close();
        out.close();
    }

    /**
     * Computes estimate of square root of x to within relative error of 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     *
     * @return estimate of square root
     */
    private static double sqrt(double x, double eps) {
        // x and epsilon prompted from user for calculation
        double r = 1;
        while ((Math.abs(r * r - x)) > (eps * eps * x)) {
            r = 0.5 * (r + (x / r));
        }
        return r;
    }
}
