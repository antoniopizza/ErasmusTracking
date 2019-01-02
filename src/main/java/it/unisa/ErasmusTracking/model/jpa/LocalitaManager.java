package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.resource.DriverManagerConnectionPool;

public class LocalitaManager {
    /** Query per il popolamento */
    private static final String AGGIUNGI_LOCALITA="INSERT INTO localita(citta, nazione) VALUES(?,?)";

    /** Query per la selezione */
    private static final String CERCA_PER_NAZIONE = "SELECT * FROM location " +
            "WHERE nazione = ?";
    private static final String CERCA_PER_CITTA = "SELECT * FROM location " +
            "WHERE citta = ?";
    private static final String VISUALIZZA_TUTTI = "SELECT * FROM location";
    /*private static final String CERCA_PER_CITTA_E_NAZIONE = "SELECT * FROM location " +
                                                            "WHERE citta = ? AND location = ?";
    */


    public String db;
    public String username;
    public String password;

    public LocalitaManager(String db, String username, String password) {

        this.db = db;
        this.username = username;
        this.password = password;
    }

    /**
     * Metodo per l'inserimento di nuova localita' nel database
     * @param localita
     * @throws SQLException
     */
    public synchronized void doSave(Localita localita) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(AGGIUNGI_LOCALITA);

            preparedStatement.setString(1, localita.getCitta());
            preparedStatement.setString(2, localita.getNazione());

            preparedStatement.executeUpdate();

            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Metodo per leggere tutte le tuple del database
     * @return
     * @throws SQLException
     */
    public synchronized Collection<Localita> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Localita> localitaCollection = new LinkedList<Localita>();

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(VISUALIZZA_TUTTI);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Localita bean = new Localita();
                bean.setCitta((rs.getString("citta")));
                bean.setNazione(rs.getString("nazione"));

                localitaCollection.add(bean);
            }
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return localitaCollection;
    }

    /**
     * Metodo per cercare tutte le localita' di una certa citta'
     * @param citta
     * @return
     * @throws SQLException
     */
    public synchronized Collection<Localita> doRetrieveLocalitaByCity(String citta) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Localita> localitaCollection = new LinkedList<>();

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(CERCA_PER_CITTA);

            preparedStatement.setString(1, citta);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Localita bean = new Localita();
                bean.setCitta((rs.getString("citta")));
                bean.setNazione(rs.getString("nazione"));

                localitaCollection.add(bean);
            }

        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return localitaCollection;
    }

    public synchronized Localita doRetrieveById(int id) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Localita localita = new Localita();

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(CERCA_PER_CITTA);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                localita.setCitta((rs.getString("citta")));
                localita.setNazione(rs.getString("nazione"));
            }

        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return localita;
    }


    /**
     * Metodo per cercare tutte le localita' in una determinata nazione
     * @param nazione
     * @return
     * @throws SQLException
     */
    public synchronized Collection<Localita> doRetrieveLocalitaByNation(String nazione) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Localita> localitaCollection = new LinkedList<>();

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(CERCA_PER_NAZIONE);

            preparedStatement.setString(1, nazione);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Localita bean = new Localita();
                bean.setCitta((rs.getString("citta")));
                bean.setNazione(rs.getString("nazione"));

                localitaCollection.add(bean);
            }

        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return localitaCollection;
    }


}