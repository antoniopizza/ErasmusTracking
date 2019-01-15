package main.java.it.unisa.ErasmusTracking.bean;


public class MessaggioTicket {
  private int idMessaggio;
  private String contenuto;
  private String dataInvio;
  private String oraInvio;
  private int ticketId;      // ticket(id del ticket)
  private int proprietario; // account(id_account)

  /**
   * MessaggioTicket.
   *
   * @param idMessaggio
   *
   * @param contenuto
   *
   * @param dataInvio
   *
   * @param oraInvio
   *
   * @param ticketId
   *
   * @param proprietario
   *
   */
  public MessaggioTicket(int idMessaggio, String contenuto,
                         String dataInvio, String oraInvio,
                         int ticketId, int proprietario) {

    this.idMessaggio = idMessaggio;
    this.contenuto = contenuto;
    this.dataInvio = dataInvio;
    this.ticketId = ticketId;
    this.proprietario = proprietario;
    this.oraInvio = oraInvio;
  }

  public MessaggioTicket() {
  }

  public int getIdMessaggio() {
    return idMessaggio;
  }

  public String getContenuto() {
    return contenuto;
  }

  public String getDataInvio() {
    return dataInvio;
  }

  public String getOraInvio() {
    return oraInvio;
  }

  public int getTicketId() {
    return ticketId;
  }

  public int getProprietario() {
    return proprietario;
  }

  public void setIdMessaggio(int newIdMessaggio) {
    idMessaggio = newIdMessaggio;
  }

  public void  setContenuto(String newContenuto) {
    contenuto = newContenuto;
  }

  public void setDataInvio(String dataInvio) {
    this.dataInvio = dataInvio;
  }

  public void setOraInvio(String oraInvio) {
    this.oraInvio = oraInvio;
  }

  public void setTicketId(int newIdTicket) {
    ticketId = newIdTicket;
  }

  public void setProprietario(int newProprietario) {
    proprietario = newProprietario;
  }


  @Override
  public String toString() {
    return "id del messaggio= " + idMessaggio + "\n"
        +
        "Contenuto= " + contenuto + "\n"
        +
        "Data di Invio= " + dataInvio + "\n"
        +
        "Ora di Invio= " + dataInvio + "\n"
        +
        "Id del ticket= " + ticketId + "\n"
        +
        "Proprietario= " + proprietario + "\n";
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

    MessaggioTicket acc = (MessaggioTicket) obj;

    if (this.getIdMessaggio() == (acc.getIdMessaggio())
        &&
        this.getContenuto().equals(acc.getContenuto())
        &&
        this.getDataInvio().equals(acc.getDataInvio())
        &&
        this.getOraInvio().equals(acc.getOraInvio())
        &&
        this.getTicketId() == acc.getTicketId()
        &&
        this.getProprietario() == acc.getProprietario()) {
      return true;
    }
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {

      MessaggioTicket b = (MessaggioTicket) super.clone();

      b.setIdMessaggio(this.getIdMessaggio());
      b.setContenuto(this.getContenuto());
      b.setDataInvio(this.getDataInvio());
      b.setOraInvio(this.getOraInvio());
      b.setTicketId(this.getTicketId());
      b.setProprietario(this.getProprietario());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();

      return null;
    }

  }
}

