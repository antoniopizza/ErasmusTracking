package main.java.it.unisa.ErasmusTracking.bean;

import java.util.ArrayList;

public class Coordinatore extends Account {
    private ArrayList<Localita> localita;
    private Studente studente;

    public Coordinatore( int id, String nome, String cognome, String email, String password, Localita localita, Studente studente) {
        super(id, nome, cognome, email, password);
        this.localita = new ArrayList<Localita>();
        this.studente = studente;
    }

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getCognome(){
        return super.getCognome();
    }

    public void setCognome (String cognome) {
        super.setCognome(cognome);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public ArrayList<Localita> getLocalita() {
        return localita;
    }

    public void setLocalita(ArrayList<Localita> localita) {
        this.localita = localita;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public String toString() {
        return super.toString()+ "Coordinatore[Località=" + localita + ",Studente=" + studente + "]";
    }

}
