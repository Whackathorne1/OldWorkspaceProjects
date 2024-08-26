import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/*
 * Original Monte Carlo program
 */
public class MonteCarlo1 {
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Number of points: ");
        int n = in.nextInteger();
        out.print("Radius of Circle: ");
        int radius = in.nextInteger();
        out.print("Range of Area: ");
        double range = in.nextDouble();
        //counter for hits and misses
        int ptsInInterval = 0, ptsInSubinterval = 0;
        //generates random number to be evaluated
        Random rnd = new Random1L();
        //goes through amount of points you input and stops at that #
        while (ptsInInterval < n) {
            double x = rnd.nextDouble() * range;
            double y = rnd.nextDouble() * range;
            ptsInInterval++;
            //sees weather or not the point was inside the circle or not
            if (((x - 1) * (x - 1) + (y - 1) * (y - 1)) < radius) {
                ptsInSubinterval++;
            }
        }
        //estimates the radius of the circle based on hits and misses
        double estimate = ((range * range) * ptsInSubinterval) / ptsInInterval;
        out.println();
        out.println("Estimate: " + estimate);

        in.close();
        out.close();
    }
}
