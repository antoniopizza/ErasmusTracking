package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.IMobilitaErasmusDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IReceivingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ISendingInstituteDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public synchronized void doSave(Object object){
        MobilitaErasmus mobilitaErasmus = (MobilitaErasmus) object;
        if(mobilitaErasmus.getDataInizio() == null && mobilitaErasmus.getDataFine() == null){
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;

            String insertSQL =  "INSERT INTO " + MobilitaErasmusManager.TAB_NAME + " (data_inizio," +
                    " data_fine, stato, receiving_institute, learning_agreement) " +
                    "VALUES (NULL , NULL , NULL , ?, ?, ?)";

            try {
                connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement1= connection1.prepareStatement(insertSQL);

                // TO REVIEW

                preparedStatement1.setInt(1, mobilitaErasmus.getSendingInstitute().getId());
                preparedStatement1.setInt(2, mobilitaErasmus.getReceivingInstitute().getId());
                preparedStatement1.setInt(3, mobilitaErasmus.getLearningAgreement());


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

            String insertSQL = "INSERT INTO " + MobilitaErasmusManager.TAB_NAME + " (data_inizio," +
                    " data_fine, stato, receiving_institute, learning_agreement, sending_institute)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";

            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);

                // TAB LEARNING AGREEMENT

                preparedStatement.setString(1, mobilitaErasmus.getDataInizio());
                preparedStatement.setString(2, mobilitaErasmus.getDataFine());
                preparedStatement.setString(3, mobilitaErasmus.getStato());

                // TO REVIEW

                preparedStatement.setInt(6, mobilitaErasmus.getSendingInstitute().getId());
                preparedStatement.setInt(4, mobilitaErasmus.getReceivingInstitute().getId());
                preparedStatement.setInt(5, mobilitaErasmus.getLearningAgreement());


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
    public List<MobilitaErasmus> doRetrieveAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<MobilitaErasmus> list = new ArrayList<>();
        String selectSQL =  "SELECT * FROM " + MobilitaErasmusManager.TAB_NAME;

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MobilitaErasmus mobilitaErasmus = new MobilitaErasmus();

                mobilitaErasmus.setId(rs.getInt("id_mobilita_erasmus"));
                mobilitaErasmus.setDataInizio(rs.getString("data_inizio"));
                mobilitaErasmus.setDataFine(rs.getString("data_fine"));
                mobilitaErasmus.setStato(rs.getString("stato"));

                // check sul db
                int idReceivingInstitute = rs.getInt("receiving_institute");
                int idSendingInstitute = rs.getInt("sending_institute");
                int learningAgreement = rs.getInt("learning_agreement");


                ISendingInstituteDao sendingInstituteManager = new SendingInstituteManager(this.db, this.username, this.password);
                SendingInstitute sendingInstitute = (SendingInstitute) sendingInstituteManager.doRetrieveById(idSendingInstitute);

                IReceivingInstituteDao receivingInstituteManager = new ReceivingInstituteManager(this.db, this.username, this.password);
                ReceivingInstitute receivingInstitute = (ReceivingInstitute) receivingInstituteManager.doRetrieveById(idReceivingInstitute);

                mobilitaErasmus.setSendingInstitute(sendingInstitute);
                mobilitaErasmus.setReceivingInstitute(receivingInstitute);

                mobilitaErasmus.setLearningAgreement(learningAgreement);
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
        return list;
    }

    @Override
    public Object doRetrieveById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        MobilitaErasmus mobilitaErasmus = new MobilitaErasmus();

        String selectSQL =  "SELECT * FROM " + MobilitaErasmusManager.TAB_NAME + " WHERE id_mobilita_erasmus = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                mobilitaErasmus.setId(rs.getInt("id_mobilita_erasmus"));
                mobilitaErasmus.setDataInizio(rs.getString("data_inizio"));
                mobilitaErasmus.setDataFine(rs.getString("data_fine"));
                mobilitaErasmus.setStato(rs.getString("stato"));

                // check sul db
                int idReceivingInstitute = rs.getInt("receiving_institute");
                int idSendingInstitute = rs.getInt("sending_institute");
                int learningAgreement = rs.getInt("learning_agreement");


                ISendingInstituteDao sendingInstituteManager = new SendingInstituteManager(this.db, this.username, this.password);
                SendingInstitute sendingInstitute = (SendingInstitute) sendingInstituteManager.doRetrieveById(rs.getInt("sending_institute"));

                IReceivingInstituteDao receivingInstituteManager = new ReceivingInstituteManager(this.db, this.username, this.password);
                ReceivingInstitute receivingInstitute = (ReceivingInstitute) receivingInstituteManager.doRetrieveById(rs.getInt("receiving_institute"));

                mobilitaErasmus.setSendingInstitute(sendingInstitute);
                mobilitaErasmus.setReceivingInstitute(receivingInstitute);

                mobilitaErasmus.setLearningAgreement(learningAgreement);
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

    public synchronized List<MobilitaErasmus> doRetrieveByIdSending(int idSending) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<MobilitaErasmus> list = new ArrayList<>();
        String selectSQL =  "SELECT id_mobilita_erasmus, data_inizio, data_fine, stato, sending_institute, receiving_institute, learning_agreement FROM " + MobilitaErasmusManager.TAB_NAME + " WHERE " + MobilitaErasmusManager.TAB_NAME + ".id_mobilita_erasmus = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idSending);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MobilitaErasmus mobilitaErasmus = new MobilitaErasmus();

                mobilitaErasmus.setId(rs.getInt("id_mobilita_erasmus"));
                mobilitaErasmus.setDataInizio(rs.getString("data_inizio"));
                mobilitaErasmus.setDataFine(rs.getString("data_fine"));
                mobilitaErasmus.setStato(rs.getString("stato"));

                // check sul db
                int idReceivingInstitute = rs.getInt("idReceivingInstitute");
                int idSendingInstitute = rs.getInt("idSendingInstitute");


                ISendingInstituteDao sendingInstituteManager = new SendingInstituteManager(this.db, this.username, this.password);
                SendingInstitute sendingInstitute = (SendingInstitute) sendingInstituteManager.doRetrieveById(idSendingInstitute);

                IReceivingInstituteDao receivingInstituteManager = new ReceivingInstituteManager(this.db, this.username, this.password);
                ReceivingInstitute receivingInstitute = (ReceivingInstitute) receivingInstituteManager.doRetrieveById(idReceivingInstitute);

                mobilitaErasmus.setSendingInstitute(sendingInstitute);
                mobilitaErasmus.setReceivingInstitute(receivingInstitute);

                mobilitaErasmus.setLearningAgreement(rs.getInt("learning_agreement"));
                list.add(mobilitaErasmus);
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
        return list;
    }

    public synchronized void doUpdate(Object object) {

        MobilitaErasmus mobilitaErasmus = (MobilitaErasmus) object;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "UPDATE " + MobilitaErasmusManager.TAB_NAME + " " +
                "SET data_inizio = ?, data_fine = ?, stato = ?," +
                " sending_institute = ?, receiving_institute = ?, learning_agreement=? " +
                "WHERE id_mapping_esame = ? ;";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB LEARNING AGREEMENT

            preparedStatement.setString(1, mobilitaErasmus.getDataInizio());
            preparedStatement.setString(2, mobilitaErasmus.getDataFine());
            preparedStatement.setString(3, mobilitaErasmus.getStato());
            preparedStatement.setInt(4, mobilitaErasmus.getSendingInstitute().getId());
            preparedStatement.setInt(5, mobilitaErasmus.getReceivingInstitute().getId());
            preparedStatement.setInt(6, mobilitaErasmus.getLearningAgreement());
            preparedStatement.setInt(4, mobilitaErasmus.getId());

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