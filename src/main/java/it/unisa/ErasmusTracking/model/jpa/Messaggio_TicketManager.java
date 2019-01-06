package main.java.it.unisa.ErasmusTracking.model.jpa;
import main.java.it.unisa.ErasmusTracking.bean.Messaggio_Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.IMessaggioDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;
import java.sql.*;
import java.util.Collection;
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
        Messaggio_Ticket messaggio_ticket=(Messaggio_Ticket)object;
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
    public synchronized Messaggio_Ticket doRetrieveById(int id_ticket) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Messaggio_Ticket bean = new Messaggio_Ticket();
        String selectSQL = "SELECT * FROM " + Messaggio_TicketManager.TAB_NAME + " WHERE id = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_ticket);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId_messaggio(rs.getInt("id"));
                bean.setContenuto(rs.getString("contenuto"));
                bean.setTicket_id(rs.getInt("Id Ticket"));
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
    public Collection<Messaggio_Ticket> doRetrieveByIdAccount(int idAccount) {
        return null;
    }
}
