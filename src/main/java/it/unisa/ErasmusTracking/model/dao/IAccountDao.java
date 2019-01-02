package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Account;

import java.util.List;

public interface IAccountDao {
    public List<Account> doRetrieveByIdAccount(int id);
    public List<Account> doRetrieveByEmail(String email);
}
