package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.model.dao.IAmministratoreDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.AmministratoriManager;

@WebServlet("/AddAmministratore")
public class AddAmministratore extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static IAmministratoreDao manager = new AmministratoriManager(db, username, password);

  public AddAmministratore() {
    super();
  }

  /**
   *doGet.
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

    int account = Integer.parseInt(request.getParameter("account"));

    Amministratore amministratore = new Amministratore();

    amministratore.setId(account);


    try {
      manager.doSave(amministratore);

    } catch (NullPointerException e) {
      e.printStackTrace();
    }


    //DA MODIFICARE NON APPENA CI SONO LE JSP

    ServletContext context = request.getSession().getServletContext();

    response.setContentType("text/html");


    RequestDispatcher dispositivo = context.getRequestDispatcher("/newCliente.jsp");
    dispositivo.forward(request, response);

  }

}