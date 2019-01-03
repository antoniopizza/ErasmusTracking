package main.java.it.unisa.ErasmusTracking.bean;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Studente extends Account {
    private String sesso;
    private DateFormat data;
    private String dataString;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private String nazionalita;
    private String cicloDiStudi;
    private String codiceMateria;
    private String telefono;
    private int annoAccademico;
    private String matricola;

    //Costruttore
    public Studente(int id, String nome, String cognome, String email,
                    String password,String ruolo, String sesso, String dataDiNascita,
                    String luogoDiNascita, String nazionalita, String cicloDiStudi,
                    String codiceMateria, String telefono, int annoAccademico, String matricola) {
        super(id,nome,cognome, email, password, ruolo);
        this.sesso = sesso;
        dataString = dataDiNascita;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.dataDiNascita=data.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getRuolo() {
        return super.getRuolo();
    }

    public void setRuolo(String ruolo) {
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
        return data.format(dataDiNascita);
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

    public int getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass())
            return false;

        Studente stu = (Studente) obj;
        return this.equals(stu);
    }

   /* @Override
    protected Object clone() throws CloneNotSupportedException
    {
        try
        {
            Studente b = (Studente) super.clone();

            b.setRuolo(this.getRuolo());
            b.setSesso(this.getSesso());
            //b.setDataDiNascita(this.getDataDiNascita());
            b.setLuogoDiNascita(this.getLuogoDiNascita());
            b.setNazionalita(this.getNazionalita());
            b.setCicloDiStudi(this.setCicloDiStudi());
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
*/
}
