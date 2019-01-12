package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.Documenti;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface IDocumentoDao extends IGenericDao {
    public List<Documenti> doRetrieveByIdAccount(int idAccount);
    public List<Documenti> doRetrieveByUsernameStudent(String username);
}
