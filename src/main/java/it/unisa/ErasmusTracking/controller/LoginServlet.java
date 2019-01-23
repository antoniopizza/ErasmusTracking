package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.model.dao.IAccountDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;



/**
 * Servlet implementation class AdminLogin.
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static IAccountDao model = new AccountManager(db,username,password);


  /**
   * LoginServle.
   *
   * @see HttpServlet#HttpServlet()
   *
   */
  public LoginServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * doGet.
   *
   *  @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   *
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("Error. This servlet must be called with POST method.");
  }

  /**
   * doPost.
   *
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   *
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    String userForm = request.getParameter("username");
    String passForm = request.getParameter("password");
    System.out.println("Username: " + userForm + ", password: " + passForm);


    Account account = new Account();
    HttpSession session = request.getSession();
    //Questa variabile a 0 serve per prevenire l'accesso non autorizzato alla pagina dopo il login
    String ruolo = "";
    //salvo la variabile nella session per leggerla dalle pagine autorizzate
    session.setAttribute("ruolo", ruolo);

    String linkReind = (String) session.getAttribute("link");
    System.out.println(linkReind);
    try {
      request.removeAttribute("utente");
      account = model.doRetrieveByEmail(userForm);
    } catch (Exception e) {
      System.out.println("[AdminLogin.java] Error: " + e);
    }

    if(account.getEmail() != null) {
      //username e password corrispondono
      if (account.getEmail().equals(userForm) && account.getPassword().equals(passForm)) {
        System.out.println(account.toString());
        //salvo il nome dell'admin nella sessione
        session.setAttribute("name", userForm);
        //metto il bit di controllo admin a 1 per l'accesso autorizzato
        ruolo = account.getRuolo();
        session.removeAttribute("ruolo");
        //inserisco il bit nella session per leggerlo dalle page autorizzate
        session.setAttribute("ruolo", ruolo);
        session.removeAttribute("utente");
        session.setAttribute("utente", account);
        ServletContext context = request.getSession().getServletContext();

        response.setContentType("text/html");
        //vado sulla pagina di errore login
        response.sendRedirect(
            context
                +
                "/AccountServlet?action=doRetrieveById&id="
                +
                account.getId());
      } else { //username o psw o entrambi errati
        ServletContext context = request.getSession().getServletContext();

        response.setContentType("text/html");
        response.sendRedirect(
            context + "/login.jsp?page=fail"); //vado sulla pagina di errore login
      }
    } else { //username o psw o entrambi errati
      ServletContext context = request.getSession().getServletContext();

      response.setContentType("text/html");
      response.sendRedirect(
          context + "/login.jsp?page=fail"); //vado sulla pagina di errore login
    }

  }

}