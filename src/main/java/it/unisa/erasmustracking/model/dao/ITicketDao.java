package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.Ticket;
import java.util.List;

public interface ITicketDao extends IGenericDao {
    public List<Ticket> doRetrieveByIdCoordinatore(int destinatario);
    public List<Ticket> doRetrieveByUsernameStudent(String username);
    public List<Ticket> doRetrieveByIdStudente(int mittente);

}
