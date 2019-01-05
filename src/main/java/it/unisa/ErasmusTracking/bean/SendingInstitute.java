package main.java.it.unisa.ErasmusTracking.bean;

public class SendingInstitute {
    private int id;
    private String codiceErasmus;
    private String indirizzo;
    private String dipartimento;

    public SendingInstitute(String codiceErasmus, String indirizzo, String dipartimento) {
        this.codiceErasmus = codiceErasmus;
        this.indirizzo = indirizzo;
        this.dipartimento = dipartimento;
    }
    public SendingInstitute() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCodiceErasmus() {
        return codiceErasmus;
    }

    public void setCodiceErasmus(String codiceErasmus) {
        this.codiceErasmus = codiceErasmus;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    @Override
    public String toString()
    {
        return "id= " + id + "\n"+
                "Codice Erasmus= "+ codiceErasmus+ "\n"+
                "Indirizzo Istitute= "+ indirizzo + "\n"+
                "Dipartimento= "+ dipartimento + "\n";
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

        SendingInstitute acc = (SendingInstitute) obj;

        if(this.getId()==(acc.getId())&&
                this.getCodiceErasmus().equals(acc.getCodiceErasmus()) &&
                this.getIndirizzo().equals(acc.getIndirizzo())&&
                this.getDipartimento().equals(acc.getDipartimento()))
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

            SendingInstitute b = (SendingInstitute) super.clone();

            b.setId(this.getId());
            b.setCodiceErasmus(this.getCodiceErasmus());
            b.setIndirizzo(this.getIndirizzo());
            b.setDipartimento(this.getDipartimento());

            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }
}
