package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Studente;
import java.util.List;

public interface IStudenteDao extends IGenericDao {
    public List<Studente> doRetrieveByIdAccount(int idAccount);
    public List<Studente> doRetrieveByMatricola(String matricola);

}
