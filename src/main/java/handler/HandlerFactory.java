package handler;


import handler.impl.AddBook;
import handler.impl.GetAllBooks;
import handler.impl.MainPage;

public class HandlerFactory {

    private static final HandlerFactory instance = new HandlerFactory();

    private final AddBook addBook = new AddBook();

    private final MainPage helloHandler = new MainPage();

    private final GetAllBooks getAllBooks = new GetAllBooks();

    private final HandlerRouter hndlerDirection = new HandlerRouter();

    public HandlerFactory() {

    }

    public static HandlerFactory getInstance() {
        return instance;
    }

    public AddBook getAddBook() {
        return addBook;
    }


    public MainPage getHelloHandler() {
        return helloHandler;
    }


    public GetAllBooks getAllBooks() {
        return getAllBooks;
    }

    public HandlerRouter getHndlerDirection() {
        return hndlerDirection;
    }

}
