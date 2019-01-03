package main.java.it.unisa.ErasmusTracking.model.jpa;
import main.java.it.unisa.ErasmusTracking.bean.Messaggio_Ticket;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;
import java.sql.*;
import java.util.Collection;
import java.util.List;
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
    public synchronized void doSave(Messaggio_Ticket messaggio_ticket) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO" + Messaggio_TicketManager.TAB_NAME + "VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB Messaggi

            preparedStatement.setInt(1, messaggio_ticket.getId_messaggio());
            preparedStatement.setString(2, messaggio_ticket.getContenuto());
            preparedStatement.setInt(3, messaggio_ticket.getTicket_id());
            preparedStatement.setInt(4, messaggio_ticket.getProprietario());
            preparedStatement.setDate(5, Date.valueOf(messaggio_ticket.getdatainvio()));


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
}
