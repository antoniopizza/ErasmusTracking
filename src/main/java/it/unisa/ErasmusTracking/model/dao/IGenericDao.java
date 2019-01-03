package main.java.it.unisa.ErasmusTracking.model.dao;

import java.sql.SQLException;
import java.util.List;


public interface IGenericDao {
    public void doSave(Object object);
    public boolean doDelete(int id);
    public List<?> doRetrieveAll();
    public Object doRetrieveById(int id);
}
