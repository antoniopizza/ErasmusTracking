package main.java.it.unisa.ErasmusTracking.bean;

public class MappingEsame
{

    private int id;
    private Esame esameInterno;
    private Esame esameEsterno;

    private String lingua;
    private String stato;
    private int learningAgreement;

    public MappingEsame(int id, int learningAgreement, Esame esameInterno, Esame esameEsterno, String lingua, String stato) {
        this.esameInterno = esameInterno;
        this.esameEsterno = esameEsterno;
        this.id=id;
        this.learningAgreement = learningAgreement;
        this.lingua = lingua;
        this.stato = stato;
    }

    public MappingEsame() {
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getLearningAgreement() {
        return learningAgreement;
    }

    public void setLearningAgreement(int learningAgreement) {
        this.learningAgreement = learningAgreement;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Esame getEsameInterno() {
        return esameInterno;
    }

    public void setEsameInterno(Esame esameInterno) {
        this.esameInterno = esameInterno;
    }

    public Esame getEsameEsterno() {
        return esameEsterno;
    }

    public void setEsameEsterno(Esame esameEsterno) {
        this.esameEsterno = esameEsterno;
    }


    @Override
    public String toString()
    {
        return "id= " + id + "\n"+
                "Esame Interno= "+ esameInterno + "\n"+
                "Esame Esterno= "+ esameEsterno + "\n"+
                "Lingua= "+ lingua+ "\n"+
                "Stato= " + stato+ "\n" +
                "Learning Agreement= " + learningAgreement+ "\n";
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

        MappingEsame acc = (MappingEsame) obj;

        if(this.getId()==(acc.getId())&&
                this.getEsameInterno().equals(acc.getEsameInterno()) &&
                this.getEsameEsterno().equals(acc.getEsameEsterno())&&
                this.getLingua().equals(acc.getLingua())&&
                this.getStato().equals(acc.getStato()) &&
                this.getLearningAgreement()==acc.getLearningAgreement())
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

            MappingEsame b = (MappingEsame) super.clone();

            b.setId(this.getId());
            b.setEsameInterno(this.getEsameInterno());
            b.setEsameEsterno(this.getEsameEsterno());
            b.setLingua(this.getLingua());
            b.setStato(this.getStato());
            b.setLearningAgreement(this.getLearningAgreement());


            return b;
        }

        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();

            return null;
        }

    }

}
