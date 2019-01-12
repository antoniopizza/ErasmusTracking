package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;


public interface IDocumentoDao extends IGenericDao {
  public List<Documenti> doRetrieveByIdAccount(int idAccount);

  public List<Documenti> doRetrieveByUsernameStudent(String username);
}
