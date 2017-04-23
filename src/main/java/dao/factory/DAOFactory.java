package dao.factory;

import dao.database.DBWorker;

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