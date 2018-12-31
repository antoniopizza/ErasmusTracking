package main.java.it.unisa.ErasmusTracking.bean;

public class MappingEsame {

    private int id;
    private Esame esameInterno;
    private Esame esameEsterno;

    public MappingEsame(Esame esameInterno, Esame esameEsterno) {
        this.esameInterno = esameInterno;
        this.esameEsterno = esameEsterno;
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

}
