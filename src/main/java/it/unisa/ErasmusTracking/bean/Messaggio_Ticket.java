package main.java.it.unisa.ErasmusTracking.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;


public class Messaggio_Ticket {
    private  DateFormat data;
    private int id_messaggio;
    private String contenuto;
    private String data_invio;//data e ora insieme
    private int ticket_id;      // ticket(id del ticket)
    private int proprietario; // account(id_account)


    public Messaggio_Ticket(int id_messaggio, String contenuto, Date data_invio, int ticket_id, int proprietario) {
        this.id_messaggio = id_messaggio;
        this.contenuto = contenuto;
        Date dataString = data_invio;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.data_invio = String.valueOf(data.parse(String.valueOf(dataString)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.ticket_id = ticket_id;
        this.proprietario = proprietario;
    }

    public Messaggio_Ticket(){
    }
        public int getId_messaggio () {return id_messaggio;}
        public String getContenuto () {return contenuto;}
        public String getdatainvio () {return data.format(data_invio);}
        public int getTicket_id () {return ticket_id;}
        public int getProprietario () {return proprietario;}
        public void setId_messaggio (int NewId_messaggio){id_messaggio=NewId_messaggio;}
        public void  setContenuto (String NewContenuto){contenuto=NewContenuto;}
        public void setData_invio(Date NewData_invio){
         //   dataString=newData;
            data = DateFormat.getDateInstance(DateFormat.SHORT);
         //   try { this.datacreazione= String.valueOf(data.parse(String.valueOf(dataString)));
         //   } catch (ParseException e) { e.printStackTrace(); }
    }
        public void setTicket_id(int NewTicket_id){ticket_id=NewTicket_id;}
        public void setProprietario(int NewProprietario){proprietario=NewProprietario;}
    }

