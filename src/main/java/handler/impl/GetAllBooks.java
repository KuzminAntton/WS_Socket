package handler.impl;

import bean.Book;
import bean.BooksPojo;
import com.epam.ws_socket.constants.CommonConstants;
import com.epam.ws_socket.constants.ResponseConstants;
import handler.IHandle;
import method.Request;
import method.Response;
import store.Store;
import utils.jackson.JsonUtils;
import utils.marshaller.MarshallerHelper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;


public class GetAllBooks implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
        String acceptType = rq.getAccept();

        try {
            response(rq, rp, acceptType);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void response(Request rq, Response rp, String acceptType) throws JAXBException {
        String body = "";
        HashSet<Book> books = Store.getAllBook();

        rp.setVersion(rq.getVersion());
        rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
        rp.setContentType(rq.getAccept());

        BooksPojo book = new BooksPojo(books);

        if (acceptType.equals(CommonConstants.ACCEPT_TYPE_XML)) {

            StringWriter writer = new StringWriter();
            MarshallerHelper.marshall(book, writer);

            body = writer.toString();

            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);
        } else {
            body = JsonUtils.toJson(book);
            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);
        }

        try {
            rp.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}