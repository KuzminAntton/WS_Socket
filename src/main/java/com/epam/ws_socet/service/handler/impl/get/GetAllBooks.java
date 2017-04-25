package com.epam.ws_socet.service.handler.impl.get;

import com.epam.ws_socet.bean.Book;
import com.epam.ws_socet.bean.BooksPojo;
import com.epam.ws_socet.dao.database.DBWorker;
import com.epam.ws_socet.dao.exception.DAOException;
import com.epam.ws_socet.dao.factory.DAOFactory;
import com.epam.ws_socet.service.handler.IHandle;
import com.epam.ws_socet.service.handler.method.Request;
import com.epam.ws_socet.service.handler.method.Response;
import com.epam.ws_socet.utils.DataUtils;
import com.epam.ws_socet.utils.constants.CommonConstants;
import com.epam.ws_socet.utils.constants.ResponseConstants;
import com.epam.ws_socet.utils.jackson.JsonUtils;
import com.epam.ws_socet.utils.xml.XMLHelper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashSet;


public class GetAllBooks implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
        String acceptType = rq.getAccept();

        try {
            response(rq, rp, acceptType);
        } catch (JAXBException | DAOException e) {
            e.printStackTrace();
        }
    }

    private void response(Request rq, Response rp, String acceptType) throws JAXBException, DAOException {
        String body = "";
        //HashSet<Book> books = Store.getAllBook();

        DBWorker dbWorker = DAOFactory.getInstance().getDbWorker();
        HashSet<Book> books = dbWorker.getAllBooks();

        rp.setVersion(rq.getVersion());
        rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
        rp.setContentType(rq.getAccept());
        rp.setServer(ResponseConstants.SERVER_VALUE);
        rp.setDate(DataUtils.getCurrentDataByFormat(CommonConstants.DATA_FORMAT_FOR_RESPONSE));

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