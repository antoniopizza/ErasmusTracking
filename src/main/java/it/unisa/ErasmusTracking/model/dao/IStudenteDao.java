package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Studente;
import java.util.List;

public interface IStudenteDao extends IAccountDao {
    public List<Studente> doRetrieveByIdStudente(int idStudente);
    public List<Studente> doRetrieveByMatricola(String matricola);

}
