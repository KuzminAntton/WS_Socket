package handler;


import handler.impl.put.AddBook;
import handler.impl.get.GetAllBooks;
import handler.impl.get.GetCertainBook;
import handler.impl.get.GetMainPage;

public class HandlerFactory {

    private static final HandlerFactory instance = new HandlerFactory();

    private final AddBook addBook = new AddBook();

    private final GetMainPage getMainPage = new GetMainPage();

    private final GetAllBooks getAllBooks = new GetAllBooks();

    private final HandlerRouter handlerRouter = new HandlerRouter();

    private final GetCertainBook getCertainBook = new GetCertainBook();

    public HandlerFactory() {

    }

    public static HandlerFactory getInstance() {
        return instance;
    }

    public AddBook getAddBook() {
        return addBook;
    }


    public GetMainPage getGetMainPage() {
        return getMainPage;
    }


    public GetAllBooks getAllBooks() {
        return getAllBooks;
    }

    public GetCertainBook getCertainBook() {
        return getCertainBook;
    }


    public HandlerRouter getHandlerRouter() {
        return handlerRouter;
    }

}
