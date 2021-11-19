//////////////// P08 Linked Sorting //////////////////////////////////////////
//
// Title: LinkedBookshelfTester.java
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
 * This class tests the methods of LinkedBookshelf
 */
public class LinkedBookshelfTester {

  /**
   * Called when the program runs
   */
  public static void main(String[] args) {

    System.out.println(runAllTests());
    
    Book book1 = new Book("testing", 430);
    Book book2 = new Book("fail", 33);
    Book book3 = new Book("lol", 100);
    Book book4 = new Book("apple", 200);
    LinkedBookshelf shelf = new LinkedBookshelf();
    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    shelf.appendBook(book4);
    System.out.println(shelf.toString());
    LinkedBookshelf.reverse(shelf);
    System.out.println(shelf.toString());

  }

  /**
   * Tests that the LinkedNode class works as expected
   * 
   * @returns true if the class works, false otherwise
   */
  public static boolean testLinkedNode() {
    try {
      LinkedNode<Integer> testNode = new LinkedNode<Integer>(1);
      if (testNode.getData() != 1)
        return false;
      if (testNode.getNext() != null)
        return false;
      LinkedNode<Integer> testNextNode = new LinkedNode<Integer>(2);
      testNode = new LinkedNode<Integer>(1, testNextNode);
      if (testNode.getData() != 1 || !testNode.getNext().equals(testNextNode))
        return false;
      testNode.setNext(null);
      if (testNode.getNext() != null)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests that the clear() method works as expected
   * 
   * @returns true if the method works, false otherwise
   */
  public static boolean testClear() {
    Book.resetGenerator();
    // set up shelf with 4 books
    Book book1 = new Book("testing", 430);
    Book book2 = new Book("fail", 33);
    Book book3 = new Book("lol", 100);
    Book book4 = new Book("apple", 200);
    LinkedBookshelf shelf = new LinkedBookshelf();
    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book4);
    shelf.appendBook(book3);

    try {
      // clear list
      shelf.clear();
      // check that list head and tail are null and that size is 0
      if (!shelf.isEmpty() || shelf.size() != 0)
        return false;
      if (shelf.toString().equals("ID\n\n"))
        return false;
      if (shelf.getFirst() != null || shelf.getLast() != null)
        return false;
    } catch (NullPointerException e) {
      // exception from getFirst/getLast
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Tests that the appendBook() method works as expected
   * 
   * @returns true if the method works, false otherwise
   */
  public static boolean testAddBooks() {
    try {
      Book.resetGenerator();
      Book book1 = new Book("testing", 430);
      Book book2 = new Book("fail", 33);
      LinkedBookshelf shelf = new LinkedBookshelf();
      // add a book to list
      shelf.appendBook(book1);

      // make sure list size is 1 and that the tail and head nodes point to the new node
      if (shelf.size() != 1 || !(shelf.getLast().getPageCount() == 430
          && shelf.getLast().getTitle().equals("testing")))
        return false;
      if (!(shelf.getFirst().getPageCount() == 430
          && shelf.getFirst().getTitle().equals("testing")))
        return false;

      // add another book and make sure head node points to book1 and tail points to book2
      shelf.appendBook(book2);

      if (shelf.size() != 2
          || !(shelf.getLast().getPageCount() == 33 && shelf.getLast().getTitle().equals("fail")))
        return false;
      if (!(shelf.getFirst().getPageCount() == 430
          && shelf.getFirst().getTitle().equals("testing")))
        return false;
    } catch (Exception e) {
      // incorrect exception thrown
      return false;
    }
    return true;
  }

  /**
   * Tests that the sort() method works as expected
   * 
   * @returns true if the method works, false otherwise
   */
  public static boolean testSortBooks() {
    Book.resetGenerator();
    // test valid sorts by all
    try {
      // set up unsorted bookshelf and sorted to compare
      Book book1 = new Book("testing", 430, "Johnsen", "Corey");
      Book book2 = new Book("pass", 33, "Saluja", "Saniya");
      Book book3 = new Book("fail", 2, "Millevolte", "Basil");
      LinkedBookshelf shelf = new LinkedBookshelf();
      shelf.appendBook(book1);
      shelf.appendBook(book2);
      shelf.appendBook(book3);
      LinkedBookshelf sorted = new LinkedBookshelf();
      sorted.appendBook(book3);
      sorted.appendBook(book2);
      sorted.appendBook(book1);

      // test sorting by title
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (shelf.size() != 3)
        return false;
      for (int i = 0; i < shelf.size(); i++) {
        if (!(shelf.get(i).getTitle().equals(sorted.get(i).getTitle())))
          return false;
      }

      // test sorting by author
      LinkedBookshelf.sort(shelf, Attribute.AUTHOR);
      sorted.clear();
      sorted.appendBook(book1);
      sorted.appendBook(book3);
      sorted.appendBook(book2);
      if (shelf.size() != 3)
        return false;
      for (int i = 0; i < shelf.size(); i++) {
        if (!(shelf.get(i).getAuthor().equals(sorted.get(i).getAuthor())))
          return false;
      }

      // test sorting by page count
      LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);
      sorted.clear();
      sorted.appendBook(book3);
      sorted.appendBook(book2);
      sorted.appendBook(book1);
      if (shelf.size() != 3)
        return false;
      for (int i = 0; i < shelf.size(); i++) {
        if (shelf.get(i).getPageCount() != sorted.get(i).getPageCount())
          return false;
      }

      // test sorting by ID
      LinkedBookshelf.sort(shelf, Attribute.ID);
      sorted.clear();
      sorted.appendBook(book1);
      sorted.appendBook(book2);
      sorted.appendBook(book3);
      if (shelf.size() != 3)
        return false;
      for (int i = 0; i < shelf.size(); i++) {
        if (shelf.get(i).ID != sorted.get(i).ID)
          return false;
      }
    } catch (Exception e) {
      // invalid exception
      return false;
    }
    return true;
  }

  /**
   * Tests that all test methods return true
   * 
   * @returns true if all test methods return true, false otherwise
   */
  public static boolean runAllTests() {
    return testLinkedNode() && testAddBooks() && testClear() && testSortBooks();
  }

}
