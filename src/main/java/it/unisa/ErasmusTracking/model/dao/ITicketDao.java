package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;
import main.java.it.unisa.ErasmusTracking.bean.Ticket;


public interface ITicketDao extends IGenericDao {
  public List<Ticket> doRetrieveByIdCoordinatore(int destinatario);

  public void doClose(int idTicket);

  public List<Ticket> doRetrieveByIdStudente(int mittente);

}
