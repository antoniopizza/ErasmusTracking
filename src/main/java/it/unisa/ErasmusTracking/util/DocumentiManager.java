package main.java.it.unisa.ErasmusTracking.util;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.resource.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class DocumentiManager {

    private static final String TAB_NAME = "documenti"; //Nome tabella nel DB
    public String db;
    public String username;
    public String password;

    public DocumentiManager(String db, String username, String password) {

        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Documenti documento) throws SQLException{

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + DocumentiManager.TAB_NAME + " VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, documento.getId());
            preparedStatement.setString(2, documento.getNome());
            preparedStatement.setString(3, documento.getDataCaricamento());
            preparedStatement.setString(4, documento.getUrl());
            preparedStatement.setInt(5, documento.getProprietario());


            preparedStatement.executeUpdate();

            connection.commit();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    //Genera query DELETE per eliminare la riga identificata da 'id' all'interno del DB
    public synchronized boolean doDeleteString(int id)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + DocumentiManager.TAB_NAME + " WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return (result != 0);
    }

    //Genera query SELECT per ricevere i dati in base a quella determinata key

    public synchronized Documenti doRetrieveClientById(int id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Documenti bean = new Documenti();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return bean;
    }

    //genera query SELECT * per prendere tutte le righe dal DB

    public synchronized Collection<Documenti> doRetrieveAllDocument() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Documenti> documenti = new LinkedList<Documenti>();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Documenti bean = new Documenti();
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));

                documenti.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return documenti;

    }

    public synchronized Collection<Documenti> doRetrieveDocumentByIdStudent(int IDproprietario) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Documenti> documenti = new LinkedList<Documenti>();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE proprietario = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, IDproprietario);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Documenti bean = new Documenti();
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));

                documenti.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return documenti;

    }
    /*public synchronized Collection<Documenti> doRetrieveDocumentByUsernameStudent(String username) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Documenti> documenti = new LinkedList<Documenti>();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE proprietario = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, IDproprietario);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Documenti bean = new Documenti();
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));

                documenti.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return documenti;

    }
*/
}