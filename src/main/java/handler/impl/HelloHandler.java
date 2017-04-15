package handler.impl;

import handler.IHandle;
import method.Request;
import method.Response;

import java.io.FileReader;
import java.io.IOException;

public class HelloHandler implements IHandle {
    public void handle(Request rq, Response rp) throws IOException {
        boolean isMap = true;
        rp.setVersion(rq.getVersion());
        rp.setStatusCode("200");
        rp.setContentType("text/html");
        FileReader reader = new FileReader("/home/anton/Anton/Visual_Studio_Nodepad/test.html");

        StringBuilder str = new StringBuilder();
            int c;
            while((c=reader.read())!=-1){
                str.append((char)c);
            }
        rp.setBody(str.toString());
        rp.write();

    }
}
