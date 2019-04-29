package tests;

import exceptions.BookNotFoundException;
import exceptions.DuplicateBookException;
import exceptions.UnavailableBookException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AdminSystem;
import ui.RegularUserSystem;
import ui.UserSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserSystemTest {
    private ArrayList<Book> booksToLoad;
    private ArrayList<Book> booksToSave;
    private ArrayList<Book> onLoanTest;
    private String statusTest;
    private UserSystem umTest;
    private UserSystem uaTest;
    private Book b1 = new Book("Test", "available");
    private Book b2 = new Book("Next", "on-loan");
    private Book b3 = new Book("Random Book", "available");
    private Book b4 = new Book("Unavailable Book Here", "on-loan");

    public UserSystemTest() throws IOException {
    }

    public void checkLoad(ArrayList<Book> loadedList) throws IOException {
        assertEquals(b1.getTitle(), loadedList.get(0).getTitle());
        assertEquals(b1.getAvailability(), loadedList.get(0).getAvailability());
        assertEquals(b2.getTitle(), loadedList.get(1).getTitle());
        assertEquals(b2.getAvailability(), loadedList.get(1).getAvailability());
        assertEquals(b3.getTitle(), loadedList.get(2).getTitle());
        assertEquals(b3.getAvailability(), loadedList.get(2).getAvailability());
        assertEquals(b4.getTitle(), loadedList.get(3).getTitle());
        assertEquals(b4.getAvailability(), loadedList.get(3).getAvailability());
    }
    @BeforeEach
    public void setUp() throws IOException {
        umTest=new RegularUserSystem();
        uaTest=new AdminSystem();
        booksToLoad = new ArrayList<>();
        booksToSave = new ArrayList<>();
        onLoanTest = new ArrayList<>();
        umTest.loadLibraryBooks("Load Books Test.txt", booksToLoad);
        umTest.loadLibraryBooks("Load Books Test.txt", booksToSave);
    }

    @Test
    //Test loadLibraryBooks
    void testLoadBooks() throws IOException {
        checkLoad(booksToLoad);
        checkLoad(booksToSave);
    }

    @Test
    //Test loan an available book in library, expect no exception
    void testLoanNoExceptionThrown() {
        try {
            umTest.loanBook("Test", booksToSave, onLoanTest);
        } catch (UnavailableBookException e) {
            fail("Exception not expected");
        } catch (BookNotFoundException e) {
            fail("Exception not expected");
        }
        assertEquals(1, onLoanTest.size());

    }

    @Test
    //Test loan an already on-loan book, and a book not in library
    //Catch UnavailableBookException and BOokNotFoundException respectively
    void testLoanExpectExceptions(){
        //Add an on-loan book
        try {
            umTest.loanBook("Unavailable Book Here", booksToSave, onLoanTest);
        } catch (UnavailableBookException e) {
        } catch (BookNotFoundException e) {
            fail("Exception not expected");
        }
        assertEquals(0, onLoanTest.size());

        //Add a book not in the library
        try {
            umTest.loanBook("No Book", booksToSave, onLoanTest);
        } catch (UnavailableBookException e) {
            fail("Exception not expected");
        } catch (BookNotFoundException e) {
        }
        assertEquals(0, onLoanTest.size());
    }

    @Test
    //Test return a book in onLoan, expect no exception;
    public void testReturn() throws BookNotFoundException, UnavailableBookException {
        //Add 2 books to onLoan to start
        umTest.loanBook("Test", booksToSave, onLoanTest);
        umTest.loanBook("Random Book", booksToSave, onLoanTest);
        //Return a book in on-loan
        try {
            umTest.returnBook("Test");
        } catch (UnavailableBookException e) {
            fail("Exception not expected");
        }
        assertEquals(1, onLoanTest.size());
    }

    @Test
    //Test return a book in library but not in onLoan, and a book not in library;
    //Catch UnavailableBookException and BookNotFoundException respectively
    void testReturnExpectExceptions() throws BookNotFoundException, UnavailableBookException {
        //Add 2 books to onLoan to start
        umTest.loanBook("Test", booksToSave, onLoanTest);
        umTest.loanBook("Random Book", booksToSave, onLoanTest);
        //Return a book not in on-loan
        try {
            umTest.returnBook("Unavailable Book Here");
        } catch (UnavailableBookException e) {
        }
        assertEquals(2, onLoanTest.size());

        //Return a different book not in library
        try {
            umTest.returnBook("Book not here");
        } catch (UnavailableBookException e) {
            fail("Exception not expected");
        }
        assertEquals(2, onLoanTest.size());
    }

    @Test
    //Test save
    public void testSave() throws IOException, UnavailableBookException, BookNotFoundException {
        //Add 2 books to onLoan to start
        umTest.loanBook("Test", booksToSave, onLoanTest);
        umTest.loanBook("Random Book", booksToSave, onLoanTest);
        //save booksToSave to file
        umTest.saveLibraryBooks("Save Books Test.txt", booksToSave);
        List<String> lines = Files.readAllLines(Paths.get("Save Books Test.txt"));
        assertEquals("Test: on-loan", lines.get(0));
        assertEquals("Next: on-loan", lines.get(1));
        assertEquals("Random Book: on-loan", lines.get(2));
        assertEquals("Unavailable Book Here: on-loan", lines.get(3));
        //Reset changes to the save Books Test file
        umTest.saveLibraryBooks("Save Books Test.txt", booksToLoad);
    }

    @Test
    //Test addToAllBooks method by adding a book not in library; expect no exception
    public void testAddToLibraryExpectNoException() {
        //Add a new book
        try {
            uaTest.addToAllBooks("Sample 1");
        } catch (DuplicateBookException e) {
            fail("Exception not expected");
        }
        assertEquals(5, booksToSave.size());
    }

    @Test
    //Test addToAllBooks method by adding a book already in library; expect DuplicateBookException
    public void testAddToLibraryExpectDuplicateBookException(){
        try {
            uaTest.addToAllBooks("Test");
        } catch (DuplicateBookException e) {
        }
        assertEquals(4,booksToSave.size());
    }

    @Test
    //Test Change book status
    public void testChangeStatus() throws BookNotFoundException {
        //Change status of a book to on-loan
        statusTest = booksToSave.get(0).getAvailability();
        assertEquals("available", statusTest);
        uaTest.changeBookStatus("Test", "on-loan", booksToSave);
        statusTest = booksToSave.get(0).getAvailability();
        assertEquals("on-loan", statusTest);
        //Change status of a book to available
        statusTest = booksToSave.get(3).getAvailability();
        assertEquals("on-loan", statusTest);
        uaTest.changeBookStatus("Unavailable Book Here", "available", booksToSave);
        statusTest = booksToSave.get(3).getAvailability();
        assertEquals("available", statusTest);
    }
}
