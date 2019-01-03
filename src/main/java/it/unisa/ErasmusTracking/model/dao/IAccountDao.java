package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Account;

import java.util.List;

public interface IAccountDao extends IGenericDao{
    public List<?> doRetrieveByEmail(String email);
}
