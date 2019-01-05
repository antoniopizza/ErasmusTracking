package main.java.it.unisa.ErasmusTracking.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;


public class Messaggio_Ticket {
    private int id_messaggio;
    private String contenuto;
    private String data_invio;
    private String ora_invio;
    private int ticket_id;      // ticket(id del ticket)
    private int proprietario; // account(id_account)


    public Messaggio_Ticket(int id_messaggio, String contenuto, String data_invio, String ora_invio, int ticket_id, int proprietario) {
        this.id_messaggio = id_messaggio;
        this.contenuto = contenuto;
        this.data_invio = data_invio;
        this.ticket_id = ticket_id;
        this.proprietario = proprietario;
        this.ora_invio = ora_invio;
    }

    public Messaggio_Ticket(){
    }
        public int getId_messaggio () {return id_messaggio;}
        public String getContenuto () {return contenuto;}
        public String getDataInvio () {return data_invio;}
        public String getOraInvio () {return ora_invio;}
        public int getTicket_id () {return ticket_id;}
        public int getProprietario () {return proprietario;}
        public void setId_messaggio (int NewId_messaggio){id_messaggio=NewId_messaggio;}
        public void  setContenuto (String NewContenuto){contenuto=NewContenuto;}
        public void setData_invio(String data_invio){
            this.data_invio = data_invio;
        }
        public void setOra_invio(String ora_invio){
            this.ora_invio = ora_invio;
        }
        public void setTicket_id(int NewId_Ticket){ticket_id=NewId_Ticket;}
        public void setProprietario(int NewProprietario){proprietario=NewProprietario;}
    }

