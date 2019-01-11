package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.*;
import main.java.it.unisa.ErasmusTracking.model.jpa.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static AccountManager manager = new AccountManager(db, username, password);


    public AccountServlet() {
        super();
    }



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        Account accounto = (Account) request.getSession().getAttribute("utente");




        try {
            if (action != null) {
                if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    Account account = (Account) manager.doRetrieveById(id);
                    //System.out.println(account.toString());

                    Studente studente = new Studente();
                    Coordinatore coordinatore = new Coordinatore();
                    Amministratore amministratore = new Amministratore();


                    if(account.getRuolo().equalsIgnoreCase("studente")) {
                        IStudenteDao studenteDao = new StudenteManager(db, username, password);
                        studente = (Studente) studenteDao.doRetrieveById(account.getId());
                        request.removeAttribute("studente");
                        request.removeAttribute("coordinatore");
                        request.removeAttribute("amministratore");
                        request.setAttribute("studente", studente);
                    } else if (account.getRuolo().equalsIgnoreCase("coordinatore")) {
                        CoordinatoriManager coordinatoreDao = new CoordinatoriManager(db, username, password);
                        coordinatore = (Coordinatore) coordinatoreDao.doRetrieveById(account.getId());
                        request.removeAttribute("studente");
                        request.removeAttribute("coordinatore");
                        request.removeAttribute("amministratore");
                        request.setAttribute("coordinatore", coordinatore);
                    } else if (account.getRuolo().equalsIgnoreCase("amministratore")) {
                        IAmministratoreDao amministratoreDao = new AmministratoriManager(db, username, password);
                        amministratore = (Amministratore) amministratoreDao.doRetrieveById(account.getId());
                        request.removeAttribute("studente");
                        request.removeAttribute("coordinatore");
                        request.removeAttribute("amministratore");
                        request.setAttribute("amministratore", amministratore);
                    }

                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/profile.jsp");
                    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveByEmail")){
                    String email = request.getParameter("email");
                    Account account = (Account) manager.doRetrieveByEmail(email);
                    request.removeAttribute("account");
                    request.setAttribute("account", account);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                }  else if (action.equalsIgnoreCase("doRetrieveAll")){
                    List<Account> accounts = (ArrayList<Account>) manager.doRetrieveAll();
                    request.removeAttribute("listaAccounts");
                    request.setAttribute("listaAccounts", accounts);


                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/utente.jsp");
                    dispositivo.forward(request, response);

                }

            }
        } catch (Exception e){
            System.out.println("[AccountServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }



}