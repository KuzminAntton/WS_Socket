package com.epam.ws_socet.controller;

import com.epam.ws_socet.service.handler.HandlerFactory;
import com.epam.ws_socet.service.handler.method.Request;
import com.epam.ws_socet.service.handler.method.Response;
import com.epam.ws_socet.utils.constants.ActionConstants;

import java.io.IOException;

public class HandlerRouter {

    public void direct(Request rq, Response rp) throws IOException {
        HandlerFactory handlerFactory = HandlerFactory.getInstance();
        if(rq.getPath().equals(ActionConstants.ACTION_MAIN_PAGE)){
            handlerFactory.getGetMainPage().handle(rq,rp);
        } else if(rq.getPath().contains(ActionConstants.ACTION_GET_CERTAIN_BOOK)) {
            handlerFactory.getCertainBook().handle(rq, rp);
        } else if(rq.getPath().contains(ActionConstants.ACTION_ADD_BOOK)) {
            handlerFactory.getAddBook().handle(rq,rp);
        } else if(rq.getPath().contains(ActionConstants.ACTION_GET_ALL_BOOKS)) {
            handlerFactory.getAllBooks().handle(rq,rp);
        }
    }
}