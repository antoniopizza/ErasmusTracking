package main.java.it.unisa.ErasmusTracking.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.lang.String;
import java.util.Date;

public class Ticket {
    private int id;
    private String object;
    private int mittente;
    private int destinatario;
    private boolean stato;
    private Date datacreazione;
    private Date data_invio;
    private DateFormat data;
    private Date dataString;



    public Ticket(int ticket_id,String object,int mittente, int destinatario,boolean stato, Date dava_invio){
        this.id= ticket_id;
        this.object= object;
        dataString = datacreazione;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.datacreazione= (Date) data.parse(String.valueOf(dataString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
    public String getDataCreazione(){return data.format(datacreazione);}
    public void setId(int newId) {id=newId;}
    public void setObject(String newObject){object=newObject;}
    public void setMittente(int newMittente){mittente=newMittente;}
    public void setDestinatario(int NewDestinatario){destinatario=NewDestinatario;}
    public void setStato(boolean NewStato){stato=NewStato;}

    public void setDatacreazione(Date newData) {
        dataString=newData;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.datacreazione= (Date) data.parse(String.valueOf(dataString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}