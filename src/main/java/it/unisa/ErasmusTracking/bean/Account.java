package main.java.it.unisa.ErasmusTracking.bean;

import java.util.ArrayList;

public class Account {
    private int id;
    private String email;
    private String password;
    private String ruolo;
    private ArrayList<Documenti> doc;

    //costrutore
    public Account( int id, String email, String password, String ruolo ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.doc = new ArrayList<>() ;
    }

    public Account(){
        this.doc = new ArrayList<>() ;

    }

    //Get&Set


    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Documenti> getDoc() {
        return doc;
    }

    public void setDoc(ArrayList<Documenti> doc) {
        this.doc = doc;
    }

    //Fine Get&Set

    public void addDocumento(Documenti documento){
        this.doc.add(documento);
    }

    public void delDocumento(Documenti documento){
        this.doc.remove(documento);
    }

    public String toString() {
       return "Account[id= " + id + ", email= "+ email + ", password= " + password + ", ruolo= " + ruolo + ", doc= "+ doc +"] ";
    }


    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
            return false;

        Account acc = (Account)obj;
        return this.equals(acc);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        try
        {

            Account b = (Account) super.clone();

            b.setId(this.getId());
            b.setEmail(this.getEmail());
            b.setPassword(this.getPassword());
            b.setRuolo(this.getRuolo());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}

