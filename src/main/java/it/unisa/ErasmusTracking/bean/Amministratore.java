package main.java.it.unisa.ErasmusTracking.bean;

public class Amministratore extends Account
{
    private int id_amministratore;

    public Amministratore(int id,String nome,String cognome,int id_amministratore ,String ruolo, String email, String password)
    {
        super(id,nome,cognome, email,ruolo, password);
        this.id_amministratore = id_amministratore;
    }

    public Amministratore()
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

    public int getId(){
        return super.getId();
    }

    public void setId(int newId){
        super.setId(newId);
    }

    public int getId_amministratore()
    {
        return id_amministratore;
    }

    public void setId_amministratore(int id_amministratore)
    {
        this.id_amministratore = id_amministratore;
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
        return super.toString()+ "\n" +
                " Ruolo = Amministratore" + "\n" +
                "id_amministratore = " + id_amministratore + "\n";
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

            b.setRuolo(this.getRuolo());
            b.setId_amministratore(this.getId_amministratore());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}
