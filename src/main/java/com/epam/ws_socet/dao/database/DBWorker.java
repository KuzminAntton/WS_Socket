package com.epam.ws_socet.dao.database;

import com.epam.ws_socet.dao.exception.ConnectionPoolException;
import com.epam.ws_socet.dao.exception.DAOException;

public class DBWorker {

    private ConnectionPool pool = new ConnectionPool();

    public void init() throws DAOException {
        try {
            pool.initPollData();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    public void destroy() {
        pool.dispose();
    }

}
