package main.java.it.unisa.ErasmusTracking.bean;

public class Amministratore extends Account
{
    private int id_amministratore;
    private String nome,cognome;


    public Amministratore(int id,int id_amministratore ,String nome, String cognome,String ruolo, String email, String password)
    {
        super(id, email,ruolo, password);
        this.id_amministratore = id_amministratore;
        this.cognome=cognome;
        this.nome = nome;

    }

    public Amministratore()
    {

    }

    public int getId_amministratore()
    {
        return id_amministratore;
    }

    public void setId_amministratore(int id_amministratore)
    {
        this.id_amministratore = id_amministratore;
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
        super.setRuolo("Amministratore");
    }

    public String getEmail()
    {
        return super.getEmail();
    }

    public void setEmail(String email)
    {
        super.setEmail(email);
    }

    public String getPassword()
    {
        return super.getPassword();
    }

    public void setPassword(String password)
    {
        super.setPassword(password);
    }

    @Override
    public String toString()
    {
        return super.toString()+ " Ruolo = Amministratore [ nome=" + nome + ", cognome" + cognome + "]";
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

        Amministratore adm = (Amministratore)obj;
        return this.equals(adm);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        try
        {
            Amministratore b = (Amministratore) super.clone();

            b.setId(this.getId());
            b.setEmail(this.getEmail());
            b.setPassword(this.getPassword());
            b.setRuolo(this.getRuolo());
            b.setNome(this.nome);
            b.setCognome(this.cognome);
            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}
