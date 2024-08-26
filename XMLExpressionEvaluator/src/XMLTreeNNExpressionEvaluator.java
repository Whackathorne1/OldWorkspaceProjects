import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;
import components.utilities.Reporter;

/**
 * 
 * @author Wyatt Hackathorne
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    @SuppressWarnings("unused")
    private static int evaluate(XMLTree exp) {
        int result = 0;
        String op = exp.label();
        //if expression is empty set result to 0
        if (exp.label().equals("number")) {
            result = Integer.parseInt(exp.attributeValue("value"));
        } else {
            int leftX = evaluate(exp.child(0));
            int rightX = evaluate(exp.child(1));
            //plus, minus, times, divide if statements to classify values
            if (op.equals("plus")) {
                result = leftX + rightX;
            } else if (op.equals("minus")) {
                result = leftX - rightX;
            } else if (op.equals("times")) {
                result = leftX * rightX;
            } else if (op.equals("divide")) {
                result = leftX / rightX;
            }
        }
        return result;
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluateNN(XMLTree exp) {
        NaturalNumber result = new NaturalNumber1L();
        NaturalNumber zero = new NaturalNumber1L(0);
        if (exp.label().equals("number")) {
            result = new NaturalNumber1L(exp.attributeValue("value"));
        } else {
            XMLTree fst = exp.child(0);
            XMLTree sec = exp.child(1);
            String op = exp.label();
            NaturalNumber evalNN = evaluateNN(sec);
            result.copyFrom(evaluateNN(fst));
            //plus, minus, times, divide if statements to classify values
            if (op.equals("plus")) {
                result.add(evalNN);
            } else if (op.equals("minus")) {
                if (result.compareTo(evalNN) >= 0) {
                    result.subtract(evalNN);
                } else {
                    Reporter.fatalErrorToConsole("Error: Number on the left side"
                            + " of the expression is greater than the number on"
                            + "the right. Expression yeilds a negative number "
                            + "which is not allowed for a natural number. ");
                }
            } else if (op.equals("times")) {
                result.multiply(evalNN);
            } else if (op.equals("divide")) {
                if (evalNN.compareTo(zero) > 0) {
                result.divide(evalNN);
                } else {
                    Reporter.fatalErrorToConsole("Error: Number on the left is"
                            + " less than 0. Divide by zero error.");
                }
            }
        }
        return result;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluateNN(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }
        in.close();
        out.close();
    }

}
