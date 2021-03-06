package main.java.it.unisa.ErasmusTracking.bean;

import java.util.ArrayList;

public class Coordinatore extends Account
{
    private int sending_institute;
    private ArrayList<Studente> studente;
    private ArrayList<Localita> localita;

    public Coordinatore(String nome, String cognome,ArrayList<Localita> localita,String email,
                         String password, String ruolo,int sending_institute, Studente studente,int id)
    {
        super(id, nome,cognome,email, password, ruolo);

        this.sending_institute = sending_institute;
        this.studente = new ArrayList<Studente>();
        this.localita = new ArrayList<Localita>();
    }

    public Coordinatore()
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


    public ArrayList<Localita> getLocalita() {
        return localita;
    }

    public void setLocalita(ArrayList<Localita> localita) {
        this.localita = localita;
    }

    public int getSending_institute()
    {
        return sending_institute;
    }

    public void setSending_institute(int sending_institute)
    {
        this.sending_institute = sending_institute;
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

    public void addLocalita(Localita localita)
    {
        this.localita.add(localita);
    }

    @Override
    public String toString() {
        return super.toString() +
                "sending_institute= " + sending_institute + "\n" +
                "Lista Studenti= "+ studente + "\n" +
                "Lista Località=" + localita + "\n";
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

        if(super.equals(cord) &&
            this.getSending_institute()==cord.getSending_institute()&&
            this.getStudente().equals(cord.getStudente()) &&
            this.getLocalita().equals(cord.getLocalita()))
            return true;
        return false;
    }

    @Override
    protected Object clone() throws  CloneNotSupportedException
    {
        try {


            Coordinatore b = (Coordinatore) super.clone();

            b.setRuolo(this.getRuolo());
            b.setSending_institute(this.getSending_institute());
            b.setLocalita(this.getLocalita());
            b.setStudente(this.getStudente());

            return b;
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }

}
