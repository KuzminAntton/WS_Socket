package com.epam.ws_socet.service.handler.impl.get;

import com.epam.ws_socet.bean.Book;
import com.epam.ws_socet.bean.BookPojo;
import com.epam.ws_socet.utils.constants.ResponseConstants;
import com.epam.ws_socet.service.handler.method.Request;
import com.epam.ws_socet.service.handler.method.Response;
import com.epam.ws_socet.store.Store;
import com.epam.ws_socet.utils.jackson.JsonUtils;
import com.epam.ws_socet.utils.marshaller.MarshallerHelper;
import com.epam.ws_socet.utils.xml.XMLHelper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class GetCertainBook {
    public void handle(Request rq, Response rp) throws IOException {
    String acceptType = rq.getAccept();

        try {
        response(rq, rp, acceptType);
    } catch (JAXBException e) {
        e.printStackTrace();
    }
}

    private void response(Request rq, Response rp, String acceptType) throws JAXBException, IOException {
        boolean isMap = true;
        String body = "";
        rp.setVersion(rq.getVersion());

        BookPojo bookCreate = null;
        try {
            if(rq.getContentType().contains("application/json")) {
                bookCreate = JsonUtils.fromJson(rq.getBody(), BookPojo.class);
                Book book = bookCreate.getBook();
                if(Store.getAllBook().contains(book)) {
                    JsonUtils.writeBookInJsonFormat(book, body, rp);
                }
            } else if (rq.getContentType().contains("application/xml")) {
                bookCreate = MarshallerHelper.unmarshall(rq.getBody(), BookPojo.class);
                Book book = bookCreate.getBook();
                XMLHelper.writeBookInXMLFormat(book, body, rp);
            }

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
