package main.java.it.unisa.ErasmusTracking.model.dao;

import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.Studente;

public interface ILearningAgreementDao extends IGenericDao {
    public LearningAgreement doRetrieveLearningAgreement(Studente studente);
}
