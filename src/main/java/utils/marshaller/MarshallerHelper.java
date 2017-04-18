package utils.marshaller;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by anton on 18.4.17.
 */
public class MarshallerHelper {
    /**
     * Marshaller/Unmarshaller implementation
     */
    private static final Marshaller marshaller = Marshaller.getInstance();

    public static void marshall(Object jaxbElement, Result paramResult) {
        marshaller.marshall(jaxbElement, paramResult);
    };

    /**
     * Serializes JAXBElement into File
     *
     * @param jaxbElement
     *            - JAXBElement
     * @param resultFile
     *            - File
     */
    public static void marshall(Object jaxbElement, File resultFile) {
        marshall(jaxbElement, new StreamResult(resultFile));
    };

    /**
     * Serializes JAXBElement to OutputStream
     *
     * @param jaxbElement
     *            - JAXBElement
     * @param os
     *            - OutputStream
     */
    public static void marshall(Object jaxbElement, OutputStream os) {
        marshall(jaxbElement, new StreamResult(os));
    };

    /**
     * Create JAXBElement from Source
     *
     * @param <T>
     * @param resultClazz
     * @return
     */
    public static <T> T unmarshall(Source source, Class<T> resultClazz) {
        return marshaller.unmarshall(source, resultClazz);
    };

    /**
     * Create JAXBElement from File
     *
     * @param <T>
     * @param file
     * @param resultClazz
     * @return
     */
    public static <T> T unmarshall(File file, Class<T> resultClazz) {
        return marshaller.unmarshall(file, resultClazz);
    };

    /**
     * Create JAXBElement from File
     *
     * @param <T>
     * @param resultClazz
     * @return
     */
    public static <T> T unmarshall(InputStream is, Class<T> resultClazz) {
        return marshaller.unmarshall(is, resultClazz);
    };

    public static <T> T unmarshall(String string, Class<T> resultClazz) {
        return marshaller.unmarshall(string, resultClazz);
    };

    /**
     * Serializes JAXBElement into Writer
     *
     * @param jaxbElement
     * @param writer
     */
    public static void marshall(Object jaxbElement, Writer writer) {
        marshaller.marshall(jaxbElement, writer);
    };
}

