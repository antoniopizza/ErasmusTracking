package main.java.it.unisa.ErasmusTracking.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;


public class MessaggioTicket {

    private DateFormat data;
    private String dataString;
    private int id_messaggio;
    private String contenuto;
    private Date data_invio;//data e ora insieme
    private int ticket_id;      // ticket(id del ticket)
    private int proprietario; // account(id_account)


    public MessaggioTicket(int id_messaggio, String contenuto, String data_invio, int ticket_id, int proprietario) {
        this.id_messaggio = id_messaggio;
        this.contenuto = contenuto;
        dataString = data_invio;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.data_invio = (data.parse((dataString)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.ticket_id = ticket_id;
        this.proprietario = proprietario;
    }

    public MessaggioTicket(){
    }
        public int getId_messaggio () {return id_messaggio;}
        public String getContenuto () {return contenuto;}
        public String getdatainvio () {return data.format(data_invio);}
        public int getTicket_id () {return ticket_id;}
        public int getProprietario () {return proprietario;}
        public void setId_messaggio (int NewId_messaggio){id_messaggio=NewId_messaggio;}
        public void  setContenuto (String NewContenuto){contenuto=NewContenuto;}
        public void setData_invio(String NewData_invio){
            dataString = NewData_invio;
            data = DateFormat.getDateInstance(DateFormat.SHORT);
            try { this.data_invio= (data.parse((dataString)));
            } catch (ParseException e) { e.printStackTrace(); }
    }
        public void setTicket_id(int NewId_Ticket){ticket_id=NewId_Ticket;}
        public void setProprietario(int NewProprietario){proprietario=NewProprietario;}
    }

