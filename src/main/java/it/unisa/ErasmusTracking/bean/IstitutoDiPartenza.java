package main.java.it.unisa.ErasmusTracking.bean;

public class IstitutoDiPartenza {
    private int id;
    private int codiceErasmus;
    private String indirizzo;
    private String dipartimento;

    public IstitutoDiPartenza(int codiceErasmus, String indirizzo, String dipartimento) {
        this.codiceErasmus = codiceErasmus;
        this.indirizzo = indirizzo;
        this.dipartimento = dipartimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCodiceErasmus() {
        return codiceErasmus;
    }

    public void setCodiceErasmus(int codiceErasmus) {
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
