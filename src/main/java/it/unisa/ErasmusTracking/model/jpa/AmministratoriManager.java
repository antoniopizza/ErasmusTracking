package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmministratoriManager
{
    private static final String TAB_NAME = "Amministratore"; //Nome tabella nel DB

    public String db,username,password;


    public AmministratoriManager(String db, String username, String password)
    {
        this.db = db;
        this.username = username;
        this.password = password;
    }

//--------------->genera le query INSERT per aggiungere elementi di tipo Amministratore al database<---------------

    public synchronized void doSave(Amministratore amministratore)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + AmministratoriManager.TAB_NAME +"(id,nome," +
                " cognome,account) VALUES( ?, ?, ?, ?)";

        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1,amministratore.getId_amministratore());
            preparedStatement.setString(2,amministratore.getNome());
            preparedStatement.setString(3,amministratore.getCognome());
            preparedStatement.setInt(4,amministratore.getId());


            System.out.println(preparedStatement.toString());

            preparedStatement.executeUpdate();

            connection.commit();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


//--------------->genera le query DELETE per eliminare elementi dal database tramite una apposita key<---------------

    public synchronized boolean doDelete(int id)
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM" + AmministratoriManager.TAB_NAME + "WHERE id = ?";

        try
        {
            connection = DriverManagerConnectionPool.getConnection(db,username,password);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);

            result = preparedStatement.executeUpdate();
            connection.commit();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                if(preparedStatement!=null)
                    preparedStatement.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return (result!=0);

    }

//--------------->genera query SELECT per ricevere i dati in base a una determinata key <---------------

    public synchronized Amministratore doRetrieveById(int id)
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Amministratore bean = new Amministratore();

        String selectSQL = "SELECT * FROM " + AmministratoriManager.TAB_NAME + " WHERE id = ?";

        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setId(rs.getInt("account"));

            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }


    public synchronized List<Amministratore> doRetrieveByIdAccount(int IdAccount)
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Amministratore> amministratori  = new ArrayList<Amministratore>();

        String selectSQL = "SELECT * FROM " + AmministratoriManager.TAB_NAME + " WHERE id = ?";
        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, IdAccount);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Amministratore bean = new Amministratore();

                bean.setId_amministratore(rs.getInt("id_amministratore"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                amministratori.add(bean);
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return amministratori;

    }
    public synchronized List<Amministratore> doRetrieveAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Amministratore> amministratore = new ArrayList<Amministratore>();

        String selectSQL = "SELECT * FROM " + AmministratoriManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Amministratore bean = new Amministratore();

                bean.setId_amministratore(rs.getInt("id_amministratore"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setId(rs.getInt("account"));

                amministratore.add(bean);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }  finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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

}