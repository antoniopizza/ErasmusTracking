package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.model.dao.IAccountDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static IAccountDao model = new AccountManager(db,username,password);


    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Error. This servlet must be called with POST method.");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userForm=request.getParameter("username");
        String passForm=request.getParameter("password");
        System.out.println("Username: " + userForm + ", password: " + passForm);


        Account account = new Account();
        HttpSession session=request.getSession();
        String ruolo = ""; //Questa variabile a 0 serve per prevenire l'accesso non autorizzato alla pagina dopo il login
        session.setAttribute("ruolo", ruolo); //salvo la variabile nella session per leggerla dalle pagine autorizzate

        String linkReind = (String) session.getAttribute("link");
        System.out.println(linkReind);
        try {
            request.removeAttribute("account");
            account = model.doRetrieveByEmail(userForm);
            request.setAttribute("account", account);
            //System.out.println(account.toString());
        } catch(Exception e) {
            System.out.println("[AdminLogin.java] Error: " + e);
        }

        if(account.getEmail().equals(userForm) && account.getPassword().equals(passForm)) { //username e password corrispondono


            session.setAttribute("name", userForm); //salvo il nome dell'admin nella sessione
            ruolo = account.getRuolo(); //metto il bit di controllo admin a 1 per l'accesso autorizzato
            session.setAttribute("ruolo", ruolo); //inserisco il bit nella session per leggerlo dalle page autorizzate

            //	RequestDispatcher disp = getServletContext().getRequestDispatcher("/" + linkReind); //trasferisco sulla pagina dopo il login
            //	disp.forward(request, response);
            response.sendRedirect(linkReind);
        }
        else { //username o psw o entrambi errati
            response.sendRedirect(request.getContextPath() + "/loginFail.jsp"); //vado sulla pagina di errore login
        }

    }

}