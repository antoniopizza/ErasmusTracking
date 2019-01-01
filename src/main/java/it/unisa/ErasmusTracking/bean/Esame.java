package main.java.it.unisa.ErasmusTracking.bean;

public class Esame {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Esame(String nome, String codice, int ECTS, String semestre) {
        this.nome = nome;
        this.codice = codice;
        this.ECTS = ECTS;
        this.semestre = semestre;
    }

    private int id;
    private String nome;
    private String codice;
    private int ECTS;
    private String semestre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }


}
