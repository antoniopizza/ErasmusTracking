package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;

public interface ILearningAgreementDao extends IGenericDao {
  public LearningAgreement doRetrieveByStudente(int idStudente);

  public void doUpdate(Object object);
}
