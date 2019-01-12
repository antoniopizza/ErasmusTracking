package main.java.it.unisa.erasmustracking.bean;


import java.text.DateFormat;
import java.util.ArrayList;

public class LearningAgreement
{

    private int id;
    private String dataInizio;
    private String dataFine;
    private String stato;
    private String tipologiaErasmus;
    private ArrayList<MappingEsame> listaEsami;
    private Studente studente;
    private String conoscenzaLingua;
    private String matricolaStudente;

    private ReceivingInstitute receivingInstitute;
    private SendingInstitute sendingInstitute;

    public LearningAgreement(int id,String dataInizio, String dataFine,String stato,
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

    @Override
    public String toString()
    {
        return "id= " + id + "\n"+
                "data di inizio= "+ dataInizio + "\n"+
                "data di fine= "+ dataFine + "\n"+
                "Stato= "+ stato + "\n"+
                "Tipologia di Erasmus= " + tipologiaErasmus + "\n" +
                "Lista degli Esami= " + listaEsami + "\n"+
                "Studente= "+ studente + "\n"+
                "Conoscenza della Lingua= "+ conoscenzaLingua + "\n"+
                "Matricola= "+ matricolaStudente + "\n";
    }


    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
            return false;

        LearningAgreement acc = (LearningAgreement) obj;

        if(this.getId()==(acc.getId())&&
                this.getDataInizio().equals(acc.getDataFine()) &&
                this.getDataFine().equals(acc.getDataFine())&&
                this.getStato()==(acc.getStato()) &&
                this.getTipologiaErasmus().equals(acc.getTipologiaErasmus())&&
                this.getListaEsami().equals(acc.getListaEsami()) &&
                this.getStudente().equals(acc.getStudente())&&
                this.getConoscenzaLingua()==(acc.getConoscenzaLingua()) &&
                this.getMatricolaStudente().equals(acc.getMatricolaStudente()))
        {
            return true;
        }
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        try
        {

            LearningAgreement b = (LearningAgreement) super.clone();

            b.setId(this.getId());
            b.setDataInizio(this.getDataInizio());
            b.setDataFine(this.getDataFine());
            b.setStato(this.getStato());
            b.setTipologiaErasmus(this.getTipologiaErasmus());
            b.setListaEsami(this.getListaEsami());
            b.setStudente(this.getStudente());
            b.setConoscenzaLingua(this.getConoscenzaLingua());
            b.setMatricolaStudente(this.getMatricolaStudente());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }

}
