package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;




public class DocumentiManager implements IDocumentoDao {

  private static final String TAB_NAME = "documenti"; //Nome tabella nel DB
  public String db;
  public String username;
  public String password;

  /**
   * DocumentiManager.
   *
   * @param db
   * @param username
   * @param password
   */

  public DocumentiManager(String db, String username, String password) {

    this.db = db;
    this.username = username;
    this.password = password;
  }

  /**
   * doSave.
   *
   * @param object
   */
  public synchronized void doSave(Object object) {
    Documenti documento = (Documenti) object;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO "
        +
        DocumentiManager.TAB_NAME
        +
        "(nome, data_caricamento, url, proprietario) VALUES (?, ?, ?, ?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, documento.getNome());
      preparedStatement.setString(2, documento.getDataCaricamento());
      preparedStatement.setBinaryStream(3, documento.getInputStream(), documento.getFileSize());
      preparedStatement.setInt(4, documento.getProprietario());

      System.out.println(preparedStatement.toString());

      preparedStatement.executeUpdate();

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
   * @return
   */

  public synchronized boolean doDelete(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "DELETE FROM " + DocumentiManager.TAB_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, id);

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
   * doRetiveById.
   *
   * @param id
   * @return
   */

  public synchronized Documenti doRetrieveById(int id) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Documenti bean = new Documenti();

    String selectSql = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE id_documento = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getInt("id_documento"));
        bean.setNome(rs.getString("nome"));
        bean.setDataCaricamento(rs.getString("data_caricamento"));
        //bean.setUrl(rs.getString("url"));
        bean.setProprietario(rs.getInt("proprietario"));
        bean.setInputStream(rs.getBinaryStream("url"));
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
   * doRetiveAll.
   *
   * @return
   */

  public synchronized List<Documenti> doRetrieveAll() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Documenti> documenti = new ArrayList<Documenti>();

    String selectSql = "SELECT * FROM " + DocumentiManager.TAB_NAME;
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Documenti bean = new Documenti();
        bean.setId(rs.getInt("id_documento"));
        bean.setNome(rs.getString("nome"));
        bean.setDataCaricamento(rs.getString("data_caricamento"));
        bean.setUrl(rs.getString("url"));
        bean.setProprietario(rs.getInt("proprietario"));

        documenti.add(bean);
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
    return documenti;

  }

  /**
   * doRetiveByAccount.
   *
   * @param idAccount
   * @return
   */

  public synchronized List<Documenti> doRetrieveByIdAccount(int idAccount) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Documenti> documenti = new ArrayList<Documenti>();

    String selectSql = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE proprietario = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, idAccount);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Documenti bean = new Documenti();
        bean.setId(rs.getInt("id_documento"));
        bean.setNome(rs.getString("nome"));
        bean.setDataCaricamento(rs.getString("data_caricamento"));
        bean.setUrl(rs.getString("url"));
        bean.setProprietario(rs.getInt("proprietario"));

        documenti.add(bean);
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
    return documenti;

  }

  /**
   * doRetiveByUsernameStuddent.
   *
   * @param username
   * @return
   */

  public synchronized List<Documenti> doRetrieveByUsernameStudent(String username) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Documenti> documenti = new ArrayList<Documenti>();

    String selectSql = "SELECT documenti.id_documento, documenti.nome,"
        +
        " documenti.data_caricamento, documenti.url, documenti.proprietario"
        +
        " FROM " + DocumentiManager.TAB_NAME
        +
        ", studente, account "
        +
        "WHERE studente.username = ? "
        +
        "AND sudente.account = account.id "
        +
        "AND account.id = proprietario";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, username);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Documenti bean = new Documenti();
        bean.setId(rs.getInt("id"));
        bean.setNome(rs.getString("nome"));
        bean.setDataCaricamento(rs.getString("data_caricamento"));
        bean.setUrl(rs.getString("url"));
        bean.setProprietario(rs.getInt("proprietario"));

        documenti.add(bean);
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
    return documenti;

  }

}