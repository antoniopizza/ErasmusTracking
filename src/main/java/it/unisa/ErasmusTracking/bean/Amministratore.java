package main.java.it.unisa.ErasmusTracking.bean;

public class Amministratore extends Account {


  public Amministratore(int id, String nome, String cognome,
                        int idAmministratore, String ruolo,
                        String email, String password) {
    super(id,nome,cognome, email,ruolo, password);
  }

  public Amministratore() {

  }

  public String getNome() {
    return super.getNome();
  }

  public void setNome(String nome) {
    super.setNome(nome);
  }

  public String getCognome() {
    return super.getCognome();
  }

  public void setCognome(String cognome) {
    super.setCognome(cognome);
  }

  public int getId() {
    return super.getId();
  }

  public void setId(int newId) {
    super.setId(newId);
  }

  public String getRuolo() {
    return super.getRuolo();
  }

  public void setRuolo(String ruolo) {
    super.setRuolo("Amministratore");
  }

  public String getEmail() {
    return super.getEmail();
  }

  public void setEmail(String email) {
    super.setEmail(email);
  }

  public String getPassword() {
    return super.getPassword();
  }

  public void setPassword(String password) {
    super.setPassword(password);
  }

  @Override
  public String toString() {
    return super.toString() + "\n"
        +
        " Ruolo = Amministratore" + "\n";
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
    Amministratore adm = (Amministratore)obj;
    return this.equals(adm);
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {
      Amministratore b = (Amministratore) super.clone();

      b.setRuolo(this.getRuolo());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();

      return null;
    }

  }
}
