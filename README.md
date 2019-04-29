# Personal Project for CPSC 210

Week 1(P1): created an ui package with a class that contains a main method which calls 2 other methods.

Week 2(P2): created a model package with Book class, containing a Book constructor with 2 parameters:
           -title (String) and available (boolean)
           Modified the main and its class (LibrarySystem) to fit P2 criteria, giving the program 5 options:
           -Adding a book
           -Placing a loan on a book
           -Returning a book
           -Printing out the library's list of books with available true as available and false as on loan
           -Printing a list of loaned books upon exiting the library system

Week 3 (P3): Created BookLists class and moved all methods definitions for ArrayList<Book> from LibrarySystem to
             BookLists. Modified the Modified methods for searchAndAddBook, addToLoan, and returnBook to take in
             the library's overall book list and the on-loan book list (both ArrayList<Book>). Same user
             interaction options as P2, but with modified method definitions and method calls. Test classes for
             Book and BookLists classes are added.

Week 4 (P4): Created Loadable and Saveable Interfaces, which were implemented in LibrarySystem. Created Classes
             LoadableTest and SaveableTest to tests Loadable and Saveable respectively.

Week 5 (P5): created Member, Admin, UserSystem, and User (abstract) Classes. Admin Overrides printUserOptions of
             User. Moved main to UserSystem Class, which directs the caller to run the system either as Admin or
             as User.Member and Admin extends User. Transferred codes from LibrarySystem to User.

Week 6 (P6): Created:
             1) BookNotFoundException - thrown when trying to loan/return a book not in library
             2) DuplicateBookException - thrown when trying to add a pre-existing book to the library
             3) UnavailableBookException - thrown when trying to loan/return a book that's loaned to someone else
                (on-loan available, but no in onLoan of the user)

Week 7 (P7): Draw graphs -- written assignment

Week 8 (P8): 1) Created GeneralUser class to contain info for a general user, which is run under the RegularUserSystem
             2) Created Admin class to contain info for an admin -- still to be completed
             3) Built a reflexive relationship between GeneralUser and Book, where GeneralUser has a field containing
             the list of books it currently has on loan, and Book contains to whom (GeneralUser) is the book loaned to
             4) Moved load and save methods to FileLoader and FileSaver respectively
                -A list of GeneralUser will be loaded from and saved to Users List.txt
                -Library's Book List.txt now contains a 3rd column for the userID of the current user of the book, where
                "none" means the book is not loaned to anyone (i.e. available)
                -Load method for Library's Book List.txt now loads the file into a reflexive relationship between each
                book and its user (stored in Users List.txt), and the list of books for the entire library will then be
                added to a HashMap, where k is Book, and v is General User

Week 9 (P9): 1) UserSystem is no longer a superclass of AdminSystem and RegularUserSystem
                -Moved runUserOptions to Library (main), which leads to AccountOptions to manage account
                -AccountOptions directs the user to act as either admin or general user, based on selection from Library
                -Admins are directed to AdminAccountManger; general Users are directed to UserAccountManager, to manage
                books (BooksManager)
             2) All print methods are moved to InstructionPrinter

Week 10 (P10): 1) Added API feature by copying the given sample API implementation
               2) Added an Observers feature with:
                  -A new class--OnHoldUsers extends Subject (abstract) and as a private field in class Book
                     -->Notifies the first user in the list of GeneralUser in OnHoldUsers to loan the book
                     whenever it becomes available; rest of the users in the list gets notified about
                     their position moving forward 1 place, but not ready to loan the book
                  -A new class--OnHoldBooks as a private field in class GeneralUser that adds a book to the user's list
                   whenever the user logs in to place a hold on a book
                  -GeneralUser implements a new interface--OnHoldBookObserver that gets updated about change in their
                   standing for book(s) that the user has placed on hold for
               3) Change the implementation of saving the library's book list to saving as a JSON file using methods
                  from the GSON library
               4) Methods for loading the saved data no longer works, as the fields of Book and and General User has
                  changed and files are saved to JSON. Will change this implementation for P11 accordingly;
Week 11-12 (P11): 1)Removed API feature from P10 to reduce non-applicable features for GUI
                  2)Commented out all "place hold" features and its relevant Observers and Subject for the simplicity
                  of application basic GUI building
                  3)Turned the features for general user access into a simple functional GUI
                    -More complex features (e.g. switch scene to admin's system, place hold on books) will be considered
                    in the future
                    -GUI built using Gluon Scene Builder and JavaFX
