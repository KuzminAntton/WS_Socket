package handler;

import com.epam.ws_socket.constants.ActionConstants;
import method.Request;
import method.Response;

import java.io.IOException;

public class HandlerRouter {

    public void direct(Request rq, Response rp) throws IOException {
        HandlerFactory handlerFactory = HandlerFactory.getInstance();
        if(rq.getPath().equals(ActionConstants.ACTION_MAIN_PAGE)){
            handlerFactory.getHelloHandler().handle(rq,rp);
            System.out.println(rp);
        } else if(rq.getPath().contains(ActionConstants.ACTION_ADD_BOOK)) {
            handlerFactory.getCertainBook().handle(rq, rp);
        } else if(rq.getPath().contains(ActionConstants.ACTION_ADD_BOOK)) {
            handlerFactory.getAddBook().handle(rq,rp);
        } else if(rq.getPath().contains(ActionConstants.ACTION_GET_ALL_BOOKS)) {
            handlerFactory.getAllBooks().handle(rq,rp);
        }
    }
}