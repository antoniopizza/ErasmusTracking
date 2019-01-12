package main.java.it.unisa.erasmustracking.bean;

import java.lang.String;


public class Ticket {
    private int id;
    private String object;
    private int mittente;
    private int destinatario;
    private String stato;
    private String datacreazione;
    private String nomeMittente;
    private String nomeDestinatario;




    public Ticket(int ticket_id,String object,int mittente, int destinatario, String stato, String datacreazione){
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
    public String getStato(){return stato;}
    public String getDataCreazione(){
        return datacreazione;
    }
    public String getNomeMittente(){return nomeMittente;}
    public String getNomeDestinatario() {return nomeDestinatario;}
    public void setId(int newId) {id=newId;}
    public void setObject(String newObject){object=newObject;}
    public void setMittente(int newMittente){mittente=newMittente;}
    public void setDestinatario(int NewDestinatario){destinatario=NewDestinatario;}
    public void setStato(String NewStato){stato=NewStato;}
    public void setDatacreazione(String newData) {
        this.datacreazione= newData;
    }
    public void setNomeMittente(String nomeMittente) {this.nomeMittente = nomeMittente;}
    public void setNomeDestinatario(String nomeDestinatario) {this.nomeDestinatario = nomeDestinatario; }

    @Override
    public String toString()
    {
        return "id= " + id + "\n"+
                "Oggetto del messaggio= "+ object+ "\n"+
                "Mittente del ticket= "+ nomeMittente + "\n"+
                "Destinatario del ticket= "+ nomeDestinatario + "\n"+
                "Stato del ticket= " + stato+ "\n" +
                "Data di Creazione= " + datacreazione+ "\n";
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

        Ticket acc = (Ticket) obj;

        if(this.getId()==(acc.getId())&&
                this.getObject().equals(acc.getObject()) &&
                this.getNomeMittente().equals(acc.getNomeMittente()) &&
                this.getNomeDestinatario().equals(acc.getNomeDestinatario()) &&
                this.getMittente()==(acc.getMittente()) &&
                this.getDestinatario()==(acc.getDestinatario()) &&
                this.getStato().equals(acc.getStato()) &&
                this.getDataCreazione().equals(acc.getDataCreazione()))
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

            Ticket b = (Ticket) super.clone();

            b.setId(this.getId());
            b.setObject(this.getObject());
            b.setMittente(this.getMittente());
            b.setDestinatario(this.getDestinatario());
            b.setStato(this.getStato());
            b.setDatacreazione(this.getDataCreazione());
            b.setNomeMittente(this.nomeMittente);
            b.setNomeDestinatario(this.nomeDestinatario);

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }

}
