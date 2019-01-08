package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;

public interface ILocalitaDao extends IGenericDao {
    public List<?> doRetrieveByCity(String city);
    public List<?> doRetrieveByNation(String nation);
    public List<?> doRetrieveByNome(String nation);
    public List<?> doRetrieveByCodiceErasmus(String nation);

}
