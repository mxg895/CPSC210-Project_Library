package tests;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookTest {
    private final boolean INITIAL_STATUS = true;
    private Book testBook;

    @BeforeEach
    public void setBefore(){
        testBook = new Book ("", INITIAL_STATUS);
    }

    @Test
    void testConstructor(){
        assertEquals("", testBook.getTitle());
        assertEquals(INITIAL_STATUS, testBook.getAvailability());
    }

    //tests if a one-letter string matches the title of a Book
    @Test
    public void testMatchingBookOneLetter(){
        testBook.setTitle("L");
        assertTrue(testBook.isMatchingBook("L"));
        assertFalse(testBook.isMatchingBook("C"));
    }

    //tests if a >1 letter string matches the title of a Book
    @Test
    public void testMatchingBookManyLetters(){
        testBook.setTitle("Harry Potter");
        assertTrue(testBook.isMatchingBook("Harry Potter"));
        assertFalse(testBook.isMatchingBook("harry potter"));
    }

    //tests if the given string matches the status of a Book
    @Test
    public void testMatchingStatus(){
        testBook.setAvailable();
        assertTrue(testBook.isMatchingStatus("available"));
        assertFalse(testBook.isMatchingStatus("on-loan"));
        assertFalse(testBook.isMatchingStatus("not a status"));

        testBook.setOnLoan();
        assertFalse(testBook.isMatchingStatus("available"));
        assertTrue(testBook.isMatchingStatus("on-loan"));
        assertFalse(testBook.isMatchingStatus("not a status"));

    }

    //tests the title and status of a Book
    @Test
    public void testMatchingNameAndStatus(){
        testBook.setOnLoan();
        testBook.setTitle("A Test Book");

        assertFalse(testBook.isMatchingBook("Not a tests book"));
        assertTrue(testBook.isMatchingBook("A Test Book"));
        assertFalse(testBook.isMatchingStatus("available"));
        assertFalse(testBook.isMatchingStatus("missing"));
        assertTrue(testBook.isMatchingStatus("on-loan"));
    }
}
