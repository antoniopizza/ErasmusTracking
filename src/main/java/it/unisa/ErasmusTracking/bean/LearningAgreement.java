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
    private Studente studente;
    private String conoscenzaLingua;
    private String matricolaStudente;

    private ReceivingInstitute receivingInstitute;
    private SendingInstitute sendingInstitute;

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

    public SendingInstitute getSendingInstitute() {
        return sendingInstitute;
    }

    public void setSendingInstitute(SendingInstitute sendingInstitute) {
        this.sendingInstitute = sendingInstitute;
    }
    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }


    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

}
