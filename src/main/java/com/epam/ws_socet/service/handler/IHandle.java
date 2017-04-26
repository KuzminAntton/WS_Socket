package com.epam.ws_socet.service.handler;

import com.epam.ws_socet.dao.exception.DAOException;
import com.epam.ws_socet.service.handler.method.Request;
import com.epam.ws_socet.service.handler.method.Response;

import java.io.IOException;

public interface IHandle {
    public void handle(Request rq, Response rp) throws IOException, DAOException;
}
