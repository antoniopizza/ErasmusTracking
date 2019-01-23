package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;
import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.model.dao.ILearningAgreementDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IMobilitaErasmusDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IReceivingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ISendingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.LearningAgreementManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.MobilitaErasmusManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.ReceivingInstituteManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.SendingInstituteManager;

@WebServlet("/AddReceivingInstitute")
public class AddReceivingInstitute extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static IReceivingInstituteDao manager = new ReceivingInstituteManager(db, username, password);

  public AddReceivingInstitute() {
    super();
  }

  /**
   *doGet.
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   *
   * @param request
   *
   * @param response
   *
   * @throws ServletException
   *
   * @throws IOException
   *
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request,response);
  }

  /**
   *doPost.
   *
   * @param request
   *
   * @param response
   *
   * @throws ServletException
   *
   * @throws IOException
   *
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ReceivingInstitute receivingInstitute = new ReceivingInstitute();
    String nomeContatto = request.getParameter("nomeContatto");
    receivingInstitute.setNomeContatto(nomeContatto);

    String email = request.getParameter("email");
    receivingInstitute.setEmailContatto(email);

    String sizeEnterprise = request.getParameter("sizeEnterprise");
    receivingInstitute.setSizeOfEnterprise(sizeEnterprise);

    String nomeMentore = request.getParameter("nomeMentore");
    receivingInstitute.setNomeMentore(nomeMentore);

    String emailMentore = request.getParameter("emailMentore");
    receivingInstitute.setEmailMentore(emailMentore);

    String website = request.getParameter("website");
    receivingInstitute.setWebsite(website);

    int location = Integer.parseInt(request.getParameter("location"));
    System.out.println("Eccolo: " + request.getParameter("location"));
    receivingInstitute.setLocalita(location);






    try {
      manager.doSave(receivingInstitute);

      ISendingInstituteDao sendingInstituteDao =
          new SendingInstituteManager(db, username,password);
      SendingInstitute sendingInstitute =
          ((SendingInstituteManager) sendingInstituteDao).doRetrieveById(1);

      ILearningAgreementDao learningAgreementDao =
          new LearningAgreementManager(db,username,password);
      int idLa = Integer.parseInt(request.getParameter("learningAgreement"));
      LearningAgreement learningAgreement =
          ((LearningAgreementManager) learningAgreementDao).doRetrieveById(idLa);
      List<ReceivingInstitute> list1 =
          (List<ReceivingInstitute>) manager.doRetrieveAll();
      ReceivingInstitute rc =
          list1.get(list1.size() - 1);


      MobilitaErasmus mobilitaErasmus =
          new MobilitaErasmus();

      mobilitaErasmus.setLearningAgreement(learningAgreement.getId());
      mobilitaErasmus.setSendingInstitute(sendingInstitute);
      mobilitaErasmus.setReceivingInstitute(rc);

      IMobilitaErasmusDao mobilitaErasmusDao =
          new MobilitaErasmusManager(db,username,password);

      mobilitaErasmusDao.doSave(mobilitaErasmus);


    } catch (NullPointerException e) {
      e.printStackTrace();
    }

    //DA MODIFICARE NON APPENA CI SONO LE JSP
    ServletContext context = request.getSession().getServletContext();

    response.setContentType("text/html");
    RequestDispatcher dispositivo =
        context.getRequestDispatcher("/LearningAgreementServlet?action=doRetrieveByStudente");
    dispositivo.forward(request, response);

  }

}