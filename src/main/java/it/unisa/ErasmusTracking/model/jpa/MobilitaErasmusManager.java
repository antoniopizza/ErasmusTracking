package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.IMobilitaErasmusDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class MobilitaErasmusManager implements IMobilitaErasmusDao {

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
    public synchronized void doSave(MobilitaErasmus mobilitaErasmus){

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
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized boolean doDelete(int id) {
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
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }  catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (result != 0);
    }
    @Override
    public void doSave(Object object) {

    }

    @Override
    public List<?> doRetrieveAll() {
        return null;
    }

    @Override
    public Object doRetrieveById(int id) {
        return null;
    }

    public synchronized MobilitaErasmus doRetrieveById(SendingInstitution sendingInstitution) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mobilitaErasmus;
    }
}