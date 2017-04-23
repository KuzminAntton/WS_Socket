package com.epam.ws_socet.dao.factory;

import com.epam.ws_socet.dao.database.DBWorker;
import com.epam.ws_socet.dao.database.DBWorker;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DBWorker dbWorker = new DBWorker();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }


    public DBWorker getDbWorker() {
        return dbWorker;
    }

}