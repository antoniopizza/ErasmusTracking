package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.Collection;
import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import java.util.Collection;

public interface ITicketDao extends IGenericDao {
    public Collection<Ticket> doRetrieveByIdCoordinatore(int destinatario);
    public Collection<Ticket> doRetrieveByUsernameStudent(String username);
    public Collection<Ticket> doRetrieveByIdStudente(int mittente);

}
