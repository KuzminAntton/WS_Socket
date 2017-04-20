package utils.xml;

import bean.Book;
import bean.BooksPojo;
import method.Response;
import utils.marshaller.MarshallerHelper;

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
