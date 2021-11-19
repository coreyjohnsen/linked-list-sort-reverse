//////////////// P08 Linked Sorting //////////////////////////////////////////
//
// Title: LinkedNode.java
// Course: CS 300 Fall 2021
//
// Author: Corey Johnsen
// Email: cjjohnsen@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class represents a single node of a singly-linked list
 */
public class LinkedNode<T> {

  // fields
  private T data;
  private LinkedNode<T> next;

  // constructors

  /**
   * Constructs a new node with specified data and sets the next node to null
   * 
   * @param data is the data to store
   */
  LinkedNode(T data) {
    this.data = data;
  }

  /**
   * Constructs a new node with specified data and sets the next node
   * 
   * @param data is the data to store
   * @param next is the next node
   */
  LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }

  // accessors

  /**
   * Gets the next node in the list
   * 
   * @returns the next node
   */
  public LinkedNode<T> getNext() {
    return this.next;
  }

  /**
   * Gets the data at this node
   * 
   * @returns the data in this node
   */
  public T getData() {
    return this.data;
  }

  /**
   * Returns the data in the node represented by a string
   * 
   * @returns the string representation of the data
   */
  public String toString() {
    return data.toString();
  }

  // mutators

  /**
   * Sets the next node
   * 
   * @param next is the new node to set the next node to
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }

}
