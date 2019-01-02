package main.java.it.unisa.ErasmusTracking.model.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

public class LocalitaManager implements ILocalitaDao {

    /** Query per il popolamento */
    private static final String AGGIUNGI_LOCALITA="INSERT INTO localita(citta, nazione) VALUES(?,?)";

    /** Query per la selezione */
    private static final String CERCA_PER_ID = "SELECT * FROM location WHERE id_location = ?";
    private static final String CERCA_PER_NAZIONE = "SELECT * FROM location WHERE nazione = ?";
    private static final String CERCA_PER_CITTA = "SELECT * FROM location WHERE citta = ?";
    private static final String VISUALIZZA_TUTTI = "SELECT * FROM location";

    /** Query per l'eliminazione */
    private static final String ELIMINA_LOCALITA = "DELETE FROM location WHERE id_location = ?";


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
     * @param object
     * @throws SQLException
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

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metodo per l'eliminaione di una localita'
     * @param id
     * @return
     * @throws SQLException
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
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
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
     * Metodo per leggere tutte le tuple del database
     * @return
     * @throws SQLException
     */
    public synchronized Collection<Localita> doRetrieveAll() {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
    public synchronized Collection<Localita> doRetrieveByCity(String citta) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return localitaCollection;
    }

    /**
     * Metodo per cercare una localita' tramite il suo ID
     * @param id
     * @return
     * @throws SQLException
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

            while(rs.next()) {
                localita.setId(rs.getInt("id_location"));
                localita.setCitta((rs.getString("citta")));
                localita.setNazione(rs.getString("nazione"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
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
     * Metodo per cercare tutte le localita' in una determinata nazione
     * @param nazione
     * @return
     * @throws SQLException
     */
    public synchronized Collection<Localita> doRetrieveByNation(String nazione){
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    DriverManagerConnectionPool.releaseConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return localitaCollection;
    }


}