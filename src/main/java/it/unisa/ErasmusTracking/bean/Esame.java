package main.java.it.unisa.ErasmusTracking.bean;

public class Esame {
  private int id;
  private String nome;
  private String codice;
  private int creditiFormativi;
  private String semestre;

  /**
   * Esame.
   *
   * @param nome
   *
   * @param codice
   *
   * @param creditiFormativi
   *
   * @param semestre
   *
   */

  public Esame(String nome, String codice, int creditiFormativi, String semestre) {
    this.nome = nome;
    this.codice = codice;
    this.creditiFormativi = creditiFormativi;
    this.semestre = semestre;
  }

  public Esame() { }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

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

  public int getCreditiFormativi() {
    return creditiFormativi;
  }

  public void setCreditiFormativi(int creditiFormativi) {
    this.creditiFormativi = creditiFormativi;
  }

  public String getSemestre() {
    return semestre;
  }

  public void setSemestre(String semestre) {
    this.semestre = semestre;
  }

  @Override
  public String toString() {
    return "id= " + id + "\n"
        +
        "nome esame= " + nome + "\n"
        +
        "codice esame= " + codice + "\n"
        +
        "creditiFormativi= " + creditiFormativi + "\n"
        +
        "Semestre= " + semestre + "\n";
  }


  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Esame acc = (Esame) obj;

    if (this.getId() == (acc.getId())
        &&
        this.getNome().equals(acc.getNome())
        &&
        this.getCodice().equals(acc.getCodice())
        &&
        this.getCreditiFormativi() == (acc.getCreditiFormativi())
        &&
        this.getSemestre().equals(acc.getSemestre())) {
      return true;
    }
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {

      Esame b = (Esame) super.clone();

      b.setId(this.getId());
      b.setNome(this.getNome());
      b.setCodice(this.getCodice());
      b.setCreditiFormativi(this.getCreditiFormativi());
      b.setSemestre(this.getSemestre());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }

  }
}
