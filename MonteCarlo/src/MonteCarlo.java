import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/*
 * Improved Monte Carlo with static methods
 * @author Wyatt Hackathorne
 */
public class MonteCarlo {

    
    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        return ((xCoord - 1) * (xCoord - 1) + (yCoord - 1) * (yCoord - 1)) <= 1;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    public static int numberOfPointsInCircle(int n) {
        int ptsInInterval = 0, ptsInSubinterval = 0;
        Random rnd = new Random1L();
        int intervalRange = 2;
        while (ptsInInterval < n) {
            /*
             * Generates a random number in [0.0,2.0) interval
             */
            double x = rnd.nextDouble() * intervalRange;
            double y = rnd.nextDouble() * intervalRange;
            ptsInInterval++;
            /*
             * Checks if the point is in the interval
             * if it is, increments "ptsInSubinterval"
             */
            if (pointIsInCircle(x, y)) {
                ptsInSubinterval++;
            }
        }
        return ptsInSubinterval;
    }
    /*
     * Main Method
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        double range = 2;
        out.print("Number of points: ");
        int n = in.nextInteger();
        int ptsInSubInterval = numberOfPointsInCircle(n);

        double estimate = (ptsInSubInterval * (range * range) / n);
        out.println("Estimate of percentage: " + estimate + "%");
        in.close();
        out.close();
    }
}
