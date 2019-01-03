package main.java.it.unisa.ErasmusTracking.model.jpa;
import main.java.it.unisa.ErasmusTracking.bean.MessaggioTicket;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;
import java.sql.*;

public class Messaggio_TicketManager {
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
    public synchronized void doSave(MessaggioTicket Messaggio_ticket) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO" + Messaggio_TicketManager.TAB_NAME + "VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB Messaggi

            preparedStatement.setInt(1, Messaggio_ticket.getId_messaggio());
            preparedStatement.setString(2, Messaggio_ticket.getContenuto());
            preparedStatement.setInt(3, Messaggio_ticket.getTicket_id());
            preparedStatement.setInt(4, Messaggio_ticket.getProprietario());
            preparedStatement.setDate(5, Date.valueOf(Messaggio_ticket.getdatainvio()));


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
    //Genera query SELECT per ricevere tutti i messaggi delll stesso ticket in base all'Id ticket
    public synchronized MessaggioTicket doRetrieveById(int id_ticket) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        MessaggioTicket bean = new MessaggioTicket();
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
}
