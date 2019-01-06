package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.Messaggio_Ticket;

import java.util.Collection;






    public interface IMessaggioDao extends IGenericDao {
        public Collection<Messaggio_Ticket> doRetrieveByIdAccount(int idAccount);



    }


