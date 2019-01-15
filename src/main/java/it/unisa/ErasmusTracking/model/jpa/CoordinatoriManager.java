package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.model.dao.ICoordinatoreDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

public class CoordinatoriManager implements ICoordinatoreDao {
  private static final String TAB_NAME = "coordinatore"; //Nome tabella nel DB

  public String db;
  public String username;
  public String password;

  /**
   * CooidinatoriManager.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   */

  public CoordinatoriManager(String db, String username, String password) {
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
    Coordinatore coordinatore = (Coordinatore) object;

    Account bean = new Account();
    bean.setNome(coordinatore.getNome());
    bean.setCognome(coordinatore.getCognome());
    bean.setPassword(coordinatore.getPassword());
    bean.setEmail(coordinatore.getEmail());
    bean.setRuolo(coordinatore.getRuolo());
    AccountManager account = new AccountManager(db,username,password);
    account.doSave(bean);

    bean = account.doRetrieveByEmail(coordinatore.getEmail());

    if (coordinatore.getSendingInstitute() == 0) {
      Connection connection1 = null;
      PreparedStatement preparedStatement1 = null;

      String insertSql = "INSERT INTO "
          +
          CoordinatoriManager.TAB_NAME
          +
          " (sending_in" +
          "stitute, account) VALUES(NULL , ?)";

      try {
        connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
        preparedStatement1 = connection1.prepareStatement(insertSql);
        preparedStatement1.setInt(1, bean.getId());
        //preparedStatement.setInt(2,coordinatore.getSendingInstitute());

        System.out.println(preparedStatement1.toString());

        preparedStatement1.executeUpdate();

        //connection1.commit();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          if (preparedStatement1 != null) {
            preparedStatement1.close();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          try {
            DriverManagerConnectionPool.releaseConnection(connection1);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    } else {
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      String insertSql = "INSERT INTO "
          +
          CoordinatoriManager.TAB_NAME
          +
          " (sending_institute, account) VALUES(? , ?)";

      try {
        connection = DriverManagerConnectionPool.getConnection(db, username, password);
        preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setInt(1, coordinatore.getSendingInstitute());
        preparedStatement.setInt(2,bean.getId());

        System.out.println(preparedStatement.toString());

        preparedStatement.executeUpdate();

        //connection.commit();
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

    String deleteSql = "DELETE FROM "
        +
        CoordinatoriManager.TAB_NAME
        +
        " WHERE account = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db,username,password);
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1,id);

      result = preparedStatement.executeUpdate();


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
   * doRetiveById.
   *
   * @param id
   *
   * @return
   *
   */
  public synchronized Coordinatore doRetrieveById(int id) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Coordinatore bean = new Coordinatore();
    AccountManager accountManager = new AccountManager(db,username,password);
    Account account = accountManager.doRetrieveById(id);

    String selectSql = "SELECT * FROM "
        +
        CoordinatoriManager.TAB_NAME
        +
        " WHERE account = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {

        bean.setSendingInstitute(rs.getInt("sending_institute"));
        bean.setId(rs.getInt("account"));
        bean.setNome(account.getNome());
        bean.setCognome(account.getCognome());
        bean.setEmail(account.getEmail());
        bean.setPassword(account.getPassword());
        bean.setRuolo("coordinatore");
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
   * doRetriveall.
   *
   * @return
   *
   */
  public synchronized List<Coordinatore> doRetrieveAll() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Coordinatore> coordinatori = new ArrayList<Coordinatore>();

    String selectSql = "SELECT * FROM " + CoordinatoriManager.TAB_NAME;
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Coordinatore bean = new Coordinatore();

        bean.setId(rs.getInt("account"));
        bean.setSendingInstitute(rs.getInt("sending_institute"));

        coordinatori.add(bean);
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
    return coordinatori;

  }

  /**
   * doRetriveByemail.
   *
   * @param email
   *
   * @return
   *
   */
  public synchronized Coordinatore doRetrieveByEmail(String email) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Coordinatore bean = new Coordinatore();

    String selectSql = "SELECT account.nome, account.cognome, account.e_mail, "
        +
        "account.password, coordinatore.sending_institute, coordinatore.account FROM "
        +
        CoordinatoriManager.TAB_NAME
        +
        ", account WHERE account.e_mail = ? AND account.id_account = coordinatore.account";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, email);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setNome(rs.getString("account.nome"));
        bean.setCognome(rs.getString("account.cognome"));
        bean.setEmail(rs.getString("account.e_mail"));
        bean.setPassword(rs.getString("account.password"));
        bean.setRuolo("coordinatore");
        bean.setSendingInstitute(rs.getInt("coordinatore.sending_institute"));
        bean.setId(rs.getInt("coordinatore.account"));

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
   * doUpdate.
   *
   * @param object
   *
   */
  public synchronized void doUpdate(Object object) {
    Coordinatore oldCoordinatore = new Coordinatore();
    Coordinatore coordinatore = (Coordinatore) object;
    oldCoordinatore = doRetrieveByEmail(coordinatore.getEmail());

    Account account = new Account();
    AccountManager manageracc = new AccountManager(db, username, password);
    account = manageracc.doRetrieveByEmail(oldCoordinatore.getEmail());
    account.setNome(coordinatore.getNome());
    account.setCognome(coordinatore.getCognome());
    account.setEmail(oldCoordinatore.getEmail());
    account.setPassword(oldCoordinatore.getPassword());

    manageracc.doUpdate(account);

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "UPDATE " + CoordinatoriManager.TAB_NAME + " "
        +
        "SET sending_institute = ? "
        +
        "WHERE account = ? ;";


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);

      // TAB LEARNING AGREEMENT

      preparedStatement.setInt(1, oldCoordinatore.getSendingInstitute());
      preparedStatement.setInt(2, account.getId());

      //

      System.out.println(preparedStatement.toString());

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
