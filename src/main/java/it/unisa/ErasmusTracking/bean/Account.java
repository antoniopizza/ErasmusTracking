package main.java.it.unisa.ErasmusTracking.bean;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;

public class Account {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private ArrayList<Documenti> doc;

    //costrutore
    public Account( int id, String nome, String cognome, String email, String password ) {
        this.id = id;
        this.cognome=cognome;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.doc = new ArrayList<>() ;
    }

    public Account(){
        this.doc = new ArrayList<>() ;

    }

    //Get&Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Fine Get&Set



    public void addDocumento(Documenti documento){
        this.doc.add(documento);
    }

    public void delDocumento(Documenti documento){
        this.doc.remove(documento);
    }

}

