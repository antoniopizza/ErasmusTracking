package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import java.sql.SQLException;
import java.util.Collection;

public interface IDocumentoDao extends IGenericDao {
    public Collection<Documenti> doRetrieveByIdAccount(int idAccount);
    public Collection<Documenti> doRetrieveByUsernameStudent(String username);
}
