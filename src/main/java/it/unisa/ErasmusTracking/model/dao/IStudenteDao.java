package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;
import main.java.it.unisa.ErasmusTracking.bean.Studente;

public interface IStudenteDao extends IAccountDao {
  public Studente doRetrieveByMatricola(String matricola);

  public List<Studente> doRetrieveByCoordinatore(int id);

  public void doUpdate(Object object);

}
