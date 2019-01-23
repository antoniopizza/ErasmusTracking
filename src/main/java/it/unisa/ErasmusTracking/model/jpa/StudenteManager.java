package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;




public class StudenteManager implements IStudenteDao {

  private static final String TAB_NAME = "studente"; //Nome tabella nel DB
  public String db;
  public String username;
  public String password;

  /**
   * StudenteManager.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   */
  public StudenteManager(String db, String username, String password) {

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
    Studente studente = (Studente) object;

    Account bean = new Account();
    bean.setNome(studente.getNome());
    bean.setCognome(studente.getCognome());
    bean.setPassword(studente.getPassword());
    bean.setEmail(studente.getEmail());
    bean.setRuolo(studente.getRuolo());

    AccountManager account = new AccountManager(db,username,password);
    account.doSave(bean);

    bean = account.doRetrieveByEmail(studente.getEmail());



    if (studente.getLuogoDiNascita() == null && studente.getDataDiNascita() == null) {
      Connection connection1 = null;
      PreparedStatement preparedStatement1 = null;

      String insertSql = "INSERT INTO " + StudenteManager.TAB_NAME + " (matricola, data_nascita,"
          +
          "luogo_nascita, sesso, nazionalita,"
          +
          " telefono, ciclo_studi, anno_accademico, "
          +
          "account, coordinatore) "
          +
          "VALUES (?, NULL, NULL, NULL, NULL, NULL, NULL, NULL , ?, ?) ";

      try {
        connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
        preparedStatement1 = connection1.prepareStatement(insertSql);
        preparedStatement1.setString(1, studente.getMatricola());
        preparedStatement1.setInt(2, bean.getId());
        preparedStatement1.setInt(3,studente.getIdCoordinatore());


        System.out.println(preparedStatement1.toString());

        preparedStatement1.executeUpdate();


        // connection1.commit();
      } catch (SQLException e) {
        e.printStackTrace();
      }  finally {
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

      Studente prova = (Studente) doRetrieveById(bean.getId());


      LearningAgreement learningAgreement = new LearningAgreement();
      LearningAgreementManager laManager = new LearningAgreementManager(db, username, password);

      learningAgreement.setStudente(prova);
      learningAgreement.setTipologiaErasmus(studente.getTipo());

      laManager.doSave(learningAgreement);


    } else {


      Connection connection = null;
      PreparedStatement preparedStatement = null;

      String insertSql = "INSERT INTO " + StudenteManager.TAB_NAME + " (matricola, data_nascita,"
          +
          "luogo_nascita, sesso, nazionalita, telefono, "
          +
          "ciclo_studi, anno_accademico, account, coordinatore) "
          +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

      try {
        connection = DriverManagerConnectionPool.getConnection(db, username, password);
        preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, studente.getMatricola());
        preparedStatement.setString(2, studente.getDataDiNascita());
        preparedStatement.setString(3, studente.getLuogoDiNascita());
        preparedStatement.setString(4, studente.getSesso());
        preparedStatement.setString(5, studente.getNazionalita());
        preparedStatement.setString(6, studente.getTelefono());
        preparedStatement.setString(7, studente.getCicloDiStudi());
        preparedStatement.setInt(8, studente.getAnnoAccademico());
        preparedStatement.setInt(9, bean.getId());
        preparedStatement.setInt(10, studente.getIdCoordinatore());

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
  }

  @Override
  public synchronized boolean doDelete(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "DELETE FROM " + StudenteManager.TAB_NAME + " WHERE account = ?";

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

  @Override
  public synchronized List<Studente> doRetrieveAll() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<Studente> studenti = new ArrayList<Studente>();

    String selectSql = "SELECT * FROM " + StudenteManager.TAB_NAME;
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Studente bean = new Studente();

        bean.setId(rs.getInt("account"));

        AccountManager accountManager = new AccountManager(db,username,password);
        Account account = accountManager.doRetrieveById(bean.getId());
        bean.setNome(account.getNome());
        bean.setCognome(account.getCognome());
        bean.setPassword(account.getPassword());
        bean.setEmail(account.getEmail());
        bean.setRuolo(account.getRuolo());
        bean.setMatricola(rs.getString("matricola"));
        bean.setDataDiNascita(rs.getString("data_nascita"));
        bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
        bean.setSesso(rs.getString("sesso"));
        bean.setNazionalita(rs.getString("nazionalita"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setCicloDiStudi(rs.getString("ciclo_studi"));
        bean.setAnnoAccademico(rs.getInt("anno_accademico"));


        studenti.add(bean);
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
    return studenti;

  }

  @Override
  public synchronized Studente doRetrieveById(int id) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Studente bean = new Studente();

    String selectSql = "SELECT account.nome, account.cognome,"
        +
        " account.e_mail, account.password, studente.data_nascita, "
        +
        "studente.luogo_nascita, studente.matricola, "
        +
        "studente.sesso, studente.nazionalita, studente.telefono, "
        +
        "studente.ciclo_studi, studente.anno_accademico, studente.account, "
        +
        "studente.coordinatore FROM "
        +
        StudenteManager.TAB_NAME
        +
        ", account WHERE studente.account = ? "
        +
        "AND studente.account = account.id_account";

    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Account account = new Account();
        AccountManager man = new AccountManager(db,username,password);
        account = man.doRetrieveById(id);

        bean.setNome(account.getNome());
        bean.setCognome(account.getCognome());
        bean.setEmail(account.getEmail());
        bean.setPassword(account.getPassword());
        bean.setRuolo("studente");
        bean.setMatricola(rs.getString("matricola"));
        bean.setDataDiNascita(rs.getString("data_nascita"));
        bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
        bean.setSesso(rs.getString("sesso"));
        bean.setNazionalita(rs.getString("nazionalita"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setCicloDiStudi(rs.getString("ciclo_studi"));
        bean.setAnnoAccademico(rs.getInt("anno_accademico"));
        bean.setId(rs.getInt("account"));
        bean.setIdCoordinatore(rs.getInt("coordinatore"));

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


  @Override
  public synchronized Studente doRetrieveByMatricola(String matricola) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Studente bean = new Studente();

    String selectSql = "SELECT account.nome, account.cognome, "
        +
        "studente.data_nascita, studente.luogo_nascita, account.e_mail"
        +
        ",studente.sesso, studente.nazionalita, studente.telefono, "
        +
        "studente.ciclo_studi, studente.anno_accademico, studente.account, "
        +
        "studente.coordinatore, studente.matricola FROM "
        +
        StudenteManager.TAB_NAME
        +
        " studente, account WHERE studente.matricola = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, matricola);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setNome(rs.getString("account.nome"));
        bean.setCognome(rs.getString("account.cognome"));
        bean.setEmail(rs.getString("account.e_mail"));
        bean.setRuolo("studente");
        bean.setMatricola(rs.getString("matricola"));
        bean.setDataDiNascita(rs.getString("data_nascita"));
        bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
        bean.setSesso(rs.getString("sesso"));
        bean.setNazionalita(rs.getString("nazionalita"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setCicloDiStudi(rs.getString("ciclo_studi"));
        bean.setAnnoAccademico(rs.getInt("anno_accademico"));
        bean.setId(rs.getInt("account"));
        bean.setIdCoordinatore(rs.getInt("coordinatore"));
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
   * doRetirveByEmail.
   *
   * @param email
   *
   * @return
   *
   */
  public synchronized Studente doRetrieveByEmail(String email) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Studente bean = new Studente();

    String selectSql = "SELECT account.nome, account.cognome, account.e_mail,"
        +
        " account.password, studente.data_nascita, studente.luogo_nascita, "
        +
        "studente.matricola,studente.sesso, studente.nazionalita, studente.telefono,"
        +
        " studente.ciclo_studi, studente.anno_accademico, studente.account, studente.coordinatore "
        +
        "FROM "
        +
        StudenteManager.TAB_NAME
        +
        ", account WHERE account.e_mail = ? AND account.id_account = studente.account";
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
        bean.setMatricola(rs.getString("studente.matricola"));
        bean.setDataDiNascita(rs.getString("studente.data_nascita"));
        bean.setLuogoDiNascita(rs.getString("studente.luogo_nascita"));
        bean.setSesso(rs.getString("studente.sesso"));
        bean.setNazionalita(rs.getString("studente.nazionalita"));
        bean.setTelefono(rs.getString("studente.telefono"));
        bean.setCicloDiStudi(rs.getString("studente.ciclo_studi"));
        bean.setAnnoAccademico(rs.getInt("studente.anno_accademico"));
        bean.setId(rs.getInt("studente.account"));
        bean.setIdCoordinatore(rs.getInt("coordinatore"));


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
   * soUpdate.
   *
   * @param object
   *
   */
  public synchronized void doUpdate(Object object) {
    Studente oldStudente = new Studente();
    Studente studente = (Studente) object;

    oldStudente = doRetrieveByEmail(studente.getEmail());

    Account account = new Account();
    AccountManager manageracc = new AccountManager(db, username, password);
    account = manageracc.doRetrieveByEmail(oldStudente.getEmail());
    account.setNome(oldStudente.getNome());
    account.setCognome(oldStudente.getCognome());
    account.setEmail(oldStudente.getEmail());
    account.setPassword(oldStudente.getPassword());
    account.setId(oldStudente.getId());


    manageracc.doUpdate(account);

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "UPDATE " + StudenteManager.TAB_NAME
        +
        " SET matricola = ?, data_nascita = ?, luogo_nascita = ?,"
        +
        " sesso = ?, nazionalita = ?, telefono = ?, ciclo_studi = ?,"
        +
        " anno_accademico = ? "
        +
        "WHERE account = ? ;";


    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(insertSql);

      // TAB LEARNING AGREEMENT

      preparedStatement.setString(1, oldStudente.getMatricola());
      preparedStatement.setString(2, studente.getDataDiNascita());
      preparedStatement.setString(3, studente.getLuogoDiNascita()); //
      preparedStatement.setString(4, studente.getSesso());
      preparedStatement.setString(5, studente.getNazionalita());
      preparedStatement.setString(6, studente.getTelefono());
      preparedStatement.setString(7, studente.getCicloDiStudi());
      preparedStatement.setInt(8, studente.getAnnoAccademico());
      preparedStatement.setInt(9, account.getId());

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
   * doRetiveByIdCoordinatore.
   *
   * @param id
   *
   * @return
   *
   */
  public synchronized List<Studente> doRetrieveByCoordinatore(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;



    List<Studente> studenti = new ArrayList<Studente>();

    String selectSql = "SELECT * FROM account, studente "
        +
        "WHERE studente.account = id_account "
        +
        "AND studente.coordinatore = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection(db, username, password);
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1,id);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Studente bean = new Studente();
        bean.setId(rs.getInt("id_account"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setPassword(rs.getString("password"));
        bean.setRuolo(rs.getString("ruolo"));
        bean.setDataDiNascita(rs.getString("data_nascita"));
        bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
        bean.setSesso(rs.getString("sesso"));
        bean.setNazionalita(rs.getString("nazionalita"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setCicloDiStudi(rs.getString("ciclo_studi"));
        bean.setAnnoAccademico(rs.getInt("anno_accademico"));
        bean.setIdCoordinatore(rs.getInt("coordinatore"));

        studenti.add(bean);
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
    return studenti;

  }


}