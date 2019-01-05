package main.java.it.unisa.ErasmusTracking.bean;


/**
 * Questa classe è un bean per la gestione delle localita'
 *
 * @author Ripoli Federico
 * @version 0.1
 */


public class Localita
{

    /**
     * variabili d'istanza
     */
    private Integer id;
    private String citta;
    private String nazione;


    /**
     * Costruttore di Location
     * @param citta La citta' della location
     * @param nazione La nazione della location
     */
    public Localita(String citta, String nazione)
    {
        this.citta = citta;
        this.nazione = nazione;
    }

    /**
     Costruttore vuoto
     */
    public Localita()
    {
    }

    /**
     * @return string citta La citta' della location
     */

    public void setId(Integer id)
    {
        this.id = id;
    }


    public Integer getId()
    {
        return id;
    }

    public String getCitta() {
        return citta;
    }

    /**
     * Modifica la citta' della location
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     * @return string nazione La nazione della location
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * Modifica la nazione della location
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }


    @Override
    public String toString()
    {
        return "Id= " + id + "\n"+
                "Città= "+ citta + "\n"+
                "Nazione= "+ nazione + "\n";
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

        Localita loc = (Localita) obj;

        if(this.getId()==(loc.getId())&&
                this.getCitta().equals(loc.getCitta()) &&
                this.getNazione().equals(loc.getNazione()))
        {
            return true;
        }
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        try
        {

            Localita b = (Localita) super.clone();

            b.setId(this.getId());
            b.setCitta(this.getCitta());
            b.setNazione(this.getNazione());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }

}
