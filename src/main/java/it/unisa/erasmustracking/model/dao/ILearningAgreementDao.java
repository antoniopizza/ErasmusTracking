package main.java.it.unisa.erasmustracking.model.dao;

import main.java.it.unisa.erasmustracking.bean.LearningAgreement;
import main.java.it.unisa.erasmustracking.bean.Studente;

public interface ILearningAgreementDao extends IGenericDao {
    public LearningAgreement doRetrieveByStudente(int idStudente);
}
