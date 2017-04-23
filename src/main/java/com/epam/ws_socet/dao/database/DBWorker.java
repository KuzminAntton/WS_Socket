package com.epam.ws_socet.dao.database;

import com.epam.ws_socet.bean.Book;
import com.epam.ws_socet.dao.exception.ConnectionPoolException;
import com.epam.ws_socet.dao.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DBWorker {

    private static final Logger log = LogManager.getRootLogger();

    private ConnectionPool pool = new ConnectionPool();
    private String sqlSearchByFreeCriteriaInquiry = "SELECT * FROM books";
    private String sqlSearchByConcreteCriteriaInquiry = "SELECT * FROM books where ";
    private String sqlAddCommand = "INSERT INTO books(book_language,book_edition,book_author,book_date) " +
            "VALUES(?,?,?,?,?)";

    private String id = "book_id";
    private String languageRowName = "book_language";
    private String editionRowName = "book_edition";
    private String authorRowName = "book_author";
    private String dateRowName = "book_date";

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


    public HashSet<Book> getAllBooks() throws DAOException {

        HashSet<Book> books = new HashSet();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
        con = pool.takeConnection();

        preparedStatement = (PreparedStatement) con.prepareStatement(sqlSearchByFreeCriteriaInquiry);

        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = Integer.parseInt(rs.getString(this.id));
            String language = rs.getString(languageRowName);
            String edition = rs.getString(editionRowName);
            String author = rs.getString(authorRowName);
            String date = rs.getString(dateRowName);

            books.add(new Book(id,language,edition,author,date));

        }

    } catch (SQLException | ConnectionPoolException e) {
        log.error(e);
        throw new DAOException(e);
    } finally {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }

        try {
            con.close();
        } catch (SQLException e) {
            log.error(e);
        }

    }

        return books;
}
    }


