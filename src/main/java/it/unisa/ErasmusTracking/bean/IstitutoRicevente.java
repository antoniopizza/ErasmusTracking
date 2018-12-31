package main.java.it.unisa.ErasmusTracking.bean;

public class IstitutoRicevente {

    private int id;
    private Localita localita;
    private String nomeContatto;
    private String emailContatto;
    private String sizeOfEnterprise;
    private String nomeMentore;
    private String emailMentore;
    private String website;

    public IstitutoRicevente(Localita localita) {
        this.localita = localita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Localita getLocalita() {
        return localita;
    }

    public void setLocalita(Localita localita) {
        this.localita = localita;
    }

    public String getNomeContatto() {
        return nomeContatto;
    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public String getEmailContatto() {
        return emailContatto;
    }

    public void setEmailContatto(String emailContatto) {
        this.emailContatto = emailContatto;
    }

    public String getSizeOfEnterprise() {
        return sizeOfEnterprise;
    }

    public void setSizeOfEnterprise(String sizeOfEnterprise) {
        this.sizeOfEnterprise = sizeOfEnterprise;
    }

    public String getNomeMentore() {
        return nomeMentore;
    }

    public void setNomeMentore(String nomeMentore) {
        this.nomeMentore = nomeMentore;
    }

    public String getEmailMentore() {
        return emailMentore;
    }

    public void setEmailMentore(String emailMentore) {
        this.emailMentore = emailMentore;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
