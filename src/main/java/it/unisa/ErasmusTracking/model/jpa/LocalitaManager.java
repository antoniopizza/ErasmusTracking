package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

public class LocalitaManager implements ILocalitaDao {

  /**
   * Query per il popolamento .
   *
   */
  private static final String AGGIUNGI_LOCALITA =
      "INSERT INTO location(citta, nazione, nome, codice_erasmus, coordinatore) VALUES(?,?,?,?,?)";

  /**
   *  Query per la selezione.
   *
   */
  private static final String CERCA_PER_ID =
      "SELECT * FROM location WHERE id_location = ?";
  private static final String CERCA_PER_NAZIONE =
      "SELECT * FROM location WHERE nazione = ?";
  private static final String CERCA_PER_CITTA =
      "SELECT * FROM location WHERE citta = ?";
  private static final String CERCA_PER_CODICE_ERASMUS =
      "SELECT * FROM location WHERE codice_erasmus = ?";
  private static final String CERCA_PER_NOME =
      "SELECT * FROM location WHERE nomeuniversita = ?";
  private static final String CERCA_PER_COORDINATORE =
      "SELECT * FROM location WHERE coordinatore = ?";
  private static final String VISUALIZZA_TUTTI =
      "SELECT * FROM location";

  /**
   *  Query per l'eliminazione.
   *
   */
  private static final String ELIMINA_LOCALITA =
      "DELETE FROM location WHERE id_location = ?";


  public String db;
  public String username;
  public String password;

  /**
   * LocalitàManager.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   */
  public LocalitaManager(String db, String username, String password) {

    this.db = db;
    this.username = username;
    this.password = password;
  }

  /**
   * Metodo per l'inserimento di nuova localita' nel database.
   *
   * @param object
   *
   * @throws SQLException
   *
   */
  public synchronized void doSave(Object object) {

    Localita localita = (Localita) object;

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(AGGIUNGI_LOCALITA);

      preparedStatement.setString(1, localita.getCitta());
      preparedStatement.setString(2, localita.getNazione());
      preparedStatement.setString(3, localita.getNome());
      preparedStatement.setString(4, localita.getCodiceErasmus());
      preparedStatement.setInt(5,localita.getCoordinatore());

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
   * Metodo per l'eliminaione di una localita'.
   *
   * @param id
   *
   * @return
   *
   * @throws SQLException
   *
   */
  public synchronized boolean doDelete(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(ELIMINA_LOCALITA);
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
   * Metodo per leggere tutte le tuple del database.
   *
   * @return
   *
   * @throws SQLException
   *
   */
  public synchronized List<Localita> doRetrieveAll() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Localita> localitaList = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(VISUALIZZA_TUTTI);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Localita bean = new Localita();
        bean.setCitta((rs.getString("citta")));
        bean.setNazione(rs.getString("nazione"));
        bean.setNome(rs.getString("nome"));
        bean.setCodiceErasmus(rs.getString("codice_erasmus"));

        localitaList.add(bean);
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

    return localitaList;
  }

  /**
   * Metodo per cercare tutte le localita' di una certa citta'.
   *
   * @param citta
   *
   * @return
   *
   * @throws SQLException
   *
   */
  public synchronized List<Localita> doRetrieveByCity(String citta) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Localita> localitaList = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(CERCA_PER_CITTA);

      preparedStatement.setString(1, citta);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Localita bean = new Localita();
        bean.setCitta((rs.getString("citta")));
        bean.setNazione(rs.getString("nazione"));
        bean.setNome(rs.getString("nome"));
        bean.setCodiceErasmus(rs.getString("codice_erasmus"));


        localitaList.add(bean);
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

    return localitaList;
  }

  /**
   * Metodo per cercare una localita' tramite il suo ID.
   *
   * @param id
   *
   * @return
   *
   * @throws SQLException
   *
   */
  public synchronized Localita doRetrieveById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Localita localita = new Localita();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(CERCA_PER_ID);

      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        localita.setId(rs.getInt("id_location"));
        localita.setCitta((rs.getString("citta")));
        localita.setNazione(rs.getString("nazione"));
        localita.setNome(rs.getString("nome"));
        localita.setCodiceErasmus(rs.getString("codice_erasmus"));

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

    return localita;
  }

  /**
   * Metodo per cercare tutte le localita' in una determinata nazione.
   *
   * @param nazione
   *
   * @return
   *
   * @throws SQLException
   *
   */
  public synchronized List<Localita> doRetrieveByNation(String nazione) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Localita> localitaList = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(CERCA_PER_NAZIONE);

      preparedStatement.setString(1, nazione);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Localita bean = new Localita();
        bean.setCitta((rs.getString("citta")));
        bean.setNazione(rs.getString("nazione"));
        bean.setNome(rs.getString("nome"));
        bean.setCodiceErasmus(rs.getString("codiceErasmus"));


        localitaList.add(bean);
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

    return localitaList;
  }

  /**
   * Metodo per leggere le localita' con un certo codice Erasmus.
   *
   * @param codiceErasmus
   *
   * @return
   *
   */
  public synchronized List<Localita> doRetrieveByCodiceErasmus(String codiceErasmus) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Localita> localitaList = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(CERCA_PER_CODICE_ERASMUS);

      preparedStatement.setString(1, codiceErasmus);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Localita bean = new Localita();
        bean.setCitta((rs.getString("citta")));
        bean.setNazione(rs.getString("nazione"));
        bean.setNome(rs.getString("nome"));
        bean.setCodiceErasmus(rs.getString("codice_erasmus"));


        localitaList.add(bean);
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

    return localitaList;
  }

  /**
   * metodo per cercare le localita con un certo nome.
   *
   * @param nome
   *
   * @return
   *
   */
  public synchronized List<Localita> doRetrieveByNome(String nome) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Localita> localitaList = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(CERCA_PER_NOME);

      preparedStatement.setString(1, nome);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Localita bean = new Localita();
        bean.setCitta((rs.getString("citta")));
        bean.setNazione(rs.getString("nazione"));
        bean.setNome(rs.getString("nome"));
        bean.setCodiceErasmus(rs.getString("codiceErasmus"));


        localitaList.add(bean);
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

    return localitaList;
  }

  /**
   * metodo per ricerca treamite id coordinatore.
   *
   * @param id
   *
   * @return
   *
   */
  public synchronized List<Localita> doRetrieveByIdCoordinatore(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Localita> localitaList = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(CERCA_PER_COORDINATORE);

      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Localita bean = new Localita();
        bean.setCitta((rs.getString("citta")));
        bean.setNazione(rs.getString("nazione"));
        bean.setNome(rs.getString("nome"));
        bean.setCodiceErasmus(rs.getString("codiceErasmus"));
        bean.setCoordinatore(rs.getInt("coordinatore"));


        localitaList.add(bean);
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

    return localitaList;
  }
}