package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.IMappingEsameDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class MappingEsameManager implements IMappingEsameDao {

    private static final String TAB_NAME = "mappingEsame"; //Nome tabella nel DB


    public String db;
    public String username;
    public String password;

    public MappingEsameManager(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }

    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Object object) {

        MappingEsame mappingEsame = (MappingEsame) object;
        if(mappingEsame.getLingua()==null &&mappingEsame.getStato() == null){
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;

            LearningAgreement learningAgreement = new LearningAgreement();
            LearningAgreementManager lamanager = new LearningAgreementManager(db,username,password);
            learningAgreement = lamanager.doRetrieveById(mappingEsame.getLearningAgreement());


            String insertSQL = "INSERT INTO " + MappingEsameManager.TAB_NAME + " (esame_interno, codice_esame_interno, ects_esame_interno, esame_esterno," +
                    " codice_esame_esterno, etc_esame_esterno, lingua, stato, learning_agreement) VALUES (NULL , NULL , NULL, NULL, NULL, NULL, NULL, NULL , ?)";

            try {
                connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement1 = connection1.prepareStatement(insertSQL);

                // TAB MAPPING ESAMI

                preparedStatement1.setInt(1, mappingEsame.getLearningAgreement());


                //

                System.out.println(preparedStatement1.toString());

                preparedStatement1.executeUpdate();

               // connection1.commit();
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

        }
        else {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            String insertSQL = "INSERT INTO " + MappingEsameManager.TAB_NAME + " (esame_interno, codice_esame, ects_esame_interno, esame_esterno," +
                    " codice_esame_esterno, etc_esame_esterno, lingua, stato, learning_agreement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);

                // TAB MAPPING ESAMI


                preparedStatement.setString(1, mappingEsame.getEsameInterno().getNome());
                preparedStatement.setString(2, mappingEsame.getEsameInterno().getCodice());
                preparedStatement.setInt(3, mappingEsame.getEsameInterno().getECTS());
                preparedStatement.setString(4, mappingEsame.getEsameEsterno().getNome());
                preparedStatement.setString(5, mappingEsame.getEsameEsterno().getCodice());
                preparedStatement.setInt(6, mappingEsame.getEsameEsterno().getECTS());
                preparedStatement.setString(7, mappingEsame.getLingua());
                preparedStatement.setString(8, mappingEsame.getStato());
                preparedStatement.setInt(9, mappingEsame.getLearningAgreement());

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

        String deleteSQL = "DELETE FROM " + MappingEsameManager.TAB_NAME + " WHERE id_mapping_erasmus = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (result != 0);
    }

    public synchronized MappingEsame doRetrieveById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        MappingEsame mappingEsame = new MappingEsame();

        String selectSQL =  "SELECT * FROM " + MappingEsameManager.TAB_NAME + " WHERE " + MappingEsameManager.TAB_NAME + ".id_mapping_esame = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                mappingEsame.setId(rs.getInt("id_mapping_esame"));
                mappingEsame.getEsameInterno().setNome(rs.getString("esame_interno"));
                mappingEsame.getEsameInterno().setCodice(rs.getString("codice_esame_interno"));
                mappingEsame.getEsameInterno().setECTS(rs.getInt("ects_esame_interno"));
                mappingEsame.getEsameEsterno().setNome(rs.getString("esame_esterno"));
                mappingEsame.getEsameEsterno().setCodice(rs.getString("codice_esame_esterno"));
                mappingEsame.getEsameEsterno().setECTS(rs.getInt("ects_esame_esterno"));
                mappingEsame.setLingua(rs.getString("lingua"));
                mappingEsame.setStato(rs.getString("stato"));
                mappingEsame.setLearningAgreement(rs.getInt("learning_agreement"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mappingEsame;
    }

    public synchronized MappingEsame doRetrieveByLearningAgreement(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        MappingEsame mappingEsame = new MappingEsame();

        String selectSQL =  "SELECT * FROM " + MappingEsameManager.TAB_NAME + ", learningAgreement WHERE " + MappingEsameManager.TAB_NAME + ".learning_agreement = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                mappingEsame.setId(rs.getInt("id_mapping_esame"));
                mappingEsame.getEsameInterno().setNome(rs.getString("esame_interno"));
                mappingEsame.getEsameInterno().setCodice(rs.getString("codice_esame_interno"));
                mappingEsame.getEsameInterno().setECTS(rs.getInt("ects_esame_interno"));
                mappingEsame.getEsameEsterno().setNome(rs.getString("esame_esterno"));
                mappingEsame.getEsameEsterno().setCodice(rs.getString("codice_esame_esterno"));
                mappingEsame.getEsameEsterno().setECTS(rs.getInt("ects_esame_esterno"));
                mappingEsame.setLingua(rs.getString("lingua"));
                mappingEsame.setStato(rs.getString("stato"));
                mappingEsame.setLearningAgreement(rs.getInt("learning_agreement"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mappingEsame;
    }

    public synchronized List<MappingEsame> doRetrieveAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<MappingEsame> mappingEsami = new ArrayList<MappingEsame>();

        String selectSQL = "SELECT * FROM " + MappingEsameManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MappingEsame bean = new MappingEsame();
                bean.setId(rs.getInt("id_mapping_esame"));
                bean.getEsameInterno().setNome(rs.getString("esame_interno"));
                bean.getEsameInterno().setCodice(rs.getString("codice_esame_interno"));
                bean.getEsameInterno().setECTS(rs.getInt("ects_esame_interno"));
                bean.getEsameEsterno().setNome(rs.getString("esame_esterno"));
                bean.getEsameEsterno().setCodice(rs.getString("codice_esame_esterno"));
                bean.getEsameEsterno().setECTS(rs.getInt("ects_esame_esterno"));
                bean.setLingua(rs.getString("lingua"));
                bean.setStato(rs.getString("stato"));
                bean.setLearningAgreement(rs.getInt("learning_agreement"));

                mappingEsami.add(bean);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mappingEsami;

    }
}