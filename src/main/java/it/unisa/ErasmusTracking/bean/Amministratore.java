package main.java.it.unisa.ErasmusTracking.bean;

public class Amministratore extends Account {
    private int id;

    public Amministratore(Utente utente, int id, String nome, String email, String password, int id1) {
        super(utente, id, nome, email, password);
        this.id = id1;
    }
}
