package main.java.it.unisa.ErasmusTracking.bean;

public class MobilitaErasmus {

    private int id;
    private String dataInizio;
    private String dataFine;
    private String stato;
    private SendingInstitute sendingInstitute;
    private ReceivingInstitute receivingInstitute;
    private int learningAgreement;


    public MobilitaErasmus(int id, String dataInizio, String dataFine, String stato, SendingInstitute sendingInstitute, ReceivingInstitute receivingInstitute, int learningAgreement) {
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.stato = stato;
        this.sendingInstitute = sendingInstitute;
        this.receivingInstitute = receivingInstitute;
        this.learningAgreement = learningAgreement;
    }

    public MobilitaErasmus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public SendingInstitute getSendingInstitute() {
        return sendingInstitute;
    }

    public void setSendingInstitute(SendingInstitute sendingInstitute) {
        this.sendingInstitute = sendingInstitute;
    }

    public ReceivingInstitute getReceivingInstitute() {
        return receivingInstitute;
    }

    public void setReceivingInstitute(ReceivingInstitute receivingInstitute) {
        this.receivingInstitute = receivingInstitute;
    }

    public int getLearningAgreement() {
        return learningAgreement;
    }

    public void setLearningAgreement(int learningAgreement) {
        this.learningAgreement = learningAgreement;
    }


}
