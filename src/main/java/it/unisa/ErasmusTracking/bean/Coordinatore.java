package main.java.it.unisa.ErasmusTracking.bean;

public class Coordinatore extends Account {
    private Localita localita;
    private Studente studente;

    public Coordinatore(Utente utente, int id, String nome, String email, String password, Localita localita, Studente studente) {
        super(utente, id, nome, email, password);
        this.localita = localita;
        this.studente = studente;
    }
}
