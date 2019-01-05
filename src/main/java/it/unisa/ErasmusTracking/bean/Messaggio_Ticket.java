package main.java.it.unisa.ErasmusTracking.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;


public class Messaggio_Ticket
{
    private int id_messaggio;
    private String contenuto;
    private String data_invio;
    private String ora_invio;
    private int ticket_id;      // ticket(id del ticket)
    private int proprietario; // account(id_account)


    public Messaggio_Ticket(int id_messaggio, String contenuto, String data_invio, String ora_invio, int ticket_id, int proprietario)
    {
        this.id_messaggio = id_messaggio;
        this.contenuto = contenuto;
        this.data_invio = data_invio;
        this.ticket_id = ticket_id;
        this.proprietario = proprietario;
        this.ora_invio = ora_invio;
    }

    public Messaggio_Ticket()
    {
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


    @Override
    public String toString()
    {
        return "id del messaggio= " + id_messaggio + "\n"+
                "Contenuto= "+ contenuto+ "\n"+
                "Data di Invio= "+ data_invio+ "\n"+
                "Ora di Invio= "+ data_invio+ "\n"+
                "Id del ticket= " + ticket_id+ "\n" +
                "Proprietario= " + proprietario+ "\n";
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

        Messaggio_Ticket acc = (Messaggio_Ticket) obj;

        if(this.getId_messaggio()==(acc.getId_messaggio())&&
                this.getContenuto().equals(acc.getContenuto()) &&
                this.getDataInvio().equals(acc.getDataInvio())&&
                this.getOraInvio().equals(acc.getOraInvio()) &&
                this.getTicket_id()==acc.getTicket_id()&&
                this.getProprietario()==acc.getProprietario())
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

            Messaggio_Ticket b = (Messaggio_Ticket) super.clone();

            b.setId_messaggio(this.getId_messaggio());
            b.setContenuto(this.getContenuto());
            b.setData_invio(this.getDataInvio());
            b.setOra_invio(this.getOraInvio());
            b.setTicket_id(this.getTicket_id());
            b.setProprietario(this.getProprietario());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}

