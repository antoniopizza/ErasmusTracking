package main.java.it.unisa.ErasmusTracking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

  private static List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Class.forName eseguita.");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Crea una connessione con il database
   * utilizzando i parametri db, username e password passati dall'esterno.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   * @return
   *
   * @throws SQLException
   *
   */

  private static synchronized Connection createDbConnection(String db, String username,
                                                            String password)
      throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    //db = "erasmusTracking";
    //username = "root";
    //password = "root";
    try {
      newConnection =
          DriverManager.getConnection(
              "jdbc:mysql://"
                  +
                  ip
                  +
                  ":"
                  +
                  port
                  +
                  "/"
                  +
                  db
                  +
                  "?relaxAutoCommit=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift"
                  +
                  "=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //newConnection.setAutoCommit(false);
    //newConnection.toString();
    return newConnection;
  }


  /**
   * Restituisce una connessione col database se esistente, altrimenti la crea.
   *
   * @param db
   *
   * @param username
   *
   * @param password
   *
   * @return
   *
   * @throws SQLException
   *
   */
  public static synchronized Connection getConnection(String db, String username, String password)
      throws SQLException {
    Connection connection;

    /**
     * la connessione è già presente.
     */
    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);
      try {
        if (connection.isClosed()) {
          connection = getConnection(db, username, password);
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection(db, username, password);
      }
    } else {
      /**
       *non esiste connessione, la creo.
       */
      connection = createDbConnection(db, username, password);
    }

    return connection;
  }

  /**
   *Chiude la connessione col db.
   */

  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) {
      freeDbConnections.add(connection);
    }
  }
}