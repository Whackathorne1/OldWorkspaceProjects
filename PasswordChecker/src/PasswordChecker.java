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
public final class PasswordChecker {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private PasswordChecker() { // constructor
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
        out.print("Please enter your password or help: ");
        String password = in.nextLine();
        if (password.equalsIgnoreCase("help")) {
            out.println("password must meet these requirements: ");
            out.println("Must contain 8 characters.");
            out.println("Must contain 1 upper case letter.");
            out.println("Must contain 1 numeric digit.");
        }
        checkPassword(password, out, 8);
        boolean x = containsUpperCaseLetter(password);
        if (x != true) {
            out.println("Password does not contain a Upper case letter.");
        }
        out.println("Restart? (y/n)");
        while (in.nextLine().equals("y") || in.nextLine().equals("y")) {
            out.print("Please enter your password or help: ");
            password = in.nextLine();
            if (password.equalsIgnoreCase("help")) {
                out.println("password must meet these requirements: ");
                out.println("Must contain 8 characters.");
                out.println("Must contain 1 upper case letter.");
                out.println("Must contain 1 numeric digit.");
            }
            checkPassword(password, out, 8);
            x = containsUpperCaseLetter(password);
            if (x != true) {
                out.println("Password does not contain a Upper case letter.");
            } else {
                out.println("Password contains an Upper case letter.");
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean x = false;
        boolean y = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                x = true;
            }
            if (Character.isLowerCase(s.charAt(i))) {

            }
        }

        return x;
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out,
            int pwordlength) {

        if (s.length() > pwordlength) { //pwordlenth = 8
            out.println("Password meets length requirement.");
        } else {
            out.println("Password does not meet length requirement.");
        }
    }
}
