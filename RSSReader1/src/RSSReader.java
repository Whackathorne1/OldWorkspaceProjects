import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Wyatt Hackathorne
 *
 */

public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        XMLTree title = channel.child(getChildElement(channel, "title"));
        XMLTree link = channel.child(getChildElement(channel, "link"));
        XMLTree description = channel
                .child(getChildElement(channel, "description"));
        //title is required to exist
        if (title.numberOfChildren() > 0) {
            out.println("<html><head><title>" + title.child(0).label()
                    + "</title>");
            out.println("</head><body>");
            out.println("<h1><a href=\"" + link.child(0).label() + "\">"
                    + title.child(0).label() + "</a></h1>");
        } else {
            out.println("<html><head><title>Empty Title</title>");
            out.println("</head><body>");
            out.println("<h1><a href=\"" + link.child(0).label()
                    + "\">No title available</a></h1>");
        }
        out.println("<p>");
        if (description.numberOfChildren() > 0) {
            out.println(description.child(0).label());
        } else {
            out.println("No description");
        }
        out.println("</p><table border=\"1\"><tr>");
        out.println("<th>Date</th><th>Source</th><th>News</th></tr>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        out.println("</table>");
        out.println("</body></html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        int index = -1;
        //tells weather the current tag has a child by giving the index a value of -1
        for (int i = 0; i < xml.numberOfChildren() && index < 0; i++) {
            if (xml.child(i).isTag() && xml.child(i).label().equals(tag)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        //call get child element and find the indices of all of the different tags
        //title, description, link, pub-date, source
        int titleIndex = getChildElement(item, "title");
        int desIndex = getChildElement(item, "description");
        int srcIndex = getChildElement(item, "source");
        int pdIndex = getChildElement(item, "pubDate");
        int linkIndex = getChildElement(item, "link");

        //initialize some string variables, hold default values
        String titleOrDes = "No title available";
        String srcUrl = "";
        String srcContents = "No Source Available";
        String pubDateContents = "No date available";
        String link = "";

        //check for link
        if (linkIndex != -1) {
            link = item.child(linkIndex).child(0).label();
        }

        //check for title/description
        if (titleIndex != -1 && item.child(titleIndex).numberOfChildren() > 0) {
            titleOrDes = item.child(titleIndex).child(0).label();
        } else if (desIndex != -1
                && item.child(desIndex).numberOfChildren() > 0) {
            titleOrDes = item.child(desIndex).child(0).label();
        }

        //source: check source exists, check source text child exists,
        if (srcIndex != -1) {
            XMLTree src = item.child(srcIndex);
            srcUrl = src.attributeValue("url");
            if (src.numberOfChildren() > 0) {
                srcContents = src.child(0).label();
            }
        }

        //check for pub-date
        if (pdIndex != -1) {
            XMLTree pd = item.child(pdIndex);
            pubDateContents = pd.child(0).label();
        }

        //prints
        out.println("<tr>");
        out.println("<td>" + pubDateContents + "</td>");

        if (srcUrl.length() > 0) {
            out.println("<td><a href=\"" + srcUrl + "\">" + srcContents
                    + "</a></td>");
        } else {
            out.println("<td>" + srcContents + "</td>");
        }
        if (link.length() > 0) {
            out.println(
                    "<td><a href=\"" + link + "\">" + titleOrDes + "</a></td>");
        } else {
            out.println("<td>" + titleOrDes + "</td>");
        }
        out.println("</tr>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //gets the url of the rss feed
        out.print("enter url: ");
        String userXML = in.nextLine();
        XMLTree xml = new XMLTree1(userXML);
        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {

            //validation that feed isTag, label = "rss"
            //has version attribute, and attribute val = 2.0)

            //get output file name
            out.println("output file name uncluding \".html\" ");
            String fileName = in.nextLine();
            SimpleWriter output = new SimpleWriter1L(fileName);

            XMLTree channel = xml.child(0);

            outputHeader(channel, output);
            //Process for all valid items
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).label().equals("item")) {
                    processItem(channel.child(i), output);
                }
            }
            outputFooter(output);

            in.close();
            out.close();
            output.close();
        }

    }
}
