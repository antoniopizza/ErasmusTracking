package main.java.it.unisa.ErasmusTracking.bean;


import java.text.DateFormat;
import java.util.ArrayList;

public class LearningAgreement
{

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

    public LearningAgreement(int id,DateFormat dataInizio, DateFormat dataFine,String stato,
                             String tipologiaErasmus,ArrayList<MappingEsame> listaEsami,Studente studente,
                             String conoscenzaLingua,String matricolaStudente,ReceivingInstitute receivingInstitute,
                             SendingInstitute sendingInstitute)
    {
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.stato = stato;
        this.tipologiaErasmus = tipologiaErasmus;
        this.listaEsami = listaEsami;
        this.studente = studente;
        this.conoscenzaLingua = conoscenzaLingua;
        this.matricolaStudente = matricolaStudente;
        this.receivingInstitute = receivingInstitute;
        this.sendingInstitute =  sendingInstitute;
    }
    public LearningAgreement()
    {
    }

    public int getId()
    {
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
