package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.model.dao.ICoordinatoreDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.CoordinatoriManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCoordinatore")
public class AddCoordinatore extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static ICoordinatoreDao manager = new CoordinatoriManager(db, username, password);

    public AddCoordinatore() {
        super();
    }

    /**
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Coordinatore coordinatore = new Coordinatore();

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        coordinatore.setNome(nome);
        coordinatore.setCognome(cognome);
        coordinatore.setEmail(email);
        coordinatore.setPassword(password);
        coordinatore.setRuolo("coordinatore");

        coordinatore.setSending_institute(1);



        try {
            manager.doSave(coordinatore);

        } catch(NullPointerException e){
            e.printStackTrace();
        }


        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/AccountServlet?action=doRetrieveAll");
        dispositivo.forward(request, response);

    }

}