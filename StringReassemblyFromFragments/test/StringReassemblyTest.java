import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

/**
 *
 * @author Wyatt Hackathorne
 *
 */
public class StringReassemblyTest {

    /**
     * Routine test.
     */
    @Test
    public void testCombination1() {
        String str1 = "idontalwaystest";
        String str2 = "dontalwaystestmethodsbutwhenido";
        int overlap = 14;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("idontalwaystestmethodsbutwhenido", result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testCombination2() {
        String str1 = "pleasedontremoveme";
        String str2 = "removemefromthestring";
        int overlap = 8;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("pleasedontremovemefromthestring", result);
    }

    /**
     * Routine/challenge test.
     */
    @Test
    public void testCombination3() {
        String str1 = "Igotmymotorcyclestolenandimsadnow";
        String str2 = "Igotmymotorcyclestolenandimsadnow";
        int overlap = 33;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Igotmymotorcyclestolenandimsadnow", result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testCombination4() {
        String str1 = "ireallywanttosleepb";
        String str2 = "becauseimtired";
        int overlap = 1;

        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("ireallywanttosleepbecauseimtired", result);
    }

    /**
     * Boundary test.
     */
    @Test
    public void testCombination5() {
        String str1 = "ireallywanttosleep";
        String str2 = "becauseimtired";
        int overlap = 0;

        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("ireallywanttosleepbecauseimtired", result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testaddToSetAvoidingSubstrings6() {
        Set<String> strset = new Set1L<String>();
        strset.add("Hey its joeman!");
        strset.add("Helloooo brooo");
        strset.add("I am leaving");
        strset.add("Testone");

        Set<String> strset2 = new Set1L<String>();
        strset2.add("Hey its joeman!");
        strset2.add("I am leaving");
        strset2.add("Helloooo brooo");
        strset2.add("Testone");

        String str = "Test";
        StringReassembly.addToSetAvoidingSubstrings(strset, str);
        assertEquals(strset2, strset);
    }

    /**
     * Boundary test.
     */
    @Test
    public void testaddToSetAvoidingSubstrings7() {
        Set<String> strset = new Set1L<String>();
        strset.add("Hey its joeman!");
        strset.add("Helloooo brooo");
        strset.add("I am leaving");
        strset.add("xyz");
        strset.add("testtwothreefour");

        Set<String> strset2 = new Set1L<String>();
        strset2.add("Hey its joeman!");
        strset2.add("Helloooo brooo");
        strset2.add("I am leaving");
        strset2.add("xyz");
        strset2.add("testtwo");


        String str = "testtwo";
        StringReassembly.addToSetAvoidingSubstrings(strset, str);
        assertNotEquals(strset, strset2);
    }
}
