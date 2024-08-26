import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
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

        XMLTree menu = new XMLTree1("https://www.w3schools.com/xml/simple.xml");
//        xml.display();
//        xml.toString();
//        out.print(xml.toString());
//        xml.label();
//        xml.isTag();
//        xml.numberOfChildren();
//        XMLTree results = xml.child(1);
//        XMLTree channel = results.child(0);
//        channel.numberOfChildren();
//        XMLTree title = channel.child(0);

        out.print(menu.child(1).child(0).child(0).label());
        in.close();
        out.close();
    }
}
