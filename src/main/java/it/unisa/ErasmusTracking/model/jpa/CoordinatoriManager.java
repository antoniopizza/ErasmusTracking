package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordinatoriManager
{
    private static final String TAB_NAME = "Coordinatore"; //Nome tabella nel DB

    public String db,username,password;

    public CoordinatoriManager(String db, String username, String password)
    {
        this.db = db;
        this.username = username;
        this.password = password;
    }

//--------------->genera le query INSERT per aggiungere elementi di tipo Coordinatore al database<---------------

    public synchronized void doSave(Coordinatore coordinatore)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + CoordinatoriManager.TAB_NAME +"(id_coordinatore, nome," +
                " cognome, sending_istitute) VALUES( ?, ?, ?, ?)";

        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, coordinatore.getId());
            preparedStatement.setString(2,coordinatore.getNome());
            preparedStatement.setString(3,coordinatore.getCognome());
            //preparedStatement.setInt(4,coordinatore.getSendingInstitute());

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

        String deleteSQL = "DELETE FROM" + CoordinatoriManager.TAB_NAME + "WHERE id = ?";

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

    public synchronized Coordinatore doRetrieveById(int id)
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Coordinatore bean = new Coordinatore();

        String selectSQL = "SELECT * FROM " + CoordinatoriManager.TAB_NAME + " WHERE id = ?";

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
                bean.setSending_institute(rs.getInt("sending_institute"));
                bean.setId(rs.getInt("account"));

            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }  finally
        {
            try
            {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
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

    public synchronized List<Coordinatore> doRetrieveByIdAccount(int IdAccount)
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Coordinatore> coordinatori  = new ArrayList<Coordinatore>();

        String selectSQL = "SELECT * FROM " + CoordinatoriManager.TAB_NAME + " WHERE id = ?";
        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, IdAccount);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Coordinatore bean = new Coordinatore();

                bean.setId_coordinatore(rs.getInt("id_coordinatore"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setSending_institute(rs.getInt("sending_institute"));
                bean.setId(rs.getInt("account"));
                coordinatori.add(bean);
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
        return coordinatori;

    }

    public synchronized List<Coordinatore> doRetrieveAllCoordinator()
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Coordinatore> coordinatori = new ArrayList<Coordinatore>();

        String selectSQL = "SELECT * FROM " + CoordinatoriManager.TAB_NAME;
        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Coordinatore bean = new Coordinatore();

                bean.setId(rs.getInt("id_coordinatore"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setSending_institute(rs.getInt("sending_institute"));

                coordinatori.add(bean);
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
        return coordinatori;

    }
}
