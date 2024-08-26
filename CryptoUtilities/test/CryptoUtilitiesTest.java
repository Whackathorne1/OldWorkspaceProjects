import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Wyatt Hackathorne
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21_99_22() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);

        NaturalNumber k = new NaturalNumber2(99);
        NaturalNumber kExpected = new NaturalNumber2(11);
        NaturalNumber r = new NaturalNumber2(22);
        NaturalNumber rExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        CryptoUtilities.reduceToGCD(k, r);
        assertEquals(kExpected, k);
        assertEquals(rExpected, r);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0_52_1000() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);

        NaturalNumber m = new NaturalNumber2(52);
        NaturalNumber mExpected = new NaturalNumber2(52);
        boolean result2 = CryptoUtilities.isEven(n);

        NaturalNumber k = new NaturalNumber2(1000);
        NaturalNumber kExpected = new NaturalNumber2(1000);
        boolean result3 = CryptoUtilities.isEven(n);

        assertEquals(nExpected, n);
        assertEquals(true, result);
        assertEquals(mExpected, m);
        assertEquals(true, result2);
        assertEquals(kExpected, k);
        assertEquals(true, result3);

    }

    @Test
    public void testIsEven_1_33_111_5555() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);

        NaturalNumber m = new NaturalNumber2(33);
        NaturalNumber mExpected = new NaturalNumber2(33);
        boolean result2 = CryptoUtilities.isEven(n);

        NaturalNumber k = new NaturalNumber2(111);
        NaturalNumber kExpected = new NaturalNumber2(111);
        boolean result3 = CryptoUtilities.isEven(n);

        NaturalNumber r = new NaturalNumber2(5555);
        NaturalNumber rExpected = new NaturalNumber2(5555);
        boolean result4 = CryptoUtilities.isEven(n);

        assertEquals(rExpected, r);
        assertEquals(false, result4);
        assertEquals(kExpected, k);
        assertEquals(false, result3);
        assertEquals(mExpected, m);
        assertEquals(false, result2);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);

        NaturalNumber n1 = new NaturalNumber2(2);
        NaturalNumber n1Expected = new NaturalNumber2(0);
        NaturalNumber p1 = new NaturalNumber2(3);
        NaturalNumber p1Expected = new NaturalNumber2(3);
        NaturalNumber m1 = new NaturalNumber2(2);
        NaturalNumber m1Expected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n1, p1, m1);

        assertEquals(n1Expected, n1);
        assertEquals(p1Expected, p1);
        assertEquals(m1Expected, m1);

        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

}