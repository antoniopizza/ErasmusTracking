package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.model.dao.IAmministratoreDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

public class AmministratoriManager implements IAmministratoreDao {

  private static final String TAB_NAME = "amministratore"; //Nome tabella nel DB

  public String db;
  public String username;
  public String password;

  /**
   * AmministratoriManager.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   */
  public AmministratoriManager(String db, String username, String password) {
    this.db = db;
    this.username = username;
    this.password = password;
  }

  /**
   * doSave.
   *
   * @param object
   *
   */
  public synchronized void doSave(Object object) {
    Amministratore amministratore = (Amministratore) object;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + AmministratoriManager.TAB_NAME + " (account) VALUES( ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setInt(1,amministratore.getId());


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


  /**
   * doDelete.
   *
   * @param id
   *
   * @return
   *
   */
  public synchronized boolean doDelete(int id) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "DELETE FROM " + AmministratoriManager.TAB_NAME + " WHERE account = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db,username,password);
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1,id);

      result = preparedStatement.executeUpdate();
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

    return (result != 0);

  }

  /**
   * doRetriveById.
   *
   *
   * @param id
   *
   * @return
   *
   */
  public synchronized Amministratore doRetrieveById(int id) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Amministratore bean = new Amministratore();

    String selectSql = "SELECT * FROM " + AmministratoriManager.TAB_NAME + " WHERE account = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getInt("account"));

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

  /**
   * doretriveAll.
   *
   * @return
   *
   */
  public synchronized List<Amministratore> doRetrieveAll() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Amministratore> amministratore = new ArrayList<Amministratore>();

    String selectSql = "SELECT * FROM " + AmministratoriManager.TAB_NAME;
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Amministratore bean = new Amministratore();
        bean.setId(rs.getInt("account"));
        amministratore.add(bean);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }  finally {
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
    return amministratore;

  }

  /**
   * doRetriveByemail.
   *
   * @param email
   *
   * @return
   *
   */
  public synchronized Amministratore doRetrieveByEmail(String email) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Amministratore amministratore = new Amministratore();

    String selectSql = "SELECT account.nome, account.cognome, "
        +
        "account.e_mail, account.password, amministratore.account "
        +
        "FROM amministratore, account WHERE account.e_mail = ? AND "
        +
        "account.id = amministratore.account";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, email);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        amministratore.setNome(rs.getString("account.nome"));
        amministratore.setCognome(rs.getString("account.cognome"));
        amministratore.setEmail(rs.getString("account.e_mail"));
        amministratore.setPassword(rs.getString("account.password"));
        amministratore.setId(rs.getInt("amministratore.account"));
        amministratore.setRuolo("amministratore");
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
    return amministratore;

  }

  /**
   * doUpdate.
   *
   * @param object
   *
   */
  public synchronized void doUpdate(Object object) {

    Amministratore amministratore = (Amministratore) object;
    amministratore = doRetrieveByEmail(amministratore.getEmail());

    Account account = new Account();
    AccountManager manageracc = new AccountManager(db, username, password);
    account = manageracc.doRetrieveByEmail(amministratore.getEmail());
    account.setNome(amministratore.getNome());
    account.setCognome(amministratore.getCognome());
    account.setEmail(amministratore.getEmail());
    account.setPassword(amministratore.getPassword());

    manageracc.doUpdate(account);
  }

}
