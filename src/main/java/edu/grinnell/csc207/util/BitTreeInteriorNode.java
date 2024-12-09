package edu.grinnell.csc207.util;

/**
 * Creates a node for the bit tree that carries no value
 * but has pointers to left and right children.
 * @author Jafar Jarrar
 */
public class BitTreeInteriorNode implements BitTreeNode {

  /**
   * A pointer to the node to the left of the node.
   */
  BitTreeNode left;

  /**
   * A pointer to the node to the right of the node.
   */
  BitTreeNode right;

  /**
   * Create a new node.
   */
  public BitTreeInteriorNode() {
    this.left = null;
    this.right = null;
  } // BTNode()
} // class BTNode
