package com.epam.ws_socet.utils.xml;

import com.epam.ws_socet.bean.Book;
import com.epam.ws_socet.bean.BooksPojo;
import com.epam.ws_socet.service.handler.method.Response;
import com.epam.ws_socet.utils.marshaller.MarshallerHelper;

import java.io.StringWriter;

public class XMLHelper {
    public static void writeBookInXMLFormat(Book book, String body, Response rp) {

        StringWriter writer = new StringWriter();
        MarshallerHelper.marshall(book, writer);

        body = writer.toString();

        rp.setContentLength(String.valueOf(body.getBytes().length));
        rp.setBody(body);

    }

    public static void writeBookInXMLFormat(BooksPojo book, String body, Response rp) {

        StringWriter writer = new StringWriter();
        MarshallerHelper.marshall(book, writer);

        body = writer.toString();

        rp.setContentLength(String.valueOf(body.getBytes().length));
        rp.setBody(body);

    }
}
