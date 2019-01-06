package main.java.it.unisa.ErasmusTracking.controller;




import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ISendingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.SendingInstituteManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddSendingInstitute")
public class AddSendingInstitute extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static ISendingInstituteDao manager = new SendingInstituteManager(db, username, password);

    public AddSendingInstitute() {
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


        String codiceErasmus = request.getParameter("codiceErasmus");
        String dipartimento = request.getParameter("dipartimento");
        String indirizzo = request.getParameter("indirizzo");
        SendingInstitute sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus(codiceErasmus);
        sendingInstitute.setDipartimento(dipartimento);
        sendingInstitute.setIndirizzo(indirizzo);

        try {
            manager.doSave(sendingInstitute);

        } catch(NullPointerException e){
            e.printStackTrace();
        }

        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
        dispositivo.forward(request, response);



    }

}