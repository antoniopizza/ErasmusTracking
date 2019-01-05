package main.java.it.unisa.ErasmusTracking.bean;

public class MappingEsame {

    private int id;
    private Esame esameInterno;
    private Esame esameEsterno;

    private String lingua;
    private String stato;
    private int learningAgreement;

    public MappingEsame(int id, int learningAgreement, Esame esameInterno, Esame esameEsterno, String lingua, String stato) {
        this.esameInterno = esameInterno;
        this.esameEsterno = esameEsterno;
        this.id=id;
        this.learningAgreement = learningAgreement;
        this.lingua = lingua;
        this.stato = stato;
    }

    public MappingEsame() {
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getLearningAgreement() {
        return learningAgreement;
    }

    public void setLearningAgreement(int learningAgreement) {
        this.learningAgreement = learningAgreement;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Esame getEsameInterno() {
        return esameInterno;
    }

    public void setEsameInterno(Esame esameInterno) {
        this.esameInterno = esameInterno;
    }

    public Esame getEsameEsterno() {
        return esameEsterno;
    }

    public void setEsameEsterno(Esame esameEsterno) {
        this.esameEsterno = esameEsterno;
    }

}
