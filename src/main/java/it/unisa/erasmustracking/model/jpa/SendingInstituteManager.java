package main.java.it.unisa.erasmustracking.model.jpa;

import main.java.it.unisa.erasmustracking.bean.*;
import main.java.it.unisa.erasmustracking.model.dao.ISendingInstituteDao;
import main.java.it.unisa.erasmustracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SendingInstituteManager implements ISendingInstituteDao {

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
    public synchronized void doSave(Object object){

        SendingInstitute sendingInstitute = (SendingInstitute) object;

        if(sendingInstitute.getDipartimento()==null && sendingInstitute.getIndirizzo() == null && sendingInstitute.getCodiceErasmus() == null){
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;

            String insertSQL =  "INSERT INTO " + SendingInstituteManager.TAB_NAME + " (codice_erasmus, dipartimento, indirizzo) VALUES (NULL , NULL, NULL)";

            try {
                connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement1 = connection1.prepareStatement(insertSQL);

                System.out.println(preparedStatement1.toString());

                preparedStatement1.executeUpdate();

                //connection1.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }  finally {
                try {
                    if (preparedStatement1 != null)
                        preparedStatement1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }  finally {
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

            String insertSQL = "INSERT INTO " + SendingInstituteManager.TAB_NAME + " (codice_erasmus, dipartimento, indirizzo) VALUES (?, ?, ?)";

            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);

                // TAB LEARNING AGREEMENT

                preparedStatement.setString(1, sendingInstitute.getCodiceErasmus());
                preparedStatement.setString(2, sendingInstitute.getDipartimento());
                preparedStatement.setString(3, sendingInstitute.getIndirizzo());

                //

                System.out.println(preparedStatement.toString());

                preparedStatement.executeUpdate();

                //connection.commit();
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

        String deleteSQL = "DELETE FROM " + SendingInstituteManager.TAB_NAME + " WHERE id_sending_institute = ?";

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

    public synchronized SendingInstitute doRetrieveById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        SendingInstitute sendingInstitute = new SendingInstitute();

        String selectSQL =  "SELECT id_sending_institute, codice_erasmus, dipartimento, indirizzo  FROM " + SendingInstituteManager.TAB_NAME + "WHERE " + SendingInstituteManager.TAB_NAME + ".id_sending_institute = ?";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                sendingInstitute.setId(rs.getInt("id_sending_institute"));
                sendingInstitute.setCodiceErasmus(rs.getString("codice_erasmus"));
                sendingInstitute.setDipartimento(rs.getString("dipartimento"));
                sendingInstitute.setIndirizzo(rs.getString("indirizzo"));
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
        return sendingInstitute;
    }
}