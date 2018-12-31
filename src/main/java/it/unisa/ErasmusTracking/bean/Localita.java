package main.java.it.unisa.ErasmusTracking.bean;


/**
 * Questa classe è un bean per la gestione delle localita'
 *
 * @author Ripoli Federico
 * @version 0.1
 */
public class Localita {

    /**
     * variabili d'istanza
     */
    private String citta;
    private String nazione;

    /**
     * Costruttore di Location
     * @param citta La citta' della location
     * @param nazione La nazione della location
     */
    public Localita(String citta, String nazione) {
        this.citta = citta;
        this.nazione = nazione;
    }

    /**
     Costruttore vuoto
     */
    public Localita() {
    }

    /**
     * @return string citta La citta' della location
     */
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

    /**
     * @return toString
     * Override del metodo toString di Object
     */
    public String toString() {
        return "Location[citta=" + citta + ",nazione=" + nazione + "]";
    }

    /**
     * @param obj
     * @return boolean true Se i due le due locatita' sono uguali
     * Override del metodo equals di Object
     */
    public boolean equals(Object obj) {
        if(!super.equals(obj)) {
            return false;
        }

        Localita location = (Localita) obj;

        if (this.citta != location.getCitta()) {
            return false;
        }
        if (this.nazione != location.getNazione()) {
            return false;
        }

        return true;
    }

    /**
     * @return Object location La localita' clonata
     * Override del metodo clone di Object
     */
    public Object clone() {
        try {
            Localita location = (Localita) super.clone();

            location.setCitta(this.citta);
            location.setNazione(this.nazione);

            return location;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
