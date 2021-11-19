//////////////// P08 Linked Sorting //////////////////////////////////////////
//
// Title: LinkedBookshelf.java
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
 * Represents a list of LinkedNodes that contain Books
 */
public class LinkedBookshelf {

  // data

  private LinkedNode<Book> front;
  private LinkedNode<Book> back;
  private int size;
  private Attribute sortedBy;

  // constructors

  /**
   * Constructs an empty LinkedBookshelf
   */
  LinkedBookshelf() {
    sortedBy = Attribute.ID;
    size = 0;
  }

  // accessors

  /**
   * Returns the size of the list
   * 
   * @returns the number of elements in the list
   */
  public int size() {
    return this.size;
  }

  /**
   * Checks if the list is empty
   * 
   * @returns true if size is 0, false otherwise
   */
  public boolean isEmpty() {
    return size == 0 && front == null && back == null;
  }

  /**
   * Represents the list as a string that shows all important aspects
   * 
   * @returns a string containing the data of all nodes and how they were sorted
   */
  public String toString() {
    String returnedString = "";
    // added sortedBy at top
    returnedString += sortedBy + "\n";
    // stores current Book
    LinkedNode<Book> currNode = front;
    for (int i = 0; i < this.size(); i++) {
      // adds current Book
      returnedString += currNode.toString();
      if (i != size - 1) {
        // sets current Book to next if there is a next Book
        returnedString += "\n";
        currNode = currNode.getNext();
      }
    }
    return returnedString;
  }

  /**
   * Gets the node at a certain index in the list
   * 
   * @param index is the index to get the node at
   * @returns the node at index
   */
  public LinkedNode<Book> getNode(int index) {
    // checks if index is in range
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(
          "Invalid index: " + index + " is not betweern 0 and " + this.size());
    // sets current node to the front
    LinkedNode<Book> returned = front;
    // finds the next node until the given index is reached
    for (int i = 0; i < index; i++)
      returned = returned.getNext();
    return returned;
  }

  /**
   * Returns the Book in the node at a certain index
   * 
   * @param index is the index to get the Book at
   * @returns the Book at index
   */
  public Book get(int index) {
    return getNode(index).getData();
  }

  /**
   * Gets the Book stored in the first node in the list
   * 
   * @returns the first Book in the list
   */
  public Book getFirst() {
    return front.getData();
  }

  /**
   * Gets the Book stored in the last node in the list
   * 
   * @returns the last Book in the list
   */
  public Book getLast() {
    return back.getData();
  }

  // mutators

  /**
   * Clears the list of all elements
   */
  public void clear() {
    front = null;
    back = null;
    size = 0;
  }

  /**
   * Adds a book to the end of the list
   * 
   * @param toAdd is the book to add to the list
   */
  public void appendBook(Book toAdd) {
    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);
    if (size == 0) {
      // if list is empty, set head and tail to added node
      front = newNode;
      back = newNode;
    } else {
      // if list is not empty, set tail to added node and the next node for the previous tail to
      // this
      back.setNext(newNode);
      back = newNode;
    }
    size++;
  }

  /**
   * Inserts a book at the correct position in a sorted list
   * 
   * @param toAdd is the book to insert into the list
   */
  public void insertBook(Book toAdd) {
    // create the new node
    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);
    if (this.size() == 0) {
      // if the list is empty, set the head and tail to the new node
      front = newNode;
      back = newNode;
    } else {
      // if not empty, compare values until sorted position is found
      for (int i = 0; i < this.size(); i++) {
        if (this.get(i).compareTo(toAdd, sortedBy) > 0) { // if this is greater than added
          if (i != 0) {
            // if the found position is not the first position
            this.size++;
            LinkedNode<Book> currNext = this.getNode(i);
            this.getNode(i - 1).setNext(newNode);
            newNode.setNext(currNext);
            break;
          } else {
            // otherwise, set the head to this node
            newNode.setNext(front);
            front = newNode;
            this.size++;
            break;
          }
        } else if (i == this.size() - 1) {
          // if it is the largest value in the list, set the tail to it and set previous next to
          // this
          back = newNode;
          size++;
          this.getNode(i).setNext(back);
          break;
        }
      }
    }
  }

  /**
   * Sorts a given list by a certain attribute
   * 
   * @param b        is the LinkedBookshelf to sort
   * @param sortedBy is the attribute to sort the list by
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {
    // set the sort methods
    b.sortedBy = sortedBy;
    if (b.size() != 0 && b.size() != 1) {
      // create unsorted list starting at index 1
      LinkedBookshelf unsorted = new LinkedBookshelf();
      for (int i = 1; i < b.size(); i++) {
        unsorted.appendBook(b.get(i));
      }
      // set the current list to only the first node
      LinkedNode<Book> firstNode = b.getNode(0);
      b.clear();
      b.appendBook(firstNode.getData());
      // use insertion sort to insert each element of unsorted list into correct position
      for (int i = 0; i < unsorted.size(); i++) {
        b.insertBook(unsorted.get(i));
      }
    }
  }
  
  /**
   * Not included in P08
   */
  public static void reverse(LinkedBookshelf b) {
    LinkedNode<Book> frontNode = null;
    for(int i = b.size()-1; i >= 0; i--) {
      if(i > 0) {
        if(i == b.size()-1)
          frontNode = b.getNode(i);
        b.getNode(i).setNext(b.getNode(i-1));
      } else {
        b.getNode(i).setNext(null);
        b.back = b.getNode(i);
        b.front = frontNode;
      }
    }
  }

}
