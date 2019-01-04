package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;

public interface IMappingEsameDao extends IGenericDao {
    public MappingEsame doRetrieveByLearningAgreement(int id);
}
