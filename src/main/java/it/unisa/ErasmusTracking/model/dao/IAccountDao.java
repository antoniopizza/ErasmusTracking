package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Account;

public interface IAccountDao extends IGenericDao {
  public Account doRetrieveByEmail(String email);
}
