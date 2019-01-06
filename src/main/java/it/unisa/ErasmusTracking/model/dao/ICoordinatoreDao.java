package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;

import java.util.List;

public interface ICoordinatoreDao extends IAccountDao {
    public Coordinatore doRetrieveByIdAccount(int IdAccount);
}
