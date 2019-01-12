package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.MappingEsame;

public interface IMappingEsameDao extends IGenericDao {
    public MappingEsame doRetrieveByLearningAgreement(int id);
}
