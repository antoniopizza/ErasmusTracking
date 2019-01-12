package main.java.it.unisa.erasmustracking.controller;

import main.java.it.unisa.erasmustracking.bean.Amministratore;
import main.java.it.unisa.erasmustracking.bean.Coordinatore;
import main.java.it.unisa.erasmustracking.bean.Documenti;
import main.java.it.unisa.erasmustracking.bean.Studente;
import main.java.it.unisa.erasmustracking.model.dao.IAmministratoreDao;
import main.java.it.unisa.erasmustracking.model.dao.ICoordinatoreDao;
import main.java.it.unisa.erasmustracking.model.dao.IDocumentoDao;
import main.java.it.unisa.erasmustracking.model.dao.IStudenteDao;
import main.java.it.unisa.erasmustracking.model.jpa.AmministratoriManager;
import main.java.it.unisa.erasmustracking.model.jpa.CoordinatoriManager;
import main.java.it.unisa.erasmustracking.model.jpa.DocumentiManager;
import main.java.it.unisa.erasmustracking.model.jpa.StudenteManager;

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

@WebServlet("/AmministratoreServlet")
public class AmministratoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static IAmministratoreDao manager = new AmministratoriManager(db, username, password);


    public AmministratoreServlet() {
        super();
    }



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
        String page = request.getParameter("page");


        try {
            if (action != null) {
                if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));

                    Amministratore amministratore =(Amministratore) manager.doRetrieveById(id);
                    request.removeAttribute("amministratore");
                    request.setAttribute("amministratore", amministratore);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveByEmail")){
                    String email = request.getParameter("email");
                    Amministratore amministratore = (Amministratore) manager.doRetrieveByEmail(email);
                    request.removeAttribute("amministratore");
                    request.setAttribute("amministratore", amministratore);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                }  else if (action.equalsIgnoreCase("doRetrieveAll")){
                    List<Amministratore> amministratori = (ArrayList<Amministratore>) manager.doRetrieveAll();
                    request.removeAttribute("listaAmministratori");
                    request.setAttribute("listaAmministratori", amministratori);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                }

            }
        } catch (Exception e){
            System.out.println("[AmministratoreServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }



}