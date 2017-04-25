package com.epam.ws_socet.view;

import com.epam.ws_socet.dao.database.DBWorker;
import com.epam.ws_socet.dao.factory.DAOFactory;
import com.epam.ws_socet.server.Server;

public class Main {
    public static void main(String[] args) throws Throwable {
        DBWorker dbWorker = DAOFactory.getInstance().getDbWorker();
        dbWorker.init();
        Server socketServer = new Server(8080,123);
        socketServer.start();

    }
}
