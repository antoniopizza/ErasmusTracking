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
    private String nome;
    private String codiceErasmus;


    /**
     * costruttore di localita'
     * @param citta
     * @param nazione
     * @param nome
     * @param codiceErasmus
     */
    public Localita(String citta, String nazione, String nome,String codiceErasmus)
    {
        this.citta = citta;
        this.nazione = nazione;
        this.nome = nome;
        this.codiceErasmus=codiceErasmus;
    }

    /**
     Costruttore vuoto
     */
    public Localita()
    {
    }

    /**
     * modifica l'id dell'istituto
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return id L'id dell'istituto
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @return string citta La citta' dell'istituto
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Modifica la citta' dell'istituto
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     * @return string nazione La nazione dell'istituto
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * Modifica la nazione dell'istituto
     * @param nazione
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /**
     * @return string nome Il nome dell'istituto
     */
    public String getNome() {
        return nome;
    }

    /**
     * modifica il nome dell'istituto
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return codiceErrasmus
     */
    public String getCodiceErasmus() {
        return codiceErasmus;
    }

    /**
     * modifica il codice Erasmus dell'istituto
     * @param codiceErasmus
     */
    public void setCodiceErasmus(String codiceErasmus) {
        this.codiceErasmus = codiceErasmus;
    }

    @Override
    public String toString()
    {
        return "Id= " + id + "\n" +
                "CodiceErasmus= " + codiceErasmus + "\n" +
                "Nome= " + nome + "\n" +
                "Città= "+ citta + "\n" +
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
                this.getNazione().equals(loc.getNazione()) &&
                this.getCodiceErasmus().equals(loc.getCodiceErasmus()) &&
                this.getNome().equals(loc.getNome()))
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
