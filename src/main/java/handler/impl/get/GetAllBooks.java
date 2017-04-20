package handler.impl.get;

import bean.Book;
import bean.BooksPojo;
import com.epam.ws_socket.constants.CommonConstants;
import com.epam.ws_socket.constants.ResponseConstants;
import handler.IHandle;
import method.Request;
import method.Response;
import store.Store;
import utils.jackson.JsonUtils;
import utils.xml.XMLHelper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
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
            XMLHelper.writeBookInXMLFormat(book, body, rp);
        } else {
            JsonUtils.writeBookInJsonFormat(book, body, rp);
        }

        try {
            rp.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}