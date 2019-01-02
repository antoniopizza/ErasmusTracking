package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SendingInstituteManager {

    private static final String TAB_NAME = "sendingInstitute"; //Nome tabella nel DB


    public String db;
    public String username;
    public String password;

    public SendingInstituteManager(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(SendingInstitution sendingInstitute) throws SQLException{

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL =  "INSERT INTO" + SendingInstituteManager.TAB_NAME + "VALUES (?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB LEARNING AGREEMENT

            preparedStatement.setInt(1, sendingInstitute.getId());
            preparedStatement.setString(2, sendingInstitute.getCodiceErasmus());
            preparedStatement.setString(4, sendingInstitute.getDipartimento());
            preparedStatement.setString(3, sendingInstitute.getIndirizzo());

            //

            System.out.println(preparedStatement.toString());

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

    public synchronized boolean doDelete(int id)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + SendingInstituteManager.TAB_NAME + " WHERE id_sending_institute = ?";

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

    public synchronized SendingInstitution doRetrieveById(SendingInstitution sendingInstitution) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        SendingInstitution sendingInstitute = new SendingInstitution();

        String selectSQL =  "SELECT id_sending_institute, codice_erasmus, dipartimento, indirizzo  FROM " + SendingInstituteManager.TAB_NAME + "WHERE " + SendingInstituteManager.TAB_NAME + ".id_sending_institute = ?";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, sendingInstitution.getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                sendingInstitute.setId(rs.getInt("id_sending_institute"));
                sendingInstitute.setCodiceErasmus(rs.getString("codice_erasmus"));
                sendingInstitute.setDipartimento(rs.getString("dipartimento"));
                sendingInstitute.setIndirizzo(rs.getString("indirizzo"));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return sendingInstitute;
    }
}