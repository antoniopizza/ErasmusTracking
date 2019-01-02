package main.java.it.unisa.ErasmusTracking.bean;

import java.util.ArrayList;

public class Coordinatore extends Account {
    private String nome,cognome;
    private int id_coordinatore,sending_institute;
    private ArrayList<Studente> studente;
    //private ArrayList<Localita> localita; <------dubbi sul mantenerla o no

    public Coordinatore( int id_coordinatore, String nome, String cognome,/**ArrayList<Localita> localita,*/String email,
                         String password, String ruolo,int sending_institute, Studente studente,int id)
    {
        super(id, email, password, ruolo);
        this.id_coordinatore = id_coordinatore;
        this.cognome=cognome;
        this.nome = nome;
        this.sending_institute = sending_institute;
        this.studente = new ArrayList<Studente>();
        //this.localita = new ArrayList<Localita>(); <--- dubbio vedi sopra

    }

    public Coordinatore()
    {

    }

    public int getId_coordinatore()
    {
        return id_coordinatore;
    }

    public void setId_coordinatore(int id_coordinatore)
    {
        this.id_coordinatore = id_coordinatore;
    }

    public int getSending_institute()
    {
        return sending_institute;
    }

    public void setSending_institute(int sending_institute)
    {
        this.sending_institute = sending_institute;
    }

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

    public String getRuolo() {
        return super.getRuolo();
    }

    public void setRuolo(String ruolo) {
        super.setRuolo("Coordinatore");
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

    public ArrayList<Studente> getStudente() {
        return studente;
    }

    public void setStudente(ArrayList<Studente> studente) {
        this.studente = studente;
    }

    public void addStudente(Studente studente)
    {
        this.studente.add(studente);
    }

    /**public void addLocalita(Localita localita)   <---- dubbio vedi sopra
    {
        this.localita.add(localita);
    }*/

    @Override
    public String toString() {
        return super.toString()+ "Coordinatore[ nome=" + nome + ", cognome" + cognome + "Sending_Institute=" + sending_institute + ", Studente=" + studente + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(this.getClass() != obj.getClass())
            return false;
        Coordinatore cord = (Coordinatore)obj;
        return this.equals(cord);
    }

    @Override
    protected Object clone() throws  CloneNotSupportedException
    {
        try {


            Coordinatore b = (Coordinatore) super.clone();

            b.setId(this.getId());
            b.setEmail(this.getEmail());
            b.setPassword(this.getPassword());
            b.setRuolo(this.getRuolo());
            b.setNome(this.nome);
            b.setCognome(this.cognome);
            //b.setLocalita(this.localita); <---- dubbio vedi sopra
            b.setStudente(this.studente);

            return b;
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }

}
