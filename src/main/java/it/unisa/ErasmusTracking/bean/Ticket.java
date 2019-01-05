package main.java.it.unisa.ErasmusTracking.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.lang.String;
import java.util.*;


public class Ticket {
    private int id;
    private String object;
    private int mittente;
    private int destinatario;
    private boolean stato;
    private String datacreazione;




    public Ticket(int ticket_id,String object,int mittente, int destinatario, boolean stato, String datacreazione){
        this.id= ticket_id;
        this.object= object;
        this.datacreazione= datacreazione;
        this.mittente=mittente;
        this.destinatario=destinatario;
        this.stato=stato;

    }
    public Ticket(){

    }
    public int getId(){return id;}
    public String getObject(){return object;}
    public int getMittente(){return mittente;}
    public int getDestinatario(){return destinatario;}
    public boolean getStato(){return stato;}
    public String getDataCreazione(){
        return datacreazione;
    }
    public void setId(int newId) {id=newId;}
    public void setObject(String newObject){object=newObject;}
    public void setMittente(int newMittente){mittente=newMittente;}
    public void setDestinatario(int NewDestinatario){destinatario=NewDestinatario;}
    public boolean setStato(boolean NewStato){return stato=NewStato;}
    public void setDatacreazione(String newData) {
        this.datacreazione= newData;
    }

}
