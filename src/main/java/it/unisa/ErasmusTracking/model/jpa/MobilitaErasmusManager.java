package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.resource.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MobilitaErasmusManager {

    private static final String TAB_NAME = "mobilitaErasmus"; //Nome tabella nel DB


    public String db;
    public String username;
    public String password;

    public MobilitaErasmusManager(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(MobilitaErasmus mobilitaErasmus) throws SQLException{

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL =  "INSERT INTO" + MobilitaErasmusManager.TAB_NAME + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB LEARNING AGREEMENT

            preparedStatement.setInt(1, mobilitaErasmus.getId());
            preparedStatement.setString(2, mobilitaErasmus.getDataInizio());
            preparedStatement.setString(4, mobilitaErasmus.getDataFine());
            preparedStatement.setString(3, mobilitaErasmus.getStato());
            preparedStatement.setInt(4, mobilitaErasmus.getSendingInstitute());
            preparedStatement.setInt(5, mobilitaErasmus.getReceivingInstitute());
            preparedStatement.setInt(6, mobilitaErasmus.getLearningAgreement());



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

    public synchronized boolean doDeleteString(int id)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + MobilitaErasmusManager.TAB_NAME + " WHERE id_mobilita_erasmus = ?";

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

    public synchronized MobilitaErasmus doRetrieveMobilitaErasmusById(SendingInstitution sendingInstitution) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        MobilitaErasmus mobilitaErasmus = new MobilitaErasmus();

        String selectSQL =  "SELECT id_mobilita_erasmus, data_inizio, data_fine, stato, sending_institute, receiving_institute, learning_agreement FROM " + MobilitaErasmusManager.TAB_NAME + "WHERE " + MobilitaErasmusManager.TAB_NAME + ".id_mobilita_erasmus = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, sendingInstitution.getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                mobilitaErasmus.setId(rs.getInt("id_mobilita_erasmus"));
                mobilitaErasmus.setDataInizio(rs.getString("data_inizio"));
                mobilitaErasmus.setDataFine(rs.getString("data_fine"));
                mobilitaErasmus.setStato(rs.getString("stato"));
                mobilitaErasmus.setSendingInstitute(rs.getInt("sending_institute"));
                mobilitaErasmus.setReceivingInstitute(rs.getInt("receiving_institute"));
                mobilitaErasmus.setLearningAgreement(rs.getInt("learning_agreement"));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return mobilitaErasmus;
    }
}