package main.java.it.unisa.ErasmusTracking.bean;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class LearningAgreement {

    private int id;
    private DateFormat dataInizio;
    private DateFormat dataFine;
    private String stato;
    private String tipologiaErasmus;
    private ArrayList<MappingEsame> listaEsami;

    private String lingua;
    private String conoscenzaLingua;

    private IstitutoRicevente istitutoRicevente;
    private IstitutoDiPartenza istitutoDiPartenza;

    public LearningAgreement(DateFormat dataInizio, DateFormat dataFine, String tipologiaErasmus) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipologiaErasmus = tipologiaErasmus;
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

    public IstitutoRicevente getIstitutoRicevente() {
        return istitutoRicevente;
    }

    public void setIstitutoRicevente(IstitutoRicevente istitutoRicevente) {
        this.istitutoRicevente = istitutoRicevente;
    }

    public IstitutoDiPartenza getIstitutoDiPartenza() {
        return istitutoDiPartenza;
    }

    public void setIstitutoDiPartenza(IstitutoDiPartenza istitutoDiPartenza) {
        this.istitutoDiPartenza = istitutoDiPartenza;
    }

}
