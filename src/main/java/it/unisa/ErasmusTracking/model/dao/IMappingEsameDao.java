package main.java.it.unisa.ErasmusTracking.model.dao;

import java.util.List;
import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;



public interface IMappingEsameDao extends IGenericDao {
  public List<MappingEsame> doRetrieveByLearningAgreement(int id);

  public void doUpdate(Object object);

}
