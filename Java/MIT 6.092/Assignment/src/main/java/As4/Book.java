package As4;

public class Book {
    String title;
    boolean borrowed;
    // Creates a new As4.Book
    public Book(String bookTitle) {
        this.title = bookTitle;
        this.borrowed = false;
    }
    // Marks the book as rented
    public void borrowed() {
        if(this.borrowed == false) {
            this.borrowed = true;
            System.out.println("You successfully borrowed " + this.title);
        }else {
            System.out.println("Sorry, this book is already borrowed.");
        }
    }
    // Marks the book as not rented
    public void returned() {
        if(this.borrowed == true) {
            this.borrowed = false;
            System.out.println("You successfully returned " + this.title);
        }
    }
    // Returns true if the book is rented, false otherwise
    public boolean isBorrowed() {
        return borrowed;
    }
    // Returns the title of the book
    public String getTitle() {
        return title;
    }
    public static void main(String[] arguments) {
// Small Counter.java of the As4.Book class
        Book example = new Book("The Da Vinci Code");
        System.out.println("Title (should be The Da Vinci Code): " + example.getTitle());
        System.out.println("Borrowed? (should be false): " + example.isBorrowed());
        example.borrowed();
        System.out.println("Borrowed? (should be true): " + example.isBorrowed());
        example.returned();
        System.out.println("Borrowed? (should be false): " + example.isBorrowed());
    }
}
