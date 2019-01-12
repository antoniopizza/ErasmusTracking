package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.Account;

import java.util.List;

public interface IAccountDao extends IGenericDao{
    public Account doRetrieveByEmail(String email);
}
