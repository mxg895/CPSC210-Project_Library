/*

package test;

import model.Book;
import ui.LibrarySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LibrarySystemTest {
    private ArrayList<Book> testLibraryList = new ArrayList<>();
    private ArrayList<Book> testOnLoanList = new ArrayList<>();
    private LibrarySystem ls;


    @BeforeEach
    public void setUpForTest(){
        Book testBOok = new Book("","");
        ls = new LibrarySystem();
    }

    @Test
    public void testEmptyLists(){
        //test asking for a book loan from an empty library
        ls.addToLoan("A Book", testLibraryList, testOnLoanList);
        assertEquals(0, testOnLoanList.size());

        //test returning a book to an empty library
        ls.returnBook("A Book", testLibraryList, testOnLoanList);
        assertEquals(0, testOnLoanList.size());
        assertEquals(0, testLibraryList.size());

    }


    @Test
    public void testTestBookLists(){
        //test adding books to an empty list of books
        ls.searchAndAddBook("Sample 1",testLibraryList);
        assertEquals(1, testLibraryList.size());
        //test adding the same book to a book list with 1 entry
        ls.searchAndAddBook("Sample 1", testLibraryList);
        assertEquals(1, testLibraryList.size());

        //test adding multiple different books of different string lengths to the same list
        ls.searchAndAddBook("Sample 2", testLibraryList);
        assertEquals(2, testLibraryList.size());
        ls.searchAndAddBook("3", testLibraryList);
        assertEquals(3, testLibraryList.size());
        ls.searchAndAddBook("Testing the Program!!#", testLibraryList);
        assertEquals(4, testLibraryList.size());

        //test adding the book titles of any previously added books
        ls.searchAndAddBook("3", testLibraryList);
        assertEquals(4, testLibraryList.size());
        ls.searchAndAddBook("Testing the Program!!#", testLibraryList);
        assertEquals(4, testLibraryList.size());
        assertEquals(0, testOnLoanList.size());

        //test asking for a loan on a book that's in the library
        ls.addToLoan("Sample 1", testLibraryList, testOnLoanList);
        assertEquals(1, testOnLoanList.size());

        //test asking for a loan on the same book
        ls.addToLoan("Sample 1", testLibraryList, testOnLoanList);
        assertEquals(1, testOnLoanList.size());

        //test asking for a loan on a book not in the library
        ls.addToLoan("Unknown", testLibraryList, testOnLoanList);
        assertEquals(1, testOnLoanList.size());

        //test asking for more loans on different books in the library
        ls.addToLoan("3", testLibraryList, testOnLoanList);
        assertEquals(2, testOnLoanList.size());
        ls.addToLoan("Testing the Program!!#", testLibraryList, testOnLoanList);
        assertEquals(3, testOnLoanList.size());

        //test asking for a loan on a book in the list of books on-loan
        ls.addToLoan("3", testLibraryList, testOnLoanList);
        assertEquals(3, testOnLoanList.size());

        //test returning a loaned book
        ls.returnBook("Sample 1", testLibraryList, testOnLoanList);
        assertEquals(2, testOnLoanList.size());

        //test returning the same book again
        ls.returnBook("Sample 1", testLibraryList, testOnLoanList);
        assertEquals(2, testOnLoanList.size());

        //test returning a book not in the library
        ls.returnBook("1", testLibraryList, testOnLoanList);
        assertEquals(2, testOnLoanList.size());

        //test returning more books
        ls.returnBook("Testing the Program!!#", testLibraryList, testOnLoanList);
        assertEquals(1, testOnLoanList.size());
        ls.returnBook("3", testLibraryList, testOnLoanList);
        assertEquals(0, testOnLoanList.size());
    }

}

*/
