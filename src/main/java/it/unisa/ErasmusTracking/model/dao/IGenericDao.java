package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.Collection;


public interface IGenericDao {
    public void doSave(Object object);
    public boolean doDelete(Object object);
    public Collection<?> doRetrieveAll();
    public Object doRetrieveById(int id);
}
