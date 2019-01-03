package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IReceivingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.ReceivingInstituteManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddReceivingInstitute")
public class AddReceivingInstitute extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static IReceivingInstituteDao manager = new ReceivingInstituteManager(db, username, password);

    public AddReceivingInstitute() {
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
        String nomeContatto = request.getParameter("nomeContatto");
        String email = request.getParameter("email");
        String sizeEnterprise = request.getParameter("sizeEnterprise");
        String nomeMentore = request.getParameter("nomeMentore");
        String emailMentore = request.getParameter("emailMentore");
        String website = request.getParameter("website");
        int location = Integer.parseInt(request.getParameter("location"));

        ReceivingInstitute receivingInstitute = new ReceivingInstitute();

        receivingInstitute.setCodiceErasmus(codiceErasmus);
        receivingInstitute.setNomeContatto(nomeContatto);
        receivingInstitute.setEmailContatto(email);
        receivingInstitute.setSizeOfEnterprise(sizeEnterprise);
        receivingInstitute.setNomeMentore(nomeMentore);
        receivingInstitute.setEmailMentore(emailMentore);
        receivingInstitute.setWebsite(website);
        receivingInstitute.setLocalita(location);

        try {
            manager.doSave(receivingInstitute);

        } catch(NullPointerException e){
            e.printStackTrace();
        }

        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
        dispositivo.forward(request, response);

    }

}