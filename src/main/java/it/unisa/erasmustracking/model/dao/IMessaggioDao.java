package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.Messaggio_Ticket;

import java.util.List;


public interface IMessaggioDao extends IGenericDao {
        public List<Messaggio_Ticket> doRetrieveByIdAccount(int idAccount);
        public List<Messaggio_Ticket> doRetrieveByIdTicket(int idTicket);



    }


