package com.epam.ws_socet.service.handler.impl.get;

import com.epam.ws_socet.utils.constants.ResponseConstants;
import com.epam.ws_socet.service.handler.IHandle;
import com.epam.ws_socet.service.handler.method.Request;
import com.epam.ws_socet.service.handler.method.Response;

import java.io.FileReader;
import java.io.IOException;

public class GetMainPage implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
        boolean isMap = true;
        rp.setVersion(rq.getVersion());
        rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
        rp.setContentType("text/html");
        FileReader reader = new FileReader("/home/anton/Anton/Visual_Studio_Nodepad/test.html");

        StringBuilder str = new StringBuilder();
            int c;
            while((c=reader.read())!=-1){
                str.append((char)c);
            }
            rp.setBody(str.toString());
            rp.setContentLength(str.length() + "");
        rp.setConnection(ResponseConstants.CONNECTION_VALUE);
        rp.write();
    }
}