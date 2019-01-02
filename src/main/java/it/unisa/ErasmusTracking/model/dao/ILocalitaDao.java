package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.Collection;

public interface ILocalitaDao extends IGenericDao {
    public Collection<?> doRetrieveByCity(String city);
    public Collection<?> doRetrieveByNation(String nation);
}
