package main.java.it.unisa.ErasmusTracking.model.jpa;


import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.ITicketDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public class TicketManager implements ITicketDao {

    private static final String TAB_NAME = "ticket"; //Nome tabella nel DB
    public String db;
    public String username;
    public String password;

    public TicketManager(String db, String username, String password) {

        this.db = db;
        this.username = username;
        this.password = password;
    }

    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Ticket ticket) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO" + TicketManager.TAB_NAME + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB TICKET

            preparedStatement.setInt(1, ticket.getId());
            preparedStatement.setString(2, ticket.getObject());
            preparedStatement.setBoolean(3, ticket.getStato());
            preparedStatement.setInt(4, ticket.getMittente());
            preparedStatement.setInt(5, ticket.getDestinatario());
            preparedStatement.setString(6, ticket.getDataCreazione());
            preparedStatement.setDate(7, Date.valueOf(ticket.getData_invio()));
            preparedStatement.setBoolean(8, ticket.setStato(true));
            //

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

    @Override
    public void doSave(Object object) {

    }

    public synchronized boolean doDelete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + TicketManager.TAB_NAME + " WHERE id = ?";

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

    public List<?> doRetrieveAll() {
        return null;
    }


    //Genera query SELECT per ricevere i dati in base a quella determinata key
    public synchronized Ticket doRetrieveById(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Ticket bean = new Ticket();
        String selectSQL = "SELECT * FROM " + TicketManager.TAB_NAME + " WHERE id = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("mittente"));
                bean.setDestinatario(rs.getInt("destinatario"));
                bean.setDatacreazione(rs.getString("data_creazione"));
                bean.setObject(rs.getString("data_creazione"));
                bean.setData_invio(rs.getDate("data_invio"));

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

    //Chiude un ticket cambiandone lo stato
    public synchronized void doClose(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Ticket bean = doRetrieveById(id);
        doDelete(id);
        bean.setStato(false);
        try {
            doSave(bean);


        } catch (SQLException e) {
            e.printStackTrace();

            }
        }


    public Collection<Ticket> doRetrieveByIdAccount(int idAccount) {
        return null;
    }

    public Collection<Ticket> doRetrieveByUsernameStudent(String username) {
        return null;
    }
}



