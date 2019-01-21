package main.java.it.unisa.ErasmusTracking.bean;

import java.io.InputStream;
import java.io.OutputStream;

public class Documenti {

  private int id;
  private String nome;
  private String dataCaricamento;

  private int proprietario;
  private int fileSize;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  private String url;



  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }

  /**
   * Documenti.
   *
   * @param id
   *
   * @param nome
   *
   * @param dataCaricamento
   *
   *
   * @param proprietario
   *
   */

  public Documenti(int id, String nome, String dataCaricamento, int proprietario) {
    this.id = id;
    this.nome = nome;
    this.dataCaricamento = dataCaricamento;
    this.proprietario = proprietario;
  }

  public Documenti() {

  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDataCaricamento() {
    return dataCaricamento;
  }



  public int getProprietario() {
    return proprietario;
  }

  public void setId(int newId) {
    id = newId;
  }

  public void setNome(String newNome) {
    nome = newNome;
  }

  public void setDataCaricamento(String newData) {
    dataCaricamento = newData;

  }



  public void setProprietario(int newProprietario) {
    proprietario = newProprietario;
  }

  @Override
  public String toString() {
    return "id= " + id + "\n"
        +
        "nome= " + nome + "\n"
        +
        "data= " + dataCaricamento + "\n"

        +
        "proprietario= " + proprietario + "\n"
        +
        "url " + url + "\n";
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

    Documenti acc = (Documenti)obj;

    if (this.getId() == (acc.getId())
        &&
        this.getNome().equals(acc.getNome())
        &&
        this.getDataCaricamento().equals(acc.getDataCaricamento())
        &&
        this.getProprietario() == (acc.getProprietario())
        &&
        this.getUrl() == (acc.getUrl())) {
      return true;
    }
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {

      Documenti b = (Documenti) super.clone();

      b.setId(this.getId());
      b.setNome(this.getNome());
      b.setDataCaricamento(this.getDataCaricamento());
      b.setProprietario(this.getProprietario());
      b.setFileSize(this.getFileSize());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();

      return null;
    }

  }
}
