package main.java.it.unisa.ErasmusTracking.model.jpa;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StudentiManager
{
    private static final String TAB_NAME = "Studenti"; //Nome tabella nel DB

    public String db;
    public String username;
    public String password;

    public StudentiManager(String db, String username, String password)
    {
        this.db = db;
        this.username = username;
        this.password = password;
    }

    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Studente studenti){

        Connection connection = null;
        PreparedStatement preparedStatement = null;




        String insertSQL = "INSERT INTO " + StudentiManager.TAB_NAME + "(matricola, nome, cognome, data_di_nascita," +
                "luogo_nascita, sesso, nazionalita, telefono, ciclo_studi, anno_accademico, id_account) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, studenti.getMatricola());
            preparedStatement.setString(2, studenti.getNome());
            preparedStatement.setString(3, studenti.getCognome());
            preparedStatement.setString(4, studenti.getDataDiNascita());
            preparedStatement.setString(5, studenti.getLuogoDiNascita());
            preparedStatement.setString(6, studenti.getSesso());
            preparedStatement.setString(7, studenti.getNazionalita());
            preparedStatement.setString(8, studenti.getTelefono());
            preparedStatement.setString(9, studenti.getCicloDiStudi());
            preparedStatement.setInt(10, studenti.getAnnoAccademico());
            preparedStatement.setInt(11, studenti.getId());




            System.out.println(preparedStatement.toString());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch(SQLException e){
            e.printStackTrace();
        }  finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch(SQLException e){
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

    public synchronized boolean doDelete(int id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM" + StudentiManager.TAB_NAME + "WHERE id = ?";

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


    //Genera query SELECT per ricevere i dati in base a quella determinata key

    public synchronized Studente doRetrieveById(int idAccount) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Studente bean = new Studente();

        String selectSQL = "SELECT * FROM " + StudentiManager.TAB_NAME + " WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idAccount);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setMatricola(rs.getString("matricola"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setDataDiNascita(rs.getDate("data_nascita"));
                bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
                bean.setSesso(rs.getString("sesso"));
                bean.setNazionalita(rs.getString("nazionalità"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCicloDiStudi(rs.getString("ciclo_studi"));
                bean.setAnnoAccademico(rs.getInt("luogo_nascita"));
                bean.setId(rs.getInt("account"));

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
        return bean;
    }

    public synchronized List<Studente> doRetrieveAllStudent() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Studente> studenti = new ArrayList<Studente>();

        String selectSQL = "SELECT * FROM " + StudentiManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Studente bean = new Studente();

                bean.setMatricola(rs.getString("matricola"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setDataDiNascita(rs.getDate("data_nascita"));
                bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
                bean.setSesso(rs.getString("sesso"));
                bean.setNazionalita(rs.getString("nazionalità"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCicloDiStudi(rs.getString("ciclo_studi"));
                bean.setAnnoAccademico(rs.getInt("anno_accademico"));
                bean.setId(rs.getInt("account"));


                studenti.add(bean);
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
        return studenti;

    }


    public synchronized List<Studente> doRetrieveByIdAccount(int IdAccount)  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Studente> studenti = new ArrayList<Studente>();

        String selectSQL = "SELECT * FROM " + StudentiManager.TAB_NAME + " WHERE id = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, IdAccount);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Studente bean = new Studente();

                bean.setMatricola(rs.getString("matricola"));
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setDataDiNascita(rs.getDate("data_nascita"));
                bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
                bean.setSesso(rs.getString("sesso"));
                bean.setNazionalita(rs.getString("nazionalità"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCicloDiStudi(rs.getString("ciclo_studi"));
                bean.setAnnoAccademico(rs.getInt("luogo_nascita"));
                bean.setId(rs.getInt("account"));
                studenti.add(bean);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }  finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }catch (SQLException e) {
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

    public synchronized List<Studente> doRetrieveByMatricola(String matricola) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Studente> studenti = new ArrayList<Studente>();

        String selectSQL = "SELECT studente.nome, studente.cognome, studente.data_nascita, studente.luogo_nascita" +
                ",studente.sesso, studente.nazionalità, studente.telefono, studente.ciclo_studi, studente.anno_accademico, studente.account FROM " +
                StudentiManager.TAB_NAME + " studente, account WHERE studente.matricola = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, matricola);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Studente bean = new Studente();

                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setDataDiNascita(rs.getDate("data_nascita"));
                bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
                bean.setSesso(rs.getString("sesso"));
                bean.setNazionalita(rs.getString("nazionalità"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCicloDiStudi(rs.getString("ciclo_studi"));
                bean.setAnnoAccademico(rs.getInt("anno_accademico"));
                bean.setId(rs.getInt("account"));

                studenti.add(bean);
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
        return studenti;

    }

}
