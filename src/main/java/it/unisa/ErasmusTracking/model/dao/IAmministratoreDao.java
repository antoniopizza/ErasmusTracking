package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;

import java.util.List;

public interface IAmministratoreDao extends IAccountDao{
    public List<Amministratore> doRetrieveByIdAmministratore(int IdAccount);
}