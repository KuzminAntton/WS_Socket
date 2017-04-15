package handler;

import method.Request;
import method.Response;

import java.io.IOException;

public interface IHandle {
    public void handle(Request rq, Response rp) throws IOException;
}
