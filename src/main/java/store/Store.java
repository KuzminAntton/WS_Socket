package store;

import bean.Book;

import java.util.HashSet;

public class Store {

    public static HashSet<Book> store = new HashSet<Book>();

    public static HashSet<Book> getAllBook() {
        store.add(new Book(01, "Java", "third", "Herbert Schildt", "1987-05-12 00:00:00 -0800"));
        store.add(new Book(02, "C++", "second", "E.Balagurusamy", "1987-05-12 00:00:00 -0700"));
        return store;
    }

    public static Book getBook(Book book) {
        if(store.contains(book)) {
            return book;
        }
        return null;
    }

    public static void addBook(Book book) {
        store.add(book);
        printAllBooks();

    }

    public static void printAllBooks() {
        for(Book b : getAllBook()) {
            System.out.println("Store : " + b);
        }
    }
}
