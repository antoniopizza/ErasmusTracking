package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LocalitaServlet")
public class LocalitaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static ILocalitaDao manager = new LocalitaManager(db, username, password);

    public LocalitaServlet() {
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
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");

        try {
            if (action != null) {
                if (action.equalsIgnoreCase("save")) {
                    Localita localita = new Localita();
                    localita.setCitta(request.getParameter("city"));
                    localita.setNazione(request.getParameter("nation"));
                    manager.doSave(localita);

                } else if (action.equalsIgnoreCase("delete")) {

                } else if (action.equalsIgnoreCase("doRetrieveById")) {

                } else if (action.equalsIgnoreCase("doRetrieveByCity")) {

                } else if (action.equalsIgnoreCase("doRetrieveByNation")) {

                }

            }
        } catch (Exception e) {

        }


    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}

