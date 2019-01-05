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
        Studente studente = (Studente) studenteManager.doRetrieveByIdStudente(learningAgreement.getStudente().getId());
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

                MappingEsame mappingEsame1 = new MappingEsame();
                MobilitaErasmus mobilitaErasmus1 = new MobilitaErasmus();

                SendingInstitute sendingInstitute1 = new SendingInstitute();
                ReceivingInstitute receivingInstitute1 = new ReceivingInstitute();

                IMappingEsameDao mappingEsameDao1 = new MappingEsameManager(db, username, password);
                IMobilitaErasmusDao mobilitaErasmusDao1 = new MobilitaErasmusManager(db, username, password);
                ISendingInstituteDao sendingInstituteDao1 = new SendingInstituteManager(db, username, password);
                IReceivingInstituteDao receivingInstituteDao1 = new ReceivingInstituteManager(db, username, password);

                mappingEsame1.setLearningAgreement(learningAgreement.getId());
                mobilitaErasmus1.setReceivingInstitute(receivingInstitute1);
                mobilitaErasmus1.setSendingInstitute(sendingInstitute1);
                mobilitaErasmus1.setLearningAgreement(learningAgreement.getId());

                mappingEsameDao1.doSave(mappingEsame1);
                sendingInstituteDao1.doSave(sendingInstitute1);
                receivingInstituteDao1.doSave(receivingInstitute1);
                mobilitaErasmusDao1.doSave(mobilitaErasmus1);

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

                MappingEsame mappingEsame = new MappingEsame();
                MobilitaErasmus mobilitaErasmus = new MobilitaErasmus();

                SendingInstitute sendingInstitute = new SendingInstitute();
                ReceivingInstitute receivingInstitute = new ReceivingInstitute();

                IMappingEsameDao mappingEsameDao = new MappingEsameManager(db, username, password);
                IMobilitaErasmusDao mobilitaErasmusDao = new MobilitaErasmusManager(db, username, password);
                ISendingInstituteDao sendingInstituteDao = new SendingInstituteManager(db, username, password);
                IReceivingInstituteDao receivingInstituteDao = new ReceivingInstituteManager(db, username, password);

                mappingEsame.setLearningAgreement(learningAgreement.getId());
                mobilitaErasmus.setReceivingInstitute(receivingInstitute);
                mobilitaErasmus.setSendingInstitute(sendingInstitute);
                mobilitaErasmus.setLearningAgreement(learningAgreement.getId());

                mappingEsameDao.doSave(mappingEsame);
                sendingInstituteDao.doSave(sendingInstitute);
                receivingInstituteDao.doSave(receivingInstitute);
                mobilitaErasmusDao.doSave(mobilitaErasmus);

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

    public synchronized LearningAgreement doRetrieveByStudente(String matricola){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        LearningAgreement learningAgreement = new LearningAgreement();

        String selectSQL =  "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM " + LearningAgreementManager.TAB_NAME + ", studente WHERE " + LearningAgreementManager.TAB_NAME + ".studente = ?";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, matricola);

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
}