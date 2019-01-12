package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.*;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class LearningAgreementManager implements ILearningAgreementDao {

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
    public synchronized void doSave(Object object) {

        LearningAgreement learningAgreement = (LearningAgreement) object;
        StudenteManager studenteManager = new StudenteManager(db,username,password);
        System.out.println("LearningAgreementMan.jaa 37:   "+learningAgreement.getStudente().getId());
        Studente studente = (Studente) studenteManager.doRetrieveById(learningAgreement.getStudente().getId());
        System.out.println(studente.toString()) ;

        if(learningAgreement.getStato()==null && learningAgreement.getTipologiaErasmus() == null){
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;

            String insertSQL =  "INSERT INTO " + LearningAgreementManager.TAB_NAME + " (tipologiaErasmus, " +
                    "stato, livello_conoscenza_lingua, studente) VALUES (NULL, NULL , NULL , ?)";


            try {
                connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement1 = connection1.prepareStatement(insertSQL);

                preparedStatement1.setInt(1, learningAgreement.getStudente().getId()); //RICORDARSI MATRICOLA

                //

                System.out.println(preparedStatement1.toString());

                preparedStatement1.executeUpdate();


                //  connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement1 != null)
                        preparedStatement1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        DriverManagerConnectionPool.releaseConnection(connection1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            String insertSQL = "INSERT INTO " + LearningAgreementManager.TAB_NAME + " (tipologiaErasmus, " +
                    "stato, livello_conoscenza_lingua, studente) VALUES (?, ?, ?, ?)";


            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);

                // TAB LEARNING AGREEMENT

                preparedStatement.setString(1, learningAgreement.getTipologiaErasmus());
                preparedStatement.setString(2, learningAgreement.getStato());
                preparedStatement.setInt(4, learningAgreement.getStudente().getId()); //RICORDARSI MATRICOLA
                preparedStatement.setString(3, learningAgreement.getConoscenzaLingua()); //

                //

                System.out.println(preparedStatement.toString());

                preparedStatement.executeUpdate();

                //  connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        DriverManagerConnectionPool.releaseConnection(connection);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized boolean doDelete(int id) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (result != 0);
    }

    @Override
    public List<?> doRetrieveAll() {
        return null;
    }

    public synchronized LearningAgreement doRetrieveByStudente(int idStudente){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        LearningAgreement learningAgreement = new LearningAgreement();

        String selectSQL =  "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM " + LearningAgreementManager.TAB_NAME + ", studente WHERE " + LearningAgreementManager.TAB_NAME + ".studente = ?";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idStudente);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                learningAgreement.setId(rs.getInt("id_learning_agreement"));
                learningAgreement.setTipologiaErasmus(rs.getString("tipologiaErasmus"));
                learningAgreement.setStato(rs.getString("stato"));

                //ON HOLD

                IStudenteDao studenteManager = new StudenteManager(db, username, password);
                Studente studente = (Studente) studenteManager.doRetrieveById(idStudente);
                System.out.println("Studente learning: " + studente.toString());
                learningAgreement.setStudente(studente);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return learningAgreement;
    }

    public synchronized LearningAgreement doRetrieveById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        LearningAgreement learningAgreement = new LearningAgreement();

        String selectSQL =  "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM " + LearningAgreementManager.TAB_NAME + " WHERE id_learning_agreement = ?";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                learningAgreement.setId(rs.getInt("id_learning_agreement"));
                learningAgreement.setTipologiaErasmus(rs.getString("tipologiaErasmus"));
                learningAgreement.setStato(rs.getString("stato"));

                //ON HOLD

                /*
                IStudenteDao studenteManager = new StudenteManager();
                Studente studente = studenteManager.doRetrieveByMatricola(rs.getString("studente"));
                learningAgreement.setStudente(studente);
                */
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return learningAgreement;
    }


    public synchronized void doUpdate(Object object) {

        LearningAgreement learningAgreement = (LearningAgreement) object;
        StudenteManager studenteManager = new StudenteManager(db,username,password);
        Studente studente = (Studente) studenteManager.doRetrieveById(learningAgreement.getStudente().getId());

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            String insertSQL = "UPDATE " + LearningAgreementManager.TAB_NAME + " " +
                    "SET tipologiaErasmus = ?, stato = ?, livello_conoscenza_lingua = ? " +
                    "WHERE studente = ? ;";


            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);

                // TAB LEARNING AGREEMENT
                preparedStatement.setString(1, learningAgreement.getTipologiaErasmus());
                preparedStatement.setString(2, learningAgreement.getStato());
                preparedStatement.setString(3, learningAgreement.getConoscenzaLingua()); //
                preparedStatement.setInt(4, studente.getId()); //RICORDARSI MATRICOLA

                //

                System.out.println(preparedStatement.toString());

                preparedStatement.executeUpdate();

                //  connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        DriverManagerConnectionPool.releaseConnection(connection);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


}