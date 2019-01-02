package main.java.it.unisa.ErasmusTracking.bean;

import java.util.ArrayList;

public class Coordinatore extends Account {
    private String nome;
    private String cognome;
    private ArrayList<Localita> localita;
    private ArrayList<Studente> studente;

    public Coordinatore( int id, String nome, String cognome, String email, String password, Localita localita, Studente studente) {
        super(id, email, password);
        this.cognome=cognome;
        this.nome = nome;
        this.localita = new ArrayList<Localita>();
        this.studente = new ArrayList<Studente>();
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
        return super.toString()+ "Coordinatore[ nome=" + nome + ", cognome" + cognome + "Località=" + localita + ", Studente=" + studente + "]";
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
            b.setNome(this.nome);
            b.setCognome(this.cognome);
            b.setLocalita(this.localita);
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
