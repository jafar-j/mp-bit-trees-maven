package edu.grinnell.csc207.util;

/**
 * Creates a leaf for the bit tree which is a node that
 * contains only a String value and has no children nodes.
 * @author Jafar Jarrar
 */
public class BitTreeLeaf implements BitTreeNode {

  /**
   * The value that the node carries.
   */
  String value;

    /**
   * Create a new node with no children.
   * @param value
   * The String value in the node.
   */
  public BitTreeLeaf(String value) {
    this.value = value;
  } // BTNode(String)
} // class BitTreeLeaf
