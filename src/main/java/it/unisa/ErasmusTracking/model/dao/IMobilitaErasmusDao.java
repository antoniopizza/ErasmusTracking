package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;

import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;

public interface IMobilitaErasmusDao extends IGenericDao {
  public void doUpdate(Object o);

  public List<MobilitaErasmus> doRetrieveByIdSending(int idSendingInstitute);

  public Object doRetrieveByLearningAgreement(int id);

}
