package main.java.it.unisa.ErasmusTracking.bean;

public class Amministratore extends Account
{


    public Amministratore(int id, String nome, String cognome, String email, String password)
    {
        super(id, nome, cognome, email, password);
    }

    public String getNome()
    {
        return super.getNome();
    }

    public void setNome(String nome)
    {
        super.setNome(nome);
    }

    public String getCognome()
    {
        return super.getCognome();

    }

    public void setCognome (String cognome)
    {
        super.setCognome(cognome);
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
        return super.toString()+ " Ruolo = Amministratore";
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
            b.setNome(this.getNome());
            b.setCognome(this.getCognome());
            b.setEmail(this.getEmail());
            b.setPassword(this.getPassword());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}
