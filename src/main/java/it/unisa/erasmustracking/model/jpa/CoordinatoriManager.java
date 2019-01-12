package main.java.it.unisa.erasmustracking.model.jpa;

import main.java.it.unisa.erasmustracking.bean.Account;
import main.java.it.unisa.erasmustracking.bean.Coordinatore;
import main.java.it.unisa.erasmustracking.model.dao.IAccountDao;
import main.java.it.unisa.erasmustracking.model.dao.ICoordinatoreDao;
import main.java.it.unisa.erasmustracking.util.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordinatoriManager implements ICoordinatoreDao
{
    private static final String TAB_NAME = "coordinatore"; //Nome tabella nel DB

    public String db,username,password;

    public CoordinatoriManager(String db, String username, String password)
    {
        this.db = db;
        this.username = username;
        this.password = password;
    }

//--------------->genera le query INSERT per aggiungere elementi di tipo Coordinatore al database<---------------

    public synchronized void doSave(Object object)
    {
        Coordinatore coordinatore = (Coordinatore) object;
        AccountManager account = new AccountManager(db,username,password);
        Account bean = new Account();
        bean.setNome(coordinatore.getNome());
        bean.setCognome(coordinatore.getCognome());
        bean.setPassword(coordinatore.getPassword());
        bean.setEmail(coordinatore.getEmail());
        bean.setRuolo(coordinatore.getRuolo());

        account.doSave(bean);

        bean = account.doRetrieveByEmail(coordinatore.getEmail());

        if(coordinatore.getSending_institute() == 0){
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;

            String insertSQL = "INSERT INTO " + CoordinatoriManager.TAB_NAME +" (sending_istitute, account) VALUES(NULL , ?)";

            try
            {
                connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement1 = connection1.prepareStatement(insertSQL);
                preparedStatement1.setInt(1, bean.getId());
                //preparedStatement.setInt(2,coordinatore.getSendingInstitute());

                System.out.println(preparedStatement1.toString());

                preparedStatement1.executeUpdate();

                //connection1.commit();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (preparedStatement1 != null)
                        preparedStatement1.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        DriverManagerConnectionPool.releaseConnection(connection1);
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            String insertSQL = "INSERT INTO " + CoordinatoriManager.TAB_NAME + " (sending_istitute, account) VALUES(? , ?)";

            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, coordinatore.getSending_institute());
                preparedStatement.setInt(2,bean.getId());

                System.out.println(preparedStatement.toString());

                preparedStatement.executeUpdate();

                //connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
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
        }
    }

//--------------->genera le query DELETE per eliminare elementi dal database tramite una apposita key<---------------

    public synchronized boolean doDelete(int id)
    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + CoordinatoriManager.TAB_NAME + " WHERE id = ?";

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
        AccountManager accountManager = new AccountManager(db,username,password);
        Account account = accountManager.doRetrieveById(id);

        String selectSQL = "SELECT * FROM " + CoordinatoriManager.TAB_NAME + " WHERE account = ?";

        try
        {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {

                bean.setSending_institute(rs.getInt("sending_institute"));
                bean.setId(rs.getInt("account"));
                bean.setNome(account.getNome());
                bean.setCognome(account.getCognome());
                bean.setEmail(account.getEmail());
                bean.setPassword(account.getPassword());
                bean.setRuolo("coordinatore");
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


    public synchronized List<Coordinatore> doRetrieveAll()
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

    public synchronized Coordinatore doRetrieveByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Coordinatore bean = new Coordinatore();

        String selectSQL = "SELECT account.nome, account.cognome, account.e_mail, account.password, coordinatore.sending_institute, coordinatore.account FROM " +
                CoordinatoriManager.TAB_NAME + ", account WHERE account.e_mail = ? AND account.id = coordinatore.account";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setNome(rs.getString("account.nome"));
                bean.setCognome(rs.getString("account.cognome"));
                bean.setEmail(rs.getString("account.e_mail"));
                bean.setPassword(rs.getString("account.password"));
                bean.setRuolo("coordinatore");
                bean.setSending_institute(rs.getInt("coordinatore.sending_institute"));
                bean.setId(rs.getInt("coordinatore.account"));

            }

        } catch(SQLException e){
            e.printStackTrace();
        } finally{
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
        return bean;    }

    public synchronized void doUpdate(Object object) {

        Coordinatore coordinatore = (Coordinatore) object;
        coordinatore = doRetrieveByEmail(coordinatore.getEmail());

        Account account = new Account();
        AccountManager manageracc = new AccountManager(db, username, password);
        account = manageracc.doRetrieveByEmail(coordinatore.getEmail());
        account.setNome(coordinatore.getNome());
        account.setCognome(coordinatore.getCognome());
        account.setEmail(coordinatore.getEmail());
        account.setPassword(coordinatore.getPassword());

        manageracc.doUpdate(account);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "UPDATE " + CoordinatoriManager.TAB_NAME + " " +
                "SET sending_institute = ? " +
                "WHERE account = ? ;";


        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);

            // TAB LEARNING AGREEMENT

            preparedStatement.setInt(1, coordinatore.getSending_institute());
            preparedStatement.setInt(2, account.getId());

            //

            System.out.println(preparedStatement.toString());

            preparedStatement.executeUpdate();

            //  connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
}
