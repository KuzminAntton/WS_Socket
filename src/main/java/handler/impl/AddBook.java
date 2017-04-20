package handler.impl;

import bean.BookPojo;
import com.epam.ws_socket.constants.ResponseConstants;
import handler.IHandle;
import method.Request;
import method.Response;
import store.Store;
import utils.jackson.JsonUtils;

import java.io.IOException;

public class AddBook implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
        boolean isMap = true;
        rp.setVersion(rq.getVersion());

        BookPojo bookCreate = null;
        try {
            bookCreate = JsonUtils.fromJson(rq.getBody(), BookPojo.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
            isMap = false;
        }

        if (isMap) {
            Store.addBook(bookCreate.getBook());
            rp.setStatusCode(ResponseConstants.STATUS_CODE_201_CREATED);
        }

        rp.write();
    }

}