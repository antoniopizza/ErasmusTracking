package main.java.it.unisa.ErasmusTracking.bean;

public class SendingInstitution {
    private int id;
    private String codiceErasmus;
    private String indirizzo;
    private String dipartimento;

    public SendingInstitution(String codiceErasmus, String indirizzo, String dipartimento) {
        this.codiceErasmus = codiceErasmus;
        this.indirizzo = indirizzo;
        this.dipartimento = dipartimento;
    }
    public SendingInstitution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCodiceErasmus() {
        return codiceErasmus;
    }

    public void setCodiceErasmus(String codiceErasmus) {
        this.codiceErasmus = codiceErasmus;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }
}
