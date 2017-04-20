package handler.impl.get;

import bean.Book;
import bean.BookPojo;
import com.epam.ws_socket.constants.CommonConstants;
import com.epam.ws_socket.constants.ResponseConstants;
import method.Request;
import method.Response;
import store.Store;
import utils.jackson.JsonUtils;
import utils.xml.XMLHelper;

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
            bookCreate = JsonUtils.fromJson(rq.getBody(), BookPojo.class);
            Book book = bookCreate.getBook();
            if(Store.getAllBook().contains(book)) {
                if (acceptType.equals(CommonConstants.ACCEPT_TYPE_XML)) {
                    XMLHelper.writeBookInXMLFormat(book, body, rp);
                } else {
                    JsonUtils.writeBookInJsonFormat(book, body, rp);
                }
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
