package main.java.it.unisa.ErasmusTracking.bean;

public class MobilitaErasmus {

  private int id;
  private String dataInizio;
  private String dataFine;
  private String stato;
  private SendingInstitute sendingInstitute;
  private ReceivingInstitute receivingInstitute;
  private int learningAgreement;

  /**
   * MobilitaErasmus.
   *
   * @param id
   *
   * @param dataInizio
   *
   * @param dataFine
   *
   * @param stato
   *
   * @param sendingInstitute
   *
   * @param receivingInstitute
   *
   * @param learningAgreement
   *
   */
  public MobilitaErasmus(int id, String dataInizio,
                         String dataFine, String stato,
                         SendingInstitute sendingInstitute,
                         ReceivingInstitute receivingInstitute,
                         int learningAgreement) {
    this.id = id;
    this.dataInizio = dataInizio;
    this.dataFine = dataFine;
    this.stato = stato;
    this.sendingInstitute = sendingInstitute;
    this.receivingInstitute = receivingInstitute;
    this.learningAgreement = learningAgreement;
  }

  public MobilitaErasmus() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDataInizio() {
    return dataInizio;
  }

  public void setDataInizio(String dataInizio) {
    this.dataInizio = dataInizio;
  }

  public String getDataFine() {
    return dataFine;
  }

  public void setDataFine(String dataFine) {
    this.dataFine = dataFine;
  }

  public String getStato() {
    return stato;
  }

  public void setStato(String stato) {
    this.stato = stato;
  }

  public SendingInstitute getSendingInstitute() {
    return sendingInstitute;
  }

  public void setSendingInstitute(SendingInstitute sendingInstitute) {
    this.sendingInstitute = sendingInstitute;
  }

  public ReceivingInstitute getReceivingInstitute() {
    return receivingInstitute;
  }

  public void setReceivingInstitute(ReceivingInstitute receivingInstitute) {
    this.receivingInstitute = receivingInstitute;
  }

  public int getLearningAgreement() {
    return learningAgreement;
  }

  public void setLearningAgreement(int learningAgreement) {
    this.learningAgreement = learningAgreement;
  }

  @Override
  public String toString() {
    return "id= " + id + "\n"
        +
        "Data di Inizio= " + dataInizio + "\n"
        +
        "Data di Fine= " + dataFine + "\n"
        +
        "Stato= " + stato + "\n"
        +
        "Sending Institute= " + sendingInstitute + "\n"
        +
        "Receiving Institute= " + receivingInstitute + "\n"
        +
        "Learning Agreement= " + learningAgreement + "\n";
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

    MobilitaErasmus acc = (MobilitaErasmus) obj;

    if ((this.getId() == (acc.getId()))
        &&
        this.getDataInizio().equals(acc.getDataInizio())
        &&
        this.getDataFine().equals(acc.getDataFine())
        &&
        this.getStato().equals(acc.getStato())
        &&
        this.getSendingInstitute().equals(acc.getSendingInstitute())
        &&
        this.getReceivingInstitute().equals(acc.getReceivingInstitute())
        &&
        this.getLearningAgreement() == (acc.getLearningAgreement())) {
      return true;
    }
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {

      MobilitaErasmus b = (MobilitaErasmus) super.clone();

      b.setId(this.getId());
      b.setDataInizio(this.getDataInizio());
      b.setDataFine(this.getDataFine());
      b.setStato(this.getStato());
      b.setSendingInstitute(this.getSendingInstitute());
      b.setReceivingInstitute(this.getReceivingInstitute());
      b.setLearningAgreement(this.getLearningAgreement());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();

      return null;
    }

  }


}
