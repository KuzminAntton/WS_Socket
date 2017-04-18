package handler;


import handler.impl.AddBook;
import handler.impl.GetAllBooks;
import handler.impl.HelloHandler;

public class HandlerFactory {

    private static final HandlerFactory instance = new HandlerFactory();

    private final AddBook addBook = new AddBook();

    private final HelloHandler helloHandler = new HelloHandler();

    private final GetAllBooks getAllBooks = new GetAllBooks();

    private final HndlerDirection hndlerDirection = new HndlerDirection();

    public HandlerFactory() {

    }

    public static HandlerFactory getInstance() {
        return instance;
    }

    public AddBook getAddBook() {
        return addBook;
    }


    public HelloHandler getHelloHandler() {
        return helloHandler;
    }


    public GetAllBooks getAllBooks() {
        return getAllBooks;
    }

    public HndlerDirection getHndlerDirection() {
        return hndlerDirection;
    }

}
