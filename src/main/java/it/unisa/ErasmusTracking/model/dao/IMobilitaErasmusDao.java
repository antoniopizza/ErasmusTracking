package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;

import java.util.List;

public interface IMobilitaErasmusDao extends IGenericDao {
  public void doUpdate(Object o);
  public List<MobilitaErasmus> doRetrieveByIdSending(int idSendingInstitute);
  public Object doRetrieveByLearningAgreement(int id);

}
