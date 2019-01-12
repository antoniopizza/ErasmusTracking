package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.Studente;
import java.util.List;

public interface IStudenteDao extends IAccountDao {
    public Studente doRetrieveByMatricola(String matricola);
    public List<Studente> doRetrieveByCoordinatore(int id);
    public void doUpdate(Object object);
}
