package main.java.it.unisa.ErasmusTracking.bean;
import java.util.Date;

public class Studente extends Account {
    private String sesso;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private String nazionalita;
    private String cicloDiStudi;
    private String codiceMateria;
    private String telefono;
    private String annoAccademico;
    private String matricola;

    //Costruttore
    public Studente(Utente utente,int id, String nome, String email, String password, String sesso, Date dataDiNascita, String luogoDiNascita, String nazionalita, String cicloDiStudi, String codiceMateria, String telefono, String annoAccademico) {
        super(utente, id, nome, email, password);
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.luogoDiNascita = luogoDiNascita;
        this.nazionalita = nazionalita;
        this.cicloDiStudi = cicloDiStudi;
        this.codiceMateria = codiceMateria;
        this.telefono = telefono;
        this.annoAccademico = annoAccademico;
    }
    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
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

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getCicloDiStudi() {
        return cicloDiStudi;
    }

    public void setCicloDiStudi(String cicloDiStudi) {
        this.cicloDiStudi = cicloDiStudi;
    }

    public String getCodiceMateria() {
        return codiceMateria;
    }

    public void setCodiceMateria(String codiceMateria) {
        this.codiceMateria = codiceMateria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(String annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
