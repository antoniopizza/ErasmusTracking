package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.ILearningAgreementDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;




public class LearningAgreementManager implements ILearningAgreementDao {

  private static final String TAB_NAME = "learningAgreement"; //Nome tabella nel DB


  public String db;
  public String username;
  public String password;

  /**
   * LerarninigAgreement.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   */

  public LearningAgreementManager(String db, String username, String password) {
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

    LearningAgreement learningAgreement = (LearningAgreement) object;

    StudenteManager studenteManager = new StudenteManager(db,username,password);


    Studente studente = (Studente) studenteManager.doRetrieveById(learningAgreement.getStudente().getId());


      Connection connection = null;
      PreparedStatement preparedStatement = null;

      String insertSql = "INSERT INTO "
          +
          LearningAgreementManager.TAB_NAME
          +
          " (tipologiaErasmus, "
          +
          "stato, livello_conoscenza_lingua, studente)"
          +
          " VALUES (?, ?, ?, ?)";


      try {
        connection = DriverManagerConnectionPool.getConnection(db, username, password);
        preparedStatement = connection.prepareStatement(insertSql);

        // TAB LEARNING AGREEMENT

        preparedStatement.setString(1, learningAgreement.getTipologiaErasmus());
        preparedStatement.setString(2, learningAgreement.getStato());
        preparedStatement.setInt(4, learningAgreement.getStudente().getId()); //RICORDARSI MATRICOLA
        preparedStatement.setString(3, learningAgreement.getConoscenzaLingua()); //

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
        LearningAgreementManager.TAB_NAME
        +
        " WHERE id_learning_agreement = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, id);

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

  @Override
  public List<LearningAgreement> doRetrieveAll() {
    List<LearningAgreement>  listaLerningAgreement = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    LearningAgreement learningAgreement = new LearningAgreement();
    String selectSql =  "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM "
            +
            LearningAgreementManager.TAB_NAME;


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        LearningAgreement bean = new LearningAgreement();
        bean.setId(rs.getInt("id_learning_agreement"));
        bean.setTipologiaErasmus(rs.getString("tipologiaErasmus"));
        bean.setStato(rs.getString("stato"));

        //ON HOLD

        IStudenteDao studenteManager = new StudenteManager(db, username, password);
        Studente studente = (Studente) studenteManager.doRetrieveById(rs.getInt("studente"));
        bean.setStudente(studente);

        listaLerningAgreement.add(bean);
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
    return listaLerningAgreement;
  }

  /**
   * doRetiveByStudente.
   *
   * @param idStudente
   *
   * @return
   *
   */

  public synchronized LearningAgreement doRetrieveByStudente(int idStudente) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    LearningAgreement learningAgreement = new LearningAgreement();

    IStudenteDao stud = new StudenteManager(db,username,password);

    //INIZIO QUERY

    String selectSql =  "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM "
        +
        LearningAgreementManager.TAB_NAME
        +
        ", studente WHERE "
        +
        LearningAgreementManager.TAB_NAME
        +
        ".studente = ?";


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, idStudente);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        learningAgreement.setId(rs.getInt("id_learning_agreement"));
        learningAgreement.setTipologiaErasmus(rs.getString("tipologiaErasmus"));
        learningAgreement.setStato(rs.getString("stato"));
        learningAgreement.setStudente((Studente) stud.doRetrieveById(idStudente));

        //ON HOLD

        IStudenteDao studenteManager = new StudenteManager(db, username, password);
        Studente studente = (Studente) studenteManager.doRetrieveById(idStudente);
        learningAgreement.setStudente(studente);

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
    return learningAgreement;
  }

  /**
   * doRetriveById.
   *
   * @param id
   *
   * @return
   *
   */

  public synchronized LearningAgreement doRetrieveById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    LearningAgreement learningAgreement = new LearningAgreement();
    String selectSql = "SELECT id_learning_agreement, tipologiaErasmus, stato, studente  FROM "
        +
        LearningAgreementManager.TAB_NAME
        +
        " WHERE id_learning_agreement = ?";


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        learningAgreement.setId(rs.getInt("id_learning_agreement"));
        learningAgreement.setTipologiaErasmus(rs.getString("tipologiaErasmus"));
        learningAgreement.setStato(rs.getString("stato"));

        //ON HOLD

        /*
        IStudenteDao studenteManager = new StudenteManager();
        Studente studente = studenteManager.doRetrieveByMatricola(rs.getString("studente"));
        learningAgreement.setStudente(studente);
        */
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
      }
    }return learningAgreement;
  }




  /**
   * doDelete.
   *
   * @param object
   *
   */

  public synchronized void doUpdate(Object object) {

    LearningAgreement learningAgreement = (LearningAgreement) object;
    StudenteManager studenteManager = new StudenteManager(db,username,password);
    Studente studente =
        (Studente) studenteManager.doRetrieveById(learningAgreement.getStudente().getId());

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "UPDATE " + LearningAgreementManager.TAB_NAME + " "
        +
        "SET tipologiaErasmus = ?, stato = ?, livello_conoscenza_lingua = ? "
        +
        "WHERE studente = ? ;";


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);

      // TAB LEARNING AGREEMENT
      preparedStatement.setString(1, learningAgreement.getTipologiaErasmus());
      preparedStatement.setString(2, learningAgreement.getStato());
      preparedStatement.setString(3, learningAgreement.getConoscenzaLingua()); //
      preparedStatement.setInt(4, studente.getId()); //RICORDARSI MATRICOLA

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