package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddLocalita")
public class AddLocalita extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static ILocalitaDao manager = new LocalitaManager(db, username, password);

    public AddLocalita() {
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

                    Localita localita = new Localita();
                    localita.setCitta(request.getParameter("indirizzo"));
                    localita.setNazione(request.getParameter("nazione"));
                   System.out.println("LALA");
                    try {
                       manager.doSave(localita);
                        System.out.println("LALA");
                   } catch (NullPointerException e) {
                       e.printStackTrace();
                   }
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);



            }
        }