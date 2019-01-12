package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;
import main.java.it.unisa.ErasmusTracking.model.dao.IMappingEsameDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.MappingEsameManager;



@WebServlet("/AddMappingEsame")
public class AddMappingEsame extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static IMappingEsameDao manager = new MappingEsameManager(db, username, password);


  public AddMappingEsame() {
    super();
  }


  /**
   * doGet.
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   *
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LearningAgreement learningAgreement =
        (LearningAgreement) request.getAttribute("learningAgreement");
    MappingEsame mappingEsame = new MappingEsame();
    mappingEsame.setLearningAgreement(learningAgreement.getId());

    manager.doSave(mappingEsame);

    RequestDispatcher dispositivo =
        getServletContext().getRequestDispatcher("/learningAgreement.jsp");
    dispositivo.forward(request, response);

  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}