package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Jafar Jarrar
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root of the tree.
   */
  BitTreeInteriorNode root;

  /**
   * The size of the tree.
   */
  int size;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new bit tree with n levels.
   * @param n
   * The number of levels in the tree.
   */
  public BitTree(int n) {
    this.root = new BitTreeInteriorNode();
    this.size = n;
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Traverses through each node and adds on to content String if the node
   * carries any value.
   * @param pen
   * Used to print Strings to user.
   * @param node
   * The nodes that will be traversed and checked for a value.
   * @param content
   * The String that will be printed and added to as the tree is traversed through.
   */
  public void dumpHelper(PrintWriter pen, BitTreeNode node, String content) {
    if (node == null) {
      return;
    } else if (node instanceof BitTreeLeaf) {
      pen.println(content + "," + ((BitTreeLeaf) node).value);
    } else {
      BitTreeInteriorNode temp = (BitTreeInteriorNode) node;
      dumpHelper(pen, temp.left, content + "0");
      dumpHelper(pen, temp.right, content + "1");
    } // if
  } // dumpHelper(PrintWriter, BitTreeNode, String)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sets the value at the end of the path in the tree reached
   * through following the bits.
   * @param bits
   * The 0 and 1 values that will be followed to get to the
   * designated path.
   * @param value
   * The value to be set at the end of the path.
   * @throws IndexOutOfBoundsException
   * If the length of the bits string is incorrect or if the
   * bits String contains values other than '0' or '1'.
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() != this.size) {
      throw new IndexOutOfBoundsException();
    } // if
    BitTreeInteriorNode current = this.root;
    for (int i = 0; i < bits.length() - 1; i++) {
      char temp = bits.charAt(i);
      if (temp != '0' && temp != '1') {
        throw new IndexOutOfBoundsException();
      } else if (temp == '0') {
        if (current.left == null) {
          current.left = new BitTreeInteriorNode();
        } // if
        current = (BitTreeInteriorNode) current.left;
      } else {
        if (current.right == null) {
          current.right = new BitTreeInteriorNode();
        } // if
        current = (BitTreeInteriorNode) current.right;
      } // if
    } // for
    if (bits.charAt(bits.length() - 1) == '0') {
      current.left = new BitTreeLeaf(value);
    } else {
      current.right = new BitTreeLeaf(value);
    } // if
  } // set(String, String)

  /**
   * Gets the value at the end of the path in the tree reached
   * through following the bits.
   * @param bits
   * The 0 and 1 values that will be followed to get to the
   * designated path.
   * @return
   * The value at the end of the path followed by the bits in the tree.
   * @throws IndexOutOfBoundsException
   * If the length of the bits string is incorrect or if the
   * String contains values other than '0' or '1'.
   */
  public String get(String bits) throws IndexOutOfBoundsException {
    if (bits.length() != this.size) {
      throw new IndexOutOfBoundsException();
    } // if
    BitTreeInteriorNode current = this.root;
    for (int i = 0; i < bits.length() - 1; i++) {
      char temp = bits.charAt(i);
      if (temp != '0' && temp != '1') {
        throw new IndexOutOfBoundsException();
      } else if (temp == '0') {
        current = (BitTreeInteriorNode) current.left;
      } else {
        current = (BitTreeInteriorNode) current.right;
      } // if
    } // for
    if (bits.charAt(bits.length() - 1) == '0') {
      return ((BitTreeLeaf) current.left).value;
    } else {
      return ((BitTreeLeaf) current.right).value;
    } // if
  } // get(String, String)

  /**
   * Prints out the contents of the tree.
   * @param pen
   * Used to print Strings to user.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(pen, this.root, "");
  } // dump(PrintWriter)

  /**
   * Reads lines from the source and calls the set method on them,
   * creating new branches in the tree based on the bits and values
   * provided in the source.
   * @param source
   * The source in which the bits, values lines will be read from.
   */
  public void load(InputStream source) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(source));
    try {
      String line = reader.readLine();
      while (line != null) {
        String[] divided = line.split(",");
        set(divided[0], divided[1]);
        line = reader.readLine();
      } // while
    } catch (IOException e) {
      // Does Nothing.
    } // try/catch
  } // load(InputStream)

} // class BitTree
