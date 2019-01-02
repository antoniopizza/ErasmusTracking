package main.java.it.unisa.ErasmusTracking.bean;

public class Amministratore extends Account
{
    private String nome;
    private String cognome;


    public Amministratore(int id, String nome, String cognome, String email, String password)
    {
        super(id, email, password);
        this.cognome=cognome;
        this.nome = nome;

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
