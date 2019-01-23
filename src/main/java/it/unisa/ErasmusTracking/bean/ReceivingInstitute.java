package main.java.it.unisa.ErasmusTracking.bean;

public class ReceivingInstitute {

  private int id;
  private int localita;
  private String nomeContatto;
  private String emailContatto;
  private String sizeOfEnterprise;
  private String nomeMentore;
  private String emailMentore;
  private String website;

  /**
   * RecivingInstitue.
   *
   * @param nomeContatto
   *
   * @param emailContatto
   *
   * @param sizeOfEnterprise
   *
   * @param nomeMentore
   *
   * @param emailMentore
   *
   * @param website
   *
   * @param localita
   *
   */
  public ReceivingInstitute(String nomeContatto, String emailContatto,
                            String sizeOfEnterprise, String nomeMentore,
                            String emailMentore, String website, int localita) {
    this.nomeContatto = nomeContatto;
    this.emailContatto = emailContatto;
    this.sizeOfEnterprise = sizeOfEnterprise;
    this.nomeMentore = nomeMentore;
    this.emailMentore = emailMentore;
    this.website = website;
    this.localita = localita;
  }

  public ReceivingInstitute() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLocalita() {
    return localita;
  }

  public void setLocalita(int localita) {
    this.localita = localita;
  }

  public String getNomeContatto() {
    return nomeContatto;
  }

  public void setNomeContatto(String nomeContatto) {
    this.nomeContatto = nomeContatto;
  }

  public String getEmailContatto() {
    return emailContatto;
  }

  public void setEmailContatto(String emailContatto) {
    this.emailContatto = emailContatto;
  }

  public String getSizeOfEnterprise() {
    return sizeOfEnterprise;
  }

  public void setSizeOfEnterprise(String sizeOfEnterprise) {
    this.sizeOfEnterprise = sizeOfEnterprise;
  }

  public String getNomeMentore() {
    return nomeMentore;
  }

  public void setNomeMentore(String nomeMentore) {
    this.nomeMentore = nomeMentore;
  }

  public String getEmailMentore() {
    return emailMentore;
  }

  public void setEmailMentore(String emailMentore) {
    this.emailMentore = emailMentore;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  @Override
  public String toString() {
    return "id= " + id + "\n"
        +
        "Località= " + localita + "\n"
        +
        "Nome del Contatto= " + nomeContatto + "\n"
        +
        "E-mail del contatto= " + emailContatto + "\n"
        +
        "Grandezza dell'istituto= " + sizeOfEnterprise + "\n"
        +
        "Nome del mentore= " + nomeMentore + "\n"
        +
        "E-mail del mentore= " + emailMentore + "\n"
        +
        "Sito web= " + website + "\n";
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

    ReceivingInstitute acc = (ReceivingInstitute) obj;

    if (this.getId() == (acc.getId())
        &&
        this.getLocalita() == acc.getLocalita()
        &&
        this.getNomeContatto().equals(acc.getNomeContatto())
        &&
        this.getEmailContatto().equals(acc.getEmailContatto())
        &&
        this.getSizeOfEnterprise().equals(acc.getSizeOfEnterprise())
        &&
        this.getNomeMentore().equals(acc.getNomeMentore())
        &&
        this.getEmailMentore().equals(acc.getEmailMentore())
        &&
        this.getWebsite().equals(acc.getWebsite())) {
      return true;
    }
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {

      ReceivingInstitute b = (ReceivingInstitute) super.clone();

      b.setId(this.getId());
      b.setLocalita(this.getLocalita());
      b.setNomeContatto(this.getNomeContatto());
      b.setEmailContatto(this.getEmailContatto());
      b.setSizeOfEnterprise(this.getSizeOfEnterprise());
      b.setNomeMentore(this.getNomeMentore());
      b.setEmailMentore(this.getEmailMentore());
      b.setWebsite(this.getWebsite());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }
}
