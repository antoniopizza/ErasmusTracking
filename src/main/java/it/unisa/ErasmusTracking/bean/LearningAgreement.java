package main.java.it.unisa.ErasmusTracking.bean;


import java.text.DateFormat;
import java.util.ArrayList;

public class LearningAgreement {

    private int id;
    private DateFormat dataInizio;
    private DateFormat dataFine;
    private String stato;
    private String tipologiaErasmus;
    private ArrayList<MappingEsame> listaEsami;
    private String studente;
    private String lingua;
    private String conoscenzaLingua;

    private ReceivingInstitute receivingInstitute;
    private SendingInstitution sendingInstitution;

    public LearningAgreement(DateFormat dataInizio, DateFormat dataFine, String tipologiaErasmus) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipologiaErasmus = tipologiaErasmus;
    }
    public LearningAgreement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateFormat getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(DateFormat dataInizio) {
        this.dataInizio = dataInizio;
    }

    public DateFormat getDataFine() {
        return dataFine;
    }

    public void setDataFine(DateFormat dataFine) {
        this.dataFine = dataFine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipologiaErasmus() {
        return tipologiaErasmus;
    }

    public void setTipologiaErasmus(String tipologiaErasmus) {
        this.tipologiaErasmus = tipologiaErasmus;
    }

    public ArrayList<MappingEsame> getListaEsami() {
        return listaEsami;
    }

    public void setListaEsami(ArrayList<MappingEsame> listaEsami) {
        this.listaEsami = listaEsami;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getConoscenzaLingua() {
        return conoscenzaLingua;
    }

    public void setConoscenzaLingua(String conoscenzaLingua) {
        this.conoscenzaLingua = conoscenzaLingua;
    }

    public ReceivingInstitute getReceivingInstitute() {
        return receivingInstitute;
    }

    public void setReceivingInstitute(ReceivingInstitute receivingInstitute) {
        this.receivingInstitute = receivingInstitute;
    }

    public SendingInstitution getSendingInstitution() {
        return sendingInstitution;
    }

    public void setSendingInstitution(SendingInstitution sendingInstitution) {
        this.sendingInstitution = sendingInstitution;
    }
    public String getStudente() {
        return studente;
    }

    public void setStudente(String studente) {
        this.studente = studente;
    }

}
