package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 *
 */
public class BrailleASCII {

  /**
   * Prints out braille representation of source String.
   * @param source
   * The String to be converted to the braille representation.
   * @return
   * The converted String.
   */
  public static String braille(String source) {
    String binary = "";
    for (int i = 0; i < source.length(); i++) {
      char temp = source.charAt(i);
      if (temp < 97 || temp > 122) {
        return binary + "\nTrouble translating because No corresponding value";
      } // if
      binary += BrailleAsciiTables.toBraille(temp);
    } // for
    return binary;
  } // braille(String)

  /**
   * Prints out String version of ascii code String.
   * @param source
   * The String of ascii values to be converted to the String representation.
   * @return
   * The converted String in correct cases. An error message if the
   * length of the ascii codes String is not long enough to represent all the
   * letters in the actual word.
   */
  public static String ascii(String source) {
    if (source.length() % 6 != 0) {
      return "Invalid length of bit string";
    } // if
    String result = "";
    for (int i = 0; i < source.length(); i += 6) {
      String bits = source.substring(i, i + 6);
      result += BrailleAsciiTables.toAscii(bits);
    } // for
    return result;
  } // ascii(String)

  /**
   * Prints out the unicode version of the String.
   * @param source
   * The String to be converted to the unicode representation.
   * @return
   * The converted String in correct cases. An error message if the
   * length of the ascii codes of the source String is not long enough
   * to represent all the letters in the actual word.
   */
  public static String unicode(String source) {
    // Convert letter to 8 bits ascii, then 6 bits to braille, then to unicode.
    String result = "";
    for (int i = 0; i < source.length(); i++) {
      char temp = source.charAt(i);
      String braille = BrailleAsciiTables.toBraille(temp);
      if (braille.length() % 6 != 0) {
        return "Invalid length of bit string";
      } // if
      result += BrailleAsciiTables.toUnicode(braille);
    } // for
    return result;
  } // unicode(String)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Asks user for target character set and source characters to convert
   * desired String to chosen representation.
   * @param args
   * Should be of length 2. The first argument should be the target
   * character set and the second argument should be the String to be
   * converted.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length != 2) {
      System.err.println("Invalid number of arguments. Please try again.");
      return;
    } // if
    String target = args[0];
    String source = args[1];
    switch (target) {
      case "braille":
        pen.println(braille(source));
        break;
      case "ascii":
        pen.println(ascii(source));
        break;
      case "unicode":
        pen.println(unicode(source));
        break;
      default:
        System.err.println("Invalid input. Please enter a valid target character set.");
    } // switch
    pen.close();
  } // main(String[]
} // class BrailleASCII
