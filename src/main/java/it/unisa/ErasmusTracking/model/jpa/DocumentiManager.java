package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;


import javax.print.Doc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class DocumentiManager implements IDocumentoDao {

    private static final String TAB_NAME = "documenti"; //Nome tabella nel DB
    public String db;
    public String username;
    public String password;

    public DocumentiManager(String db, String username, String password) {

        this.db = db;
        this.username = username;
        this.password = password;
    }


    //Genera query INSERT per salvare un nuovo elemento all'interno del DB
    public synchronized void doSave(Object object){
        Documenti documento = (Documenti) object;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + DocumentiManager.TAB_NAME + "(nome, data_caricamento, url, proprietario) VALUES (?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, documento.getNome());
            preparedStatement.setString(2, documento.getDataCaricamento());
            preparedStatement.setBinaryStream(3, documento.getInputStream(), documento.getFileSize());
            preparedStatement.setInt(4, documento.getProprietario());

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

    //Genera query DELETE per eliminare la riga identificata da 'id' all'interno del DB
    public synchronized boolean doDelete(int id)
            {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + DocumentiManager.TAB_NAME + " WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
            connection.commit();
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
        return (result != 0);
    }

    //Genera query SELECT per ricevere i dati in base a quella determinata key

    public synchronized Documenti doRetrieveById(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Documenti bean = new Documenti();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE id_documento = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("id_documento"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                //bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));
                bean.setInputStream(rs.getBinaryStream("url"));
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

    //genera query SELECT * per prendere tutte le righe dal DB

    public synchronized List<Documenti> doRetrieveAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Documenti> documenti = new ArrayList<Documenti>();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME;
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Documenti bean = new Documenti();
                bean.setId(rs.getInt("id_documento"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));

                documenti.add(bean);
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
        return documenti;

    }

    public synchronized List<Documenti> doRetrieveByIdAccount(int IdAccount)  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Documenti> documenti = new ArrayList<Documenti>();

        String selectSQL = "SELECT * FROM " + DocumentiManager.TAB_NAME + " WHERE proprietario = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, IdAccount);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Documenti bean = new Documenti();
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));

                documenti.add(bean);
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
        return documenti;

    }

    public synchronized List<Documenti> doRetrieveByUsernameStudent(String username) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Documenti> documenti = new ArrayList<Documenti>();

        String selectSQL = "SELECT documenti.id_documento, documenti.nome, documenti.data_caricamento, documenti.url, documenti.proprietario FROM " + DocumentiManager.TAB_NAME + ", studente, account WHERE studente.username = ? AND sudente.account = account.id AND account.id = proprietario";
        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Documenti bean = new Documenti();
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDataCaricamento(rs.getString("data_caricamento"));
                bean.setUrl(rs.getString("url"));
                bean.setProprietario(rs.getInt("proprietario"));

                documenti.add(bean);
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
        return documenti;

    }

    public synchronized void doUpdate(Object object) { }

}