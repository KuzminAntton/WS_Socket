package method;

import constants.CommonConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public void parseRequest(BufferedReader bfr) throws IOException {
        Map<String,String> headers = new LinkedHashMap<String, String>();
        String str = "";
        String [] strArr;
        String header;
        String value = "";
        while(true) {
            str = bfr.readLine();

            strArr = str.split(" ");

            header = strArr[0];

            for(int i = 1; i < strArr.length; i++) {
                value += strArr[i] + " ";
            }

            headers.put(header,value);
            header = "";
            value = "";


            if(str == null || str.trim().length() == 0) {
                    break;
                }
        }

        for(Map.Entry<String, String> pair : headers.entrySet()) {
            if(pair.getKey().contains(CommonConstants.GET)) {
                setMethod(pair.getKey());
                setPath(pair.getValue().split(" ")[0]);
                setVersion(pair.getValue().split(" ")[1]);
            }
            if(pair.getKey().contains(CommonConstants.POST)) {
                setMethod(pair.getKey());
            }
            if(pair.getKey().contains(CommonConstants.USER_AGENT)) {
                setUserAgent(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.ACCEPT_ENCODING)) {
                setAcceptEncoding(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.ACCEPT)) {
                setAccept(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.ACCEPT_LANGUAGE)) {
                setAcceptLanguage(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.CONNECTION)) {
                setConnection(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.CONTENT_LENGTH)) {
                setContentLenght(Integer.parseInt(pair.getValue()) );
            }
            if(pair.getKey().contains(CommonConstants.CONTENT_TYPE)) {
                setContentType(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.CACHE_CONTROL)) {
                setCacheControl(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.VERSION + "")) {
                setVersion(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.HOST)) {
                setHost(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.CSP)) {
                setCsp(pair.getValue());
            }
            if(pair.getKey().contains(CommonConstants.ORIGIN)) {
                setOrigin(pair.getValue());
            }

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

