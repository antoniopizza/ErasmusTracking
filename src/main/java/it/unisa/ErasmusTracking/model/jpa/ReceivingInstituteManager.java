package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.IReceivingInstituteDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class ReceivingInstituteManager implements IReceivingInstituteDao {

    private static final String TAB_NAME = "receivingInstitute"; //Nome tabella nel DB

    private static final String VISUALIZZA_TUTTI = "SELECT * FROM location";

    public String db;
    public String username;
    public String password;

    public ReceivingInstituteManager(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Object object){

        ReceivingInstitute receivingInstitute = (ReceivingInstitute) object;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL =  "INSERT INTO " + ReceivingInstituteManager.TAB_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

        String deleteSQL = "DELETE FROM " + ReceivingInstituteManager.TAB_NAME + " WHERE id_receiving_institute = ?";

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

    public synchronized ReceivingInstitute doRetrieveById(SendingInstitute sendingInstitute){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ReceivingInstitute receivingInstitute = new ReceivingInstitute();

        String selectSQL =  "SELECT id_receiving_institute, codice_erasmus, nome_contatto, e_mail_contatto, size_of_enterprise, nome_mentore, e_mail_mentore, website, location  FROM " + ReceivingInstituteManager.TAB_NAME + "WHERE " + ReceivingInstituteManager.TAB_NAME + ".id_receiving_institute = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, sendingInstitute.getId());

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

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }  catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return receivingInstitute;
    }


    public synchronized List<ReceivingInstitute> doRetrieveAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<ReceivingInstitute> receivingInstituteCollection = new LinkedList<ReceivingInstitute>();

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(VISUALIZZA_TUTTI);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ReceivingInstitute bean = new ReceivingInstitute();
                bean.setId(rs.getInt("id_receiving_institute"));
                bean.setCodiceErasmus(rs.getString("codice_erasmus"));
                bean.setNomeContatto(rs.getString("nome_contatto"));
                bean.setEmailContatto(rs.getString("e_mail_contatto"));
                bean.setSizeOfEnterprise(rs.getString("size_of_enterprise"));
                bean.setNomeMentore(rs.getString("nome_mentore"));
                bean.setEmailMentore(rs.getString("e_mail_mentore"));
                bean.setWebsite(rs.getString("website"));
                bean.setLocalita(rs.getInt("localita"));

                receivingInstituteCollection.add(bean);
            }
        }  catch (SQLException e) {
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

        return receivingInstituteCollection;
    }

    @Override
    public Object doRetrieveById(int id) {
        return null;
    }
}