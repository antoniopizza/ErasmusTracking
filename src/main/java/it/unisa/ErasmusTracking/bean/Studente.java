package main.java.it.unisa.ErasmusTracking.bean;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Studente extends Account {
    private String sesso;
    private String dataDiNascita;
    private String luogoDiNascita;
    private String nazionalita;
    private String cicloDiStudi;
    private String codiceMateria;
    private String telefono;
    private int annoAccademico;
    private String matricola;
    private int idCoordinatore;
    private String tipo;


    //Costruttore
    public Studente(int id, String nome, String cognome, String email,
                    String password,String ruolo, String sesso, String dataDiNascita,
                    String luogoDiNascita, String nazionalita, String cicloDiStudi,
                    String codiceMateria, String telefono, int annoAccademico, String matricola) {
        super(id,nome,cognome, email, password, ruolo);
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.luogoDiNascita = luogoDiNascita;
        this.nazionalita = nazionalita;
        this.cicloDiStudi = cicloDiStudi;
        this.codiceMateria = codiceMateria;
        this.telefono = telefono;
        this.annoAccademico = annoAccademico;
        this.matricola=matricola;
    }

    public Studente()
    {

    }

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getCognome() {
        return super.getCognome();
    }

    public void setCognome(String cognome) {
        super.setCognome(cognome);
    }


    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getRuolo() {
        return super.getRuolo();
    }

    public void setRuolo(String ruolo)
    {
        super.setRuolo("Studente");
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

    public String getDataDiNascita(){
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
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

    public int getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public void setTipo(String tipo){ this.tipo = tipo;}

    public  String getTipo(){return tipo;}

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public int getIdCoordinatore() {
        return idCoordinatore;
    }

    public void setIdCoordinatore(int idCoordinatore) {
        this.idCoordinatore = idCoordinatore;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Sesso= " + sesso + "\n" +
                "Data di nascita= " + dataDiNascita + "\n" +
                "Luogo di nascita= " + luogoDiNascita + "\n" +
                "Nazionalità= " + nazionalita + "\n" +
                "Ciclo di studi= " + cicloDiStudi + "\n" +
                "Codice materia= " + codiceMateria + "\n" +
                "Telefono= " + telefono + "\n" +
                "Anno accademico= " + annoAccademico + "\n" +
                "Matricola= " + matricola + "\n";
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

        Studente acc = (Studente) obj;

        if(super.equals(acc) &&
                this.getSesso().equals(acc.getSesso()) &&
                this.getDataDiNascita().equals(acc.getDataDiNascita()) &&
                this.getLuogoDiNascita().equals(acc.getLuogoDiNascita()) &&
                this.getNazionalita().equals(acc.getNazionalita()) &&
                this.getCicloDiStudi().equals(acc.getCicloDiStudi()) &&
                this.getCodiceMateria().equals(acc.getCodiceMateria()) &&
                this.getTelefono().equals(acc.getTelefono()) &&
                this.getAnnoAccademico() == acc.getAnnoAccademico() &&
                this.getMatricola().equals(acc.getMatricola()))

            return true;

        return false;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        try
        {
            Studente b = (Studente) super.clone();

            b.setRuolo(this.getRuolo());
            b.setSesso(this.getSesso());
            b.setDataDiNascita(this.getDataDiNascita());
            b.setLuogoDiNascita(this.getLuogoDiNascita());
            b.setNazionalita(this.getNazionalita());
            b.setCicloDiStudi(this.getCicloDiStudi());
            b.setCodiceMateria(this.getCodiceMateria());
            b.setTelefono(this.getTelefono());
            b.setAnnoAccademico(this.getAnnoAccademico());
            b.setMatricola(this.getMatricola());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}
