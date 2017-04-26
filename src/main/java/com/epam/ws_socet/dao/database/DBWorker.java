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
    private String deleteStatement = "DELETE FROM Books.books";
    private String addStatement = "INSERT INTO Books.books (book_language,book_edition,book_author,book_date) VALUES " +
            "(?,?,?,?)";
    private String updateStatement = "UPDATE Books.books";
    private String sqlSearchByConcreteCriteriaInquiry = "SELECT * FROM books where ";


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

        preparedStatement = con.prepareStatement(sqlSearchByFreeCriteriaInquiry);

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

    public boolean deleteCertainBooks(Book book) throws DAOException {
        boolean isDelete = false;

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(deleteStatement + " WHERE " +
                    "book_language ='"+ book.getLanguage()+"'"
                    +"and book_edition ='"+ book.getEdition()+"'"
                    +"and book_date ='"+ book.getDate()+"'"
                    +"and book_author ='"+ book.getAuthor()+"'"
            );

            preparedStatement.executeUpdate();

            isDelete = true;

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


        return isDelete;
    }



    public void addCertainBooks(Book book) throws DAOException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(addStatement);

            preparedStatement.setString(1, book.getLanguage());
            preparedStatement.setString(2, book.getEdition());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getDate());

            preparedStatement.executeUpdate();

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
    }

    public void updateCertainBooks(Book book) throws DAOException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(updateStatement + " SET " +
                    "book_language ='"+ book.getLanguage()+"'"
                    +", book_edition ='"+ book.getEdition()+"'"
                    +", book_date ='"+ book.getDate()+"'"
                    +", book_author ='"+ book.getAuthor()+"'"
                    +"WHERE book_id = '" + book.getId() +"'"
            );

            preparedStatement.executeUpdate();

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
    }

}




