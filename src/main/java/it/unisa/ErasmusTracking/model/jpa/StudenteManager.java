package main.java.it.unisa.ErasmusTracking.model.jpa;


import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IAccountDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudenteManager implements IStudenteDao {

    private static final String TAB_NAME = "studente"; //Nome tabella nel DB
    public String db;
    public String username;
    public String password;

    public StudenteManager(String db, String username, String password) {

        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Object object) {
        Studente studente = (Studente) object;
        AccountManager account = new AccountManager(db,username,password);
        Account bean = new Account();
        bean.setNome(studente.getNome());
        bean.setCognome(studente.getCognome());
        bean.setPassword(studente.getPassword());
        bean.setEmail(studente.getEmail());
        bean.setRuolo(studente.getRuolo());

        account.doSave(bean);



        bean = account.doRetrieveByEmail(studente.getEmail());



        if (studente.getLuogoDiNascita() == null && studente.getDataDiNascita() == null) {
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;

            String insertSQL = "INSERT INTO " + StudenteManager.TAB_NAME + " (matricola, data_nascita," +
                    "luogo_nascita, sesso, nazionalita, telefono, ciclo_studi, anno_accademico, account, coordinatore) " +
                    "VALUES (?, NULL, NULL, NULL, NULL, NULL, NULL, NULL , ?, ?) ";

            try {
                connection1 = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement1 = connection1.prepareStatement(insertSQL);
                preparedStatement1.setString(1, studente.getMatricola());
                preparedStatement1.setInt(2, bean.getId());
                preparedStatement1.setInt(3,studente.getIdCoordinatore());


                System.out.println(preparedStatement1.toString());

                preparedStatement1.executeUpdate();


               // connection1.commit();
            } catch(SQLException e){
                e.printStackTrace();
            }  finally {
                try {
                    if (preparedStatement1 != null)
                        preparedStatement1.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
                finally {
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

            laManager.doSave(learningAgreement);


        } else {


            Connection connection = null;
            PreparedStatement preparedStatement = null;

            String insertSQL = "INSERT INTO " + StudenteManager.TAB_NAME + " (matricola, data_nascita," +
                    "luogo_nascita, sesso, nazionalita, telefono, ciclo_studi, anno_accademico, account, coordinatore) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            try {
                connection = DriverManagerConnectionPool.getConnection(db, username, password);
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, studente.getMatricola());
                preparedStatement.setString(2, studente.getDataDiNascita());
                preparedStatement.setString(3, studente.getLuogoDiNascita());
                preparedStatement.setString(4, studente.getSesso());
                preparedStatement.setString(5, studente.getNazionalita());
                preparedStatement.setString(6, studente.getTelefono());
                preparedStatement.setString(7, studente.getCicloDiStudi());
                preparedStatement.setInt(8, studente.getAnnoAccademico());
                preparedStatement.setInt(9, studente.getId());
                preparedStatement.setInt(10, studente.getIdCoordinatore());

                System.out.println(preparedStatement.toString());

                preparedStatement.executeUpdate();

                connection.commit();
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

    @Override
    public synchronized boolean doDelete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + StudenteManager.TAB_NAME + " WHERE id = ?";

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

    @Override
    public synchronized List<?> doRetrieveAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Studente> studenti = new ArrayList<Studente>();

        String selectSQL = "SELECT * FROM " + StudenteManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Studente bean = new Studente();

                bean.setMatricola(rs.getString("matricola"));
                bean.setDataDiNascita(rs.getString("data_nascita"));
                bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
                bean.setSesso(rs.getString("sesso"));
                bean.setNazionalita(rs.getString("nazionalita"));
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

    @Override
    public synchronized Object doRetrieveById(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Studente bean = new Studente();

        String selectSQL = "SELECT * FROM " + StudenteManager.TAB_NAME + " WHERE account = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
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


    @Override
    public synchronized Studente doRetrieveByMatricola(String matricola) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Studente bean = new Studente();

        String selectSQL = "SELECT studente.nome, studente.cognome, studente.data_nascita, studente.luogo_nascita" +
                ",studente.sesso, studente.nazionalita, studente.telefono, studente.ciclo_studi, studente.anno_accademico, studente.account FROM " +
                StudenteManager.TAB_NAME + " studente, account WHERE studente.matricola = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, matricola);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                bean.setDataDiNascita(rs.getString("data_nascita"));
                bean.setLuogoDiNascita(rs.getString("luogo_nascita"));
                bean.setSesso(rs.getString("sesso"));
                bean.setNazionalita(rs.getString("nazionalita"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCicloDiStudi(rs.getString("ciclo_studi"));
                bean.setAnnoAccademico(rs.getInt("anno_accademico"));
                bean.setId(rs.getInt("account"));

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
        return bean;

    }

    public synchronized Studente doRetrieveByEmail(String email) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Studente bean = new Studente();

        String selectSQL = "SELECT account.nome, account.cognome, studente.data_nascita, studente.luogo_nascita, studente.matricola" +
                ",studente.sesso, studente.nazionalita, studente.telefono, studente.ciclo_studi, studente.anno_accademico, studente.account FROM " +
                StudenteManager.TAB_NAME + " studente, account WHERE account.e_mail = ? AND account.id = studente.account";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setNome(rs.getString("account.nome"));
                bean.setCognome(rs.getString("account.cognome"));
                bean.setMatricola(rs.getString("studente.matricola"));
                bean.setDataDiNascita(rs.getString("studente.data_nascita"));
                bean.setLuogoDiNascita(rs.getString("studente.luogo_nascita"));
                bean.setSesso(rs.getString("studente.sesso"));
                bean.setNazionalita(rs.getString("studente.nazionalita"));
                bean.setTelefono(rs.getString("studente.telefono"));
                bean.setCicloDiStudi(rs.getString("studente.ciclo_studi"));
                bean.setAnnoAccademico(rs.getInt("studente.anno_accademico"));
                bean.setId(rs.getInt("studente.account"));

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
        return bean;
    }
}