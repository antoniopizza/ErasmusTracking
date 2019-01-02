package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.util.DriverManagerConnectionPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StudentiManager
{
    private static final String TAB_NAME = "studenti"; //Nome tabella nel DB

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
    public synchronized void doSave(Studente studente){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
/**int id, String nome, String cognome, String email,
 String password, String sesso, Date dataDiNascita,
 String luogoDiNascita, String nazionalita, String cicloDiStudi,
 String codiceMateria, String telefono, String annoAccademico*/
        String insertSQL = "INSERT INTO " + StudentiManager.TAB_NAME + "(id, nome, cognome, email,password" +
                ",sesso,data_di_nascita,luogo_di_nascita,nazionalita,ciclo_di_studi,codice_materia,,telefono,anno_accademico) VALUES (?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(db, username, password);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, documento.getNome());
            preparedStatement.setString(2, documento.getDataCaricamento());
            preparedStatement.setString(3, documento.getUrl());
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


}
