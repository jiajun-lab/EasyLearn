package As4;

import java.util.ArrayList;

public class Library {
    private String address;
    private ArrayList<Book> as4Books;
    // Add the missing implementation to this class
    Library(String address){
        this.address = address;
        as4Books = new ArrayList<>();
    }

    private void addBook(Book as4Book){
        as4Books.add(as4Book);
    }

    private static void printOpeningHours(){
        System.out.println("Libraries are open daily from 9am to 5pm.");
    }

    private void printAddress(){
        System.out.println(this.address);
    }

    private void borrowBook(String name) {
        for (Book as4Book : as4Books) {
            if(as4Book.getTitle().equals(name)){
                as4Book.borrowed();
                return;
            }
        }
        System.out.println("Sorry, this book is not in our catalog.");
    }

    private void returnBook(String name) {
        for (Book as4Book : as4Books) {
            if(as4Book.getTitle().equals(name)){
                as4Book.returned();
                return;
            }
        }
        System.out.println("As4.Book not found");
    }

    private void printAvailableBooks() {
        if(as4Books.isEmpty()){
            System.out.println("No book in catalog");
        }
        for (Book as4Book : as4Books) {
            if (!as4Book.borrowed) {
                System.out.println(as4Book.getTitle());
            }
        }
    }

    public static void main(String[] args) {
// Create two libraries
        Library firstAs4Library = new Library("10 Main St.");
        Library secondAs4Library = new Library("228 Liberty St.");
// Add four as4Books to the first library
        firstAs4Library.addBook(new Book("The Da Vinci Code"));
        firstAs4Library.addBook(new Book("Le Petit Prince"));
        firstAs4Library.addBook(new Book("A Tale of Two Cities"));
        firstAs4Library.addBook(new Book("The Lord of the Rings"));
// Print opening hours and the addresses
        System.out.println("As4.Library hours:");
        printOpeningHours();
        System.out.println();
        System.out.println("As4.Library addresses:");
        firstAs4Library.printAddress();
        secondAs4Library.printAddress();
        System.out.println();
// Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstAs4Library.borrowBook("The Lord of the Rings");
        firstAs4Library.borrowBook("The Lord of the Rings");
        secondAs4Library.borrowBook("The Lord of the Rings");
        System.out.println();
// Print the titles of all available as4Books from both libraries
        System.out.println("Books available in the first library:");
        firstAs4Library.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondAs4Library.printAvailableBooks();
        System.out.println();
// Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstAs4Library.returnBook("The Lord of the Rings");
        System.out.println();
// Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstAs4Library.printAvailableBooks();
    }




}
