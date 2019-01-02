package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReceivingInstituteManager {

    private static final String TAB_NAME = "receivingInstitute"; //Nome tabella nel DB


    public String db;
    public String username;
    public String password;

    public ReceivingInstituteManager(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(ReceivingInstitute receivingInstitute) throws SQLException{

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL =  "INSERT INTO" + ReceivingInstituteManager.TAB_NAME + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB LEARNING AGREEMENT

            preparedStatement.setInt(1, receivingInstitute.getId());
            preparedStatement.setString(2, receivingInstitute.getCodiceErasmus());
            preparedStatement.setString(4, receivingInstitute.getNomeContatto());
            preparedStatement.setString(3, receivingInstitute.getEmailContatto());
            preparedStatement.setString(4, receivingInstitute.getSizeOfEnterprise());
            preparedStatement.setString(5, receivingInstitute.getNomeMentore());
            preparedStatement.setString(6, receivingInstitute.getEmailMentore());
            preparedStatement.setString(7, receivingInstitute.getWebsite());
            preparedStatement.setInt(8, receivingInstitute.getLocalita());



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

        String deleteSQL = "DELETE FROM " + ReceivingInstituteManager.TAB_NAME + " WHERE id_receiving_institute = ?";

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

    public synchronized ReceivingInstitute doRetrieveById(SendingInstitution sendingInstitution) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ReceivingInstitute receivingInstitute = new ReceivingInstitute();

        String selectSQL =  "SELECT id_receiving_institute, codice_erasmus, nome_contatto, e_mail_contatto, size_of_enterprise, nome_mentore, e_mail_mentore, website, location  FROM " + ReceivingInstituteManager.TAB_NAME + "WHERE " + ReceivingInstituteManager.TAB_NAME + ".id_receiving_institute = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, sendingInstitution.getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                receivingInstitute.setId(rs.getInt("id_receiving_institute"));
                receivingInstitute.setCodiceErasmus(rs.getString("codice_erasmus"));
                receivingInstitute.setNomeContatto(rs.getString("nome_contatto"));
                receivingInstitute.setEmailContatto(rs.getString("e_mail_contatto"));
                receivingInstitute.setSizeOfEnterprise(rs.getString("size_of_enterprise"));
                receivingInstitute.setNomeMentore(rs.getString("nome_mentore"));
                receivingInstitute.setEmailMentore(rs.getString("e_mail_mentore"));
                receivingInstitute.setWebsite(rs.getString("website"));
                receivingInstitute.setLocalita(rs.getInt("localita"));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return receivingInstitute;
    }
}