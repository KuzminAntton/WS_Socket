package method;

import com.epam.ws_socket.constants.CommonConstants;
import utils.HttpMethodUtils;
import utils.SplitUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Request {

    private String method;
    private String path;
    private String version;
    private String host;
    private String connection;
    private String csp;
    private String cacheControl;
    private String userAgent;
    private String accept;
    private String acceptEncoding;
    private String acceptLanguage;
    private int contentLenght = 0;
    private String origin;
    private String contentType;
    private String body;


//    public Request(BufferedReader bfr) throws IOException {
//        parseRequest(bfr);
//    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCsp() {
        return csp;
    }

    public void setCsp(String csp) {
        this.csp = csp;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public int getContentLenght() {
        return contentLenght;
    }

    public void setContentLenght(int contentLenght) {
        this.contentLenght = contentLenght;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public void parseRequest(BufferedReader bfr) {
            List<String> headerValue = null;
            try {
                headerValue = HttpMethodUtils.getHeaderValue(bfr);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String value : headerValue) {
                if (value.startsWith(CommonConstants.GET)) {

                    method = CommonConstants.GET;
                    path = SplitUtils.getCertainSplitValueBy(value, CommonConstants.PATH, CommonConstants.SPACE);
                    version = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VERSION, CommonConstants.SPACE);

                } else if (value.startsWith(CommonConstants.POST)) {

                    method = CommonConstants.POST;
                    path = SplitUtils.getCertainSplitValueBy(value, CommonConstants.PATH, CommonConstants.SPACE);
                    version = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VERSION, CommonConstants.SPACE);

                } else if (value.startsWith(CommonConstants.HOST)) {

                    host = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.CONNECTION)) {

                    connection = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.CSP)) {

                    csp = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.CACHE_CONTROL)) {

                    cacheControl = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.USER_AGENT)) {

                    userAgent = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.ACCEPT)) {

                    accept = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.ACCEPT_ENCODING)) {

                    acceptEncoding = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.ACCEPT_LANGUAGE)) {

                    acceptLanguage = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.CONTENT_LENGTH)) {

                    contentLenght = Integer.parseInt(SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE,
                            CommonConstants.COLON_SPLITTER));

                } else if (value.startsWith(CommonConstants.ORIGIN)) {

                    origin = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);

                } else if (value.startsWith(CommonConstants.CONTENT_TYPE)) {

                    contentType = SplitUtils.getCertainSplitValueBy(value, CommonConstants.VALUE, CommonConstants.COLON_SPLITTER);
                }
            }

            if (contentLenght > 0) {
                body = headerValue.get(headerValue.size() - 1);
            }

        }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' + "\n" +
                "path='" + path + '\'' + "\n" +
                "version='" + version + '\'' + "\n" +
                "host='" + host + '\'' + "\n" +
                "connection='" + connection + '\'' + "\n" +
                "csp='" + csp + '\'' + "\n" +
                "cacheControl='" + cacheControl + '\'' + "\n" +
                "userAgent='" + userAgent + '\'' + "\n" +
                "accept='" + accept + '\'' + "\n" +
                "acceptEncoding='" + acceptEncoding + '\'' + "\n" +
                "acceptLanguage='" + acceptLanguage + '\'' + "\n" +
                "contentLenght=" + contentLenght + "\n" +
                "origin='" + origin + '\'' + "\n" +
                "contentType='" + contentType + '\'' + "\n" +
                "body='" + body + '\'' + "\n" +
                '}';
    }
}

