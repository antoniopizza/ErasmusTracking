package main.java.it.unisa.ErasmusTracking.util;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.resource.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LearningAgreementManager {

    private static final String TAB_NAME = "learningAgreement"; //Nome tabella nel DB


    public String db;
    public String username;
    public String password;

    public LearningAgreementManager(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(LearningAgreement learningAgreement) throws SQLException{

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL =  "INSERT INTO" + LearningAgreementManager.TAB_NAME + "VALUES (?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB LEARNING AGREEMENT

            preparedStatement.setInt(1, learningAgreement.getId());
            preparedStatement.setString(2, learningAgreement.getTipologiaErasmus());
            preparedStatement.setString(4, learningAgreement.getStato());
            preparedStatement.setString(3, learningAgreement.getStudente()); //RICORDARSI MATRICOLA

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

        String deleteSQL = "DELETE FROM " + LearningAgreementManager.TAB_NAME + " WHERE id_learning_agreement = ?";

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

    public synchronized LearningAgreement doRetrieveLearningAgreement(Studente studente) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        LearningAgreement learningAgreement = new LearningAgreement();

        String selectSQL =  "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM " + LearningAgreementManager.TAB_NAME + ", studente WHERE " + LearningAgreementManager.TAB_NAME + ".studente = ?";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, studente.getMatricola());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                learningAgreement.setId(rs.getInt("id_learning_agreement"));
                learningAgreement.setTipologiaErasmus(rs.getString("tipologiaErasmus"));
                learningAgreement.setStato(rs.getString("stato"));
                learningAgreement.setStudente(rs.getString("studente"));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return learningAgreement;
        }
}