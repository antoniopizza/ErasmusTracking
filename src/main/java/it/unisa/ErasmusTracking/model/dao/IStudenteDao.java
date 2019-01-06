package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Studente;
import java.util.List;

public interface IStudenteDao extends IAccountDao {
    public Studente doRetrieveByIdStudente(int idStudente);
    public Studente doRetrieveByMatricola(String matricola);

}
