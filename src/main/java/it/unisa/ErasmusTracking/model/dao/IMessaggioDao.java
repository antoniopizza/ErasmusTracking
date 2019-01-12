package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;
import main.java.it.unisa.ErasmusTracking.bean.MessaggioTicket;



public interface IMessaggioDao extends IGenericDao {
  public List<MessaggioTicket> doRetrieveByIdAccount(int idAccount);

  public List<MessaggioTicket> doRetrieveByIdTicket(int idTicket);
}


