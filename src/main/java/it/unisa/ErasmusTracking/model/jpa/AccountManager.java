package main.java.it.unisa.erasmustracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.it.unisa.erasmustracking.bean.Account;
import main.java.it.unisa.erasmustracking.model.dao.IAccountDao;
import main.java.it.unisa.erasmustracking.util.DriverManagerConnectionPool;

public class AccountManager implements IAccountDao {
  private static final String TAB_NAME = "account"; //Nome tabella nel DB

  public String db;
  public String username;
  public String password;

  /**
   *Costruttore.
   */
  public AccountManager(String db, String username, String password) {
    this.db = db;
    this.username = username;
    this.password = password;
  }

  /**Genera query INSERT per salvare un nuovo elemento all'interno del DB.
   */
  public synchronized void doSave(Object object) {
    Account account = (Account) object;
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    String insertSql = "INSERT INTO " + AccountManager.TAB_NAME
        +
        "(nome, cognome, e_mail, password, ruolo) VALUES(?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, account.getNome());
      preparedStatement.setString(2, account.getCognome());
      preparedStatement.setString(3, account.getEmail());
      preparedStatement.setString(4, account.getPassword());
      preparedStatement.setString(5, account.getRuolo());




      preparedStatement.executeUpdate();

      //connection.commit();
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
  }

  /**
   *doDelete.
   */

  public synchronized boolean doDelete(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "DELETE FROM " + AccountManager.TAB_NAME + " WHERE id = ?";

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


  /**Genera query SELECT per ricevere i dati in base a quella determinata key.
   */
  public synchronized Account doRetrieveById(int id) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Account bean = new Account();

    String selectSql = "SELECT * FROM " + AccountManager.TAB_NAME + " WHERE id_account = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getInt("id_account"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setPassword(rs.getString("password"));
        bean.setRuolo(rs.getString("ruolo"));
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
    return bean;
  }

  /**
   *doRetriveAll.
   */

  public synchronized List<Account> doRetrieveAll() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Account> account = new ArrayList<Account>();

    String selectSql = "SELECT * FROM " + AccountManager.TAB_NAME;
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Account bean = new Account();

        bean.setId(rs.getInt("id_account"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setPassword(rs.getString("password"));
        bean.setRuolo(rs.getString("ruolo"));

        account.add(bean);
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
    return account;

  }

  /**
   *doRetriveByEmail.
   */

  public synchronized Account doRetrieveByEmail(String email) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Account bean = new Account();

    String selectSql = "SELECT "
            +
            "account.id_account, account.nome, "
            +
            "account.cognome, account.e_mail, "
            +
            "account.password, account.ruolo "
            +
            "FROM "
            +
            AccountManager.TAB_NAME
            +
            " WHERE account.e_mail = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, email);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getInt("id_account"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setPassword(rs.getString("password"));
        bean.setRuolo(rs.getString("ruolo"));
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
   *doUpdate.
   */

  public synchronized void doUpdate(Object object) {

    Account account = (Account) object;
    account = doRetrieveByEmail(account.getEmail());

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "UPDATE " + AccountManager.TAB_NAME + " "
        +
        "SET nome = ?, cognome = ?, password = ? "
        +
        "WHERE id_account = ? ;";


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);

      // TAB LEARNING AGREEMENT

      preparedStatement.setString(1, account.getNome());
      preparedStatement.setString(2, account.getCognome());
      preparedStatement.setString(3, account.getPassword()); //
      preparedStatement.setInt(4, account.getId());


      //


      preparedStatement.executeUpdate();

      //  connection.commit();
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

}
