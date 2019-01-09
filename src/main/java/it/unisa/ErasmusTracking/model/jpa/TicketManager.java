package main.java.it.unisa.ErasmusTracking.model.jpa;


import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import main.java.it.unisa.ErasmusTracking.bean.Messaggio_Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.ITicketDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.*;
import java.util.ArrayList;
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
    @Override
    public void doSave(Object object) {
        Ticket ticket = (Ticket) object;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TicketManager.TAB_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setBoolean(8, ticket.setStato(true));
            //


            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
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

    public synchronized List<Ticket> doRetrieveAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Ticket> ticketList = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TicketManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ticket bean = new Ticket();
                bean.setId(rs.getInt("mittente"));
                bean.setDestinatario(rs.getInt("destinatario"));
                bean.setDatacreazione(rs.getString("data_creazione"));
                bean.setObject(rs.getString("data_creazione"));

                ticketList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
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

        return ticketList;
    }


    //Genera query SELECT per ricevere i dati in base a quella determinata key
    public synchronized Ticket doRetrieveById(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Ticket bean = new Ticket();
        String selectSQL = "SELECT * FROM " + TicketManager.TAB_NAME + " WHERE id_ticket = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setMittente(rs.getInt("mittente"));
                bean.setDestinatario(rs.getInt("destinatario"));
                bean.setDatacreazione(rs.getString("data_creazione"));
                bean.setObject(rs.getString("oggetto"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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

        doSave(bean);

        }


    public List<Ticket> doRetrieveByIdCoordinatore(int destinatario) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Ticket> ticketList = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TicketManager.TAB_NAME + " WHERE destinatario = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, destinatario);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ticket bean = new Ticket();
                bean.setId(rs.getInt("mittente"));
                bean.setDestinatario(rs.getInt("destinatario"));
                bean.setDatacreazione(rs.getString("data_creazione"));
                bean.setObject(rs.getString("oggetto"));

                System.out.println("tostring ticket: " + bean.toString());
                ticketList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
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

        return ticketList;
    }

    public List<Ticket> doRetrieveByIdStudente(int mittente) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Ticket> ticketList = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TicketManager.TAB_NAME + " WHERE mittente = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, mittente);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ticket bean = new Ticket();
                bean.setId(rs.getInt("mittente"));
                bean.setDestinatario(rs.getInt("destinatario"));
                bean.setDatacreazione(rs.getString("data_creazione"));
                bean.setObject(rs.getString("oggetto"));

                System.out.println("tostring ticket: " + bean.toString());
                ticketList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
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

        return ticketList;
    }

    public List<Ticket> doRetrieveByUsernameStudent(String username) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Ticket> ticketList = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TicketManager.TAB_NAME + "WHERE account = account.id_account AND account.e_mail = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ticket bean = new Ticket();
                bean.setId(rs.getInt("mittente"));
                bean.setDestinatario(rs.getInt("destinatario"));
                bean.setDatacreazione(rs.getString("data_creazione"));
                bean.setObject(rs.getString("data_creazione"));

                ticketList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
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

        return ticketList;
    }
}



