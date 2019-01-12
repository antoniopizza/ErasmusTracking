package main.java.it.unisa.ErasmusTracking.model.jpa;
import main.java.it.unisa.ErasmusTracking.bean.MessaggioTicket;
import main.java.it.unisa.ErasmusTracking.model.dao.IMessaggioDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Messaggio_TicketManager implements IMessaggioDao {
    private static final String TAB_NAME = "messaggio_ticket"; //Nome tabella nel DB
    public String db;
    public String username;
    public String password;

    public Messaggio_TicketManager(String db, String username, String password) {

        this.db = db;
        this.username = username;
        this.password = password;
    }
    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Object object){
        MessaggioTicket messaggio_ticket=(MessaggioTicket)object;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + Messaggio_TicketManager.TAB_NAME + " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB Messaggi

            preparedStatement.setInt(1, messaggio_ticket.getId_messaggio());
            preparedStatement.setString(2, messaggio_ticket.getContenuto());
            preparedStatement.setString(3, messaggio_ticket.getDataInvio());
            preparedStatement.setString(4, messaggio_ticket.getDataInvio());
            preparedStatement.setInt(5, messaggio_ticket.getTicket_id());
            preparedStatement.setInt(6, messaggio_ticket.getProprietario());


            System.out.println(preparedStatement.toString());

            preparedStatement.executeUpdate();

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
    }

    @Override
    public boolean doDelete(int id) {
        return false;
    }

    @Override
    public List<?> doRetrieveAll() {
        return null;
    }

    //Genera query SELECT per ricevere tutti i messaggi dell stesso ticket in base all'Id ticket
    public synchronized MessaggioTicket doRetrieveById(int id_messaggio) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        MessaggioTicket bean = new MessaggioTicket();
        String selectSQL = "SELECT * FROM " + Messaggio_TicketManager.TAB_NAME + " WHERE id_messaggio = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_messaggio);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId_messaggio(rs.getInt("id_messaggio"));
                bean.setContenuto(rs.getString("contenuto"));
                bean.setTicket_id(rs.getInt("ticket"));
                bean.setData_invio(rs.getString("data_invio"));
                bean.setOra_invio(rs.getString("ora_invio"));
                bean.setProprietario(rs.getInt("proprietario"));

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
        return bean;
    }

    @Override
    public synchronized List<MessaggioTicket> doRetrieveByIdAccount(int idAccount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<MessaggioTicket> messaggi = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + Messaggio_TicketManager.TAB_NAME + " WHERE proprietario = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idAccount);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MessaggioTicket bean = new MessaggioTicket();
                bean.setId_messaggio(rs.getInt("id_messaggio"));
                bean.setContenuto(rs.getString("contenuto"));
                bean.setTicket_id(rs.getInt("ticket"));
                bean.setData_invio(rs.getString("data_invio"));
                bean.setOra_invio(rs.getString("ora_invio"));
                bean.setProprietario(rs.getInt("proprietario"));

                messaggi.add(bean);
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
        return messaggi;
    }

    public synchronized List<MessaggioTicket> doRetrieveByIdTicket(int idTicket) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<MessaggioTicket> messaggi = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + Messaggio_TicketManager.TAB_NAME + " WHERE ticket = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idTicket);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MessaggioTicket bean = new MessaggioTicket();
                bean.setId_messaggio(rs.getInt("id_messaggio"));
                bean.setContenuto(rs.getString("contenuto"));
                bean.setTicket_id(rs.getInt("ticket"));
                bean.setData_invio(rs.getString("data_invio"));
                bean.setOra_invio(rs.getString("ora_invio"));
                bean.setProprietario(rs.getInt("proprietario"));

                messaggi.add(bean);
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
        return messaggi;
    }
}
