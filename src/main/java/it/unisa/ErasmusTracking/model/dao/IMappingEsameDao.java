package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;

import java.util.List;

public interface IMappingEsameDao extends IGenericDao {
    public List<MappingEsame> doRetrieveByLearningAgreement(int id);
    public void doUpdate(Object object);
}
