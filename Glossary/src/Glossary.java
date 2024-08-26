import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
/**
 * @author Wyatt Hackathorne
 */
public final class Glossary {
 /**
 * Default constructor.
 */
 private Glossary() {
 }
     /**
      *
     * @param str
     * @param strSet
     */
     private static void generateElements(String str, Set<Character> strSet) {
         assert str != null : "Violation of: str is not null";
         assert strSet != null : "Violation of: strSet is not null";
         for (int i = 0; i < str.length(); i++) {
             if (!strSet.contains(str.charAt(i))) {
                 strSet.add(str.charAt(i));
             }
         }
     }
     /**
      * Returns the first "word" (maximal length string of characters not in
      * {@code separators}) or "separator string" (maximal length string of
      * characters in {@code separators}) in the given {@code text} starting at
      * the given {@code position}.
      *
      * @param text
      *            the {@code String} from which to get the word or separator
      *            string
      * @param pos
      *            the starting index
      * @param separators
      *            the {@code Set} of separator characters
      * @return the first word or separator string found in {@code text} starting
      *         at index {@code position}
      * @requires 0 <= position < |text|
      * @ensures <pre>
      * nextWordOrSeparator =
      *   text[position, position + |nextWordOrSeparator|)  and
      * if entries(text[position, position + 1)) intersection separators = {}
      * then
      *   entries(nextWordOrSeparator) intersection separators = {}  and
      *   (position + |nextWordOrSeparator| = |text|  or
      *    entries(text[position, position + |nextWordOrSeparator| + 1))
      *      intersection separators /= {})
      * else
      *   entries(nextWordOrSeparator) is subset of separators  and
      *   (position + |nextWordOrSeparator| = |text|  or
      *    entries(text[position, position + |nextWordOrSeparator| + 1))
      *      is not subset of separators)
      * </pre>
      */
     private static String nextWordOrSeparator(String text, int pos,
             Set<Character> separators) {
         assert text != null : "Violation of: text is not null";
         assert separators != null : "Violation of: separators is not null";
         assert 0 <= pos : "Violation of: 0 <= position";
         assert pos < text.length() : "Violation of: position < |text|";
         int count = 0;
         char nChar = 'a';
         String fString = "";
         if (separators.contains(text.charAt(pos))) {
             while (count < text.substring(pos, text.length()).length()) {
                 nChar = text.charAt(pos + count);
                 if (separators.contains(text.charAt(pos + count))) {
                     fString += nChar;
                     count++;
                 } else {
                     count = text.substring(pos, text.length()).length();
                 }
             }
             count = 0;
         } else {
             while (count < text.substring(pos, text.length()).length()) {
                 nChar = text.charAt(pos + count);
                 if (!separators.contains(text.charAt(pos + count))) {
                     fString += nChar;
                     count++;
                 } else {
                     count = text.substring(pos, text.length()).length();
                 }
             }
             count = 0;
         }
         return fString;
     }
     /**
      * @param input
      * @return
      *         single term out of index
      */
     private static String getTerms(SimpleReader input) {
         assert input != null : "Violation of: input is not null";
         String terms = input.nextLine();
         String result = "";
         while (!terms.equals("") && !input.atEOS()) {
             result += terms;
             terms = input.nextLine();
             String nextTerm = terms;
             if (!terms.equals("")) {
                 result += "|";
             }
             terms = nextTerm;
         }
         return result + terms;
     }
     /**
      * @param map
      * @param q
      */
     private static void replaceTerm(Map<String, String> map,
             Queue<String> q) {
         final String separatorStr = " \t:,;.-_?!";
         Set<Character> separatorSet = new Set1L<>();
         generateElements(separatorStr, separatorSet);
         Map<String, String> tempKeyMap = new Map1L<>();
         tempKeyMap.transferFrom(map);
         while (tempKeyMap.size() != 0) {
             Map.Pair<String, String> element = tempKeyMap.removeAny();
             String result = " ";
             String key = element.key();
             String value = element.value();
             String tokenTemp = " ";
             int pos = 0;
             while (pos < value.length()) {
                 String token = nextWordOrSeparator(value, pos,
                         separatorSet);
                 tokenTemp = token;
                 if (!separatorSet.contains(token.charAt(0))) {
                     Queue<String> tempQ = q.newInstance();
                     tempQ.transferFrom(q);
                     while (tempQ.length() > 0) {
                         String key2 = tempQ.dequeue();
                         if (token.equals(key2)) {
                             tokenTemp = "<a href=\"" + key2 + ".html\">"
                                 + key2 + "</a>";
                         }
                         q.enqueue(key2);
                     }
                 }
                 pos += token.length();
                 result += tokenTemp;
             }
             map.add(key, result);
         }
     }
     /**
      * @param map
      * @return
      *         All Keys inside of the map into a queue
      */
     private static Queue<String> keysInQueue(Map<String, String> map) {
         Map<String, String> tempMap = new Map1L<>();
         Queue<String> q = new Queue1L<>();
         tempMap.transferFrom(map);
         while (tempMap.size() != 0) {
             Map.Pair<String, String> pair = tempMap.removeAny();
             String key = pair.key();
             String value = pair.value();
             q.enqueue(key);
             map.add(key, value);
         }
         return q;
     }
     /**
      * @param line
      * @param map
      */
     private static void addElementsToMap(String line, Map<String, String> map) {
         assert line != null : "Violation of: fileName is not null";
         String element = line.substring(0, line.indexOf("|"));
         String def = line.substring(line.indexOf("|") + 1, line.length());
         if (!map.hasKey(element)) {
             map.add(element, def);
         }
  }
     /**
      * @param key
      * @param val
      * @param folderName
      */
     private static void popFile(String key, String val, String folderName) {
         SimpleWriter out = new SimpleWriter1L(folderName + "/" + key + ".html");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>" + key + "</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h2><b><i><font color=\"red\">" + key
                 + "</font></i></b></h2>");
         out.println("<blockquote>" + val + "</blockquote>");
         outputFooter(out);
         out.close();
     }
     /**
      * @param q
      * @param out
      */
     private static void popIndex(Queue<String> q, SimpleWriter out) {
         Queue<String> tempQ = new Queue1L<>();
         tempQ.transferFrom(q);
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Glossary</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Glossary</h1>");
         out.println("<hr />");
         out.println("<h3>Index</h3>");
         out.println("<ul>");
         while (tempQ.length() != 0) {
             String key = tempQ.dequeue();
             out.println(
                     "<li><a href=\"" + key + ".html\">" + key + "</a></li>");
             q.enqueue(key);
         }
         out.println("</ul>");
         out.println("</body>");
         out.println("</html>");
     }
     /**
      * @param map
      * @param out
      * @param folderName
      */
     private static void outputHeader(Map<String, String> map,
             SimpleWriter out, String folderName) {
         Map<String, String> tempMap = map.newInstance();
         Queue<String> keyQ = keysInQueue(map);
         replaceTerm(map, keyQ);
         tempMap.transferFrom(map);
         while (tempMap.size() > 0) {
             Map.Pair<String, String> pair = tempMap.removeAny();
             String key = pair.key();
             String value = pair.value();
             popFile(key, value, folderName);
         }
     }
     /**
      * Outputs the "closing" tags in the generated HTML file. These are the
      * expected elements generated by this method:
      *
      * <hr />
      * <p>
      * Return to <a href=\"index.html\">index</a>.
      * </p>
      *
      * @param out
      * the output stream
      * @updates out.contents
      * @requires out.is_open
      * @ensures out.content = #out.content * [the HTML "closing" tags]
      */
     //method to create better single point control efficiency
      private static void outputFooter(SimpleWriter out) {
          assert out != null : "Violation of: out is not null";
          assert out.isOpen() : "Violation of: out.is_open";
          out.println("<hr />");
          out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
          out.println("</body>");
          out.println("</html>");
          }
      /**
       * Main method.
       *
       * @param args
       * the command line arguments; unused here
       */
       public static void main(String[] args) {
           SimpleWriter out = new SimpleWriter1L();
           SimpleReader in = new SimpleReader1L();
           //input for file name
           out.println("insert name of input file (include .txt): ");
           String fileName = in.nextLine();
           //input for folder
           out.println("insert name of folder: ");
           String fileLocation = in.nextLine();
           //concatenates the file name and folder
           String file = fileLocation + "/" + fileName;
           SimpleReader fileIn = new SimpleReader1L(file);
           out.print("Enter the name of a folder that you want to "
                   + "saved the outputs in it: ");
           String folderName = in.nextLine();

           Map<String, String> mapK = new Map1L<>();
           while (!fileIn.atEOS()) {
               String str = getTerms(fileIn);
               String element = str.substring(0, str.indexOf(",") + 1);
               String def = str.substring(str.indexOf(",") + 1, str.length());
               while (def.contains(",")) {
                   def = def.substring(0, def.indexOf(",")) + " "
                           + def.substring(def.indexOf(",") + 1, def.length());
               }
               String result = element + def;
               addElementsToMap(result, mapK);
           }

          Queue<String> keysQ = keysInQueue(mapK);
          SimpleWriter outIndex = new SimpleWriter1L(folderName + "/index.html");
          popIndex(keysQ, outIndex);

       SimpleWriter outQ = new SimpleWriter1L();
       outputHeader(mapK, outQ, folderName);
       out.print("Please work :D");
       fileIn.close();
       out.close();
       outIndex.close();
       in.close();
       }
}
