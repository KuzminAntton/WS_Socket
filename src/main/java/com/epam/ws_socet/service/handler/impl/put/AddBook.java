package com.epam.ws_socet.service.handler.impl.put;

import com.epam.ws_socet.bean.BookPojo;
import com.epam.ws_socet.utils.constants.ResponseConstants;
import com.epam.ws_socet.service.handler.IHandle;
import com.epam.ws_socet.service.handler.method.Request;
import com.epam.ws_socet.service.handler.method.Response;
import com.epam.ws_socet.store.Store;
import com.epam.ws_socet.utils.jackson.JsonUtils;

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