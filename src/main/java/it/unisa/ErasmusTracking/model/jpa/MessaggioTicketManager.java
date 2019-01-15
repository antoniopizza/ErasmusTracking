package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.MessaggioTicket;
import main.java.it.unisa.ErasmusTracking.model.dao.IMessaggioDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;



public class MessaggioTicketManager implements IMessaggioDao {
  private static final String TAB_NAME = "messaggio_ticket"; //Nome tabella nel DB
  public String db;
  public String username;
  public String password;

  /**
   * MessaggioTicketManager.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   */
  public MessaggioTicketManager(String db, String username, String password) {

    this.db = db;
    this.username = username;
    this.password = password;
  }

  /**
   * Genera query INSERT per salvare un nuovo elemento all'interno del DB.
   *
   * @param object
   *
   */
  public synchronized void doSave(Object object) {
    MessaggioTicket messaggioTicket = (MessaggioTicket)object;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql =
        "INSERT INTO "
            +
            MessaggioTicketManager.TAB_NAME
            +
            " VALUES (?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);

      // TAB Messaggi

      preparedStatement.setInt(1, messaggioTicket.getIdMessaggio());
      preparedStatement.setString(2, messaggioTicket.getContenuto());
      preparedStatement.setString(3, messaggioTicket.getDataInvio());
      preparedStatement.setString(4, messaggioTicket.getDataInvio());
      preparedStatement.setInt(5, messaggioTicket.getTicketId());
      preparedStatement.setInt(6, messaggioTicket.getProprietario());


      System.out.println(preparedStatement.toString());

      preparedStatement.executeUpdate();

      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
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

  @Override
  public boolean doDelete(int id) {
    return false;
  }

  @Override
  public List<?> doRetrieveAll() {
    return null;
  }

  /**
   * Genera query SELECT per ricevere tutti i messaggi dell stesso ticket in base all'Id ticket.
   *
   * @param id_messaggio
   *
   * @return
   *
   */
  public synchronized MessaggioTicket doRetrieveById(int idMessaggio) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    MessaggioTicket bean = new MessaggioTicket();
    String selectSql =
        "SELECT * FROM "
            +
            MessaggioTicketManager.TAB_NAME
            +
            " WHERE id_messaggio = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, idMessaggio);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setIdMessaggio(rs.getInt("id_messaggio"));
        bean.setContenuto(rs.getString("contenuto"));
        bean.setTicketId(rs.getInt("ticket"));
        bean.setDataInvio(rs.getString("data_invio"));
        bean.setOraInvio(rs.getString("ora_invio"));
        bean.setProprietario(rs.getInt("proprietario"));

      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
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

    String selectSql =
        "SELECT * FROM "
            +
            MessaggioTicketManager.TAB_NAME
            +
            " WHERE proprietario = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, idAccount);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        MessaggioTicket bean = new MessaggioTicket();
        bean.setIdMessaggio(rs.getInt("id_messaggio"));
        bean.setContenuto(rs.getString("contenuto"));
        bean.setTicketId(rs.getInt("ticket"));
        bean.setDataInvio(rs.getString("data_invio"));
        bean.setOraInvio(rs.getString("ora_invio"));
        bean.setProprietario(rs.getInt("proprietario"));

        messaggi.add(bean);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
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

  /**
   * doRetiveByIdTiket.
   *
   * @param idTicket
   *
   * @return
   *
   */
  public synchronized List<MessaggioTicket> doRetrieveByIdTicket(int idTicket) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<MessaggioTicket> messaggi = new ArrayList<>();

    String selectSql =
        "SELECT * FROM "
            +
            MessaggioTicketManager.TAB_NAME
            +
            " WHERE ticket = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, idTicket);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        MessaggioTicket bean = new MessaggioTicket();
        bean.setIdMessaggio(rs.getInt("id_messaggio"));
        bean.setContenuto(rs.getString("contenuto"));
        bean.setTicketId(rs.getInt("ticket"));
        bean.setDataInvio(rs.getString("data_invio"));
        bean.setOraInvio(rs.getString("ora_invio"));
        bean.setProprietario(rs.getInt("proprietario"));

        messaggi.add(bean);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
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
