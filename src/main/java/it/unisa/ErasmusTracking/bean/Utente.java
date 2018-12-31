package main.java.it.unisa.ErasmusTracking.bean;

public class Utente {
    private String nome;
    private String cognome;

    //costruttore
    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    //Get&Set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String toString() {
        return "Utente[nome=" + nome + ",cognome=" + cognome + "]";
    }
}
