package main.java.it.unisa.ErasmusTracking.bean;


/**
 * Questa classe è un bean per la gestione delle localita'.
 *
 * @author Ripoli Federico
 *
 * @version 0.1
 *
 */


public class Localita {

  /**
   * variabili d'istanza.
   */
  private Integer id;
  private String citta;
  private String nazione;
  private String nome;
  private String codiceErasmus;
  private int coordinatore;


  /**
   * costruttore di localita'.
   *
   * @param citta
   *
   * @param nazione
   *
   * @param nome
   *
   * @param codiceErasmus
   *
   */
  public Localita(String citta, String nazione,
                  String nome,String codiceErasmus,
                  int coordinatore) {
    this.citta = citta;
    this.nazione = nazione;
    this.nome = nome;
    this.codiceErasmus = codiceErasmus;
    this.coordinatore = coordinatore;
  }

  /**
   *Costruttore vuoto.
   */
  public Localita() {
  }

  /**
   * modifica l'id dell'istituto.
   *
   * @param id
   *
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * getId.
   *
   * @return id L'id dell'istituto
   *
   */
  public Integer getId() {
    return id;
  }

  /**
   * getCitta.
   *
   * @return string citta La citta' dell'istituto
   *
   */
  public String getCitta() {
    return citta;
  }

  /**
   * Modifica la citta' dell'istituto.
   *
   */
  public void setCitta(String citta) {
    this.citta = citta;
  }

  /**
   * getNazione.
   *
   * @return string nazione La nazione dell'istituto
   *
   */
  public String getNazione() {
    return nazione;
  }

  /**
   * Modifica la nazione dell'istituto.
   *
   * @param nazione
   *
   */
  public void setNazione(String nazione) {
    this.nazione = nazione;
  }

  /**
   * getNome.
   *
   * @return string nome Il nome dell'istituto
   *
   */
  public String getNome() {
    return nome;
  }

  /**
   * modifica il nome dell'istituto.
   *
   * @param nome
   *
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * getCodiceErasmus.
   *
   * @return codiceErrasmus
   *
   */
  public String getCodiceErasmus() {
    return codiceErasmus;
  }

  /**
   * modifica il codice Erasmus dell'istituto.
   *
   * @param codiceErasmus
   *
   */
  public void setCodiceErasmus(String codiceErasmus) {
    this.codiceErasmus = codiceErasmus;
  }

  /**
   * getCoordinatore.
   *
   * @return int coordinatore Il coordinatore relatvo all'istituto
   *
   */
  public int getCoordinatore() {
    return coordinatore;
  }

  /**
   * modifica il coordinatore relativo all'istituto.
   *
   * @param coordinatore
   *
   */
  public void setCoordinatore(int coordinatore) {
    this.coordinatore = coordinatore;
  }

  @Override
  public String toString() {
    return "Id= " + id + "\n"
        +
        "CodiceErasmus= " + codiceErasmus + "\n"
        +
        "Nome= " + nome + "\n"
        +
        "Città= " +  citta + "\n"
        +
        "Nazione= " + nazione + "\n"
        +
        "Coordinatore= " + coordinatore + "\n";
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

    Localita loc = (Localita) obj;

    if (this.getId() == (loc.getId())
        &&
        this.getCitta().equals(loc.getCitta())
        &&
        this.getNazione().equals(loc.getNazione())
        &&
        this.getCodiceErasmus().equals(loc.getCodiceErasmus())
        &&
        this.getNome().equals(loc.getNome())
        &&
        this.getCoordinatore() == loc.getCoordinatore()) {
      return true;
    }
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    try {

      Localita b = (Localita) super.clone();

      b.setId(this.getId());
      b.setCitta(this.getCitta());
      b.setNazione(this.getNazione());

      return b;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();

      return null;
    }

  }

}
