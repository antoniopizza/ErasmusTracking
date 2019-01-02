package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Esame;
import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.MappingEsameManager;

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

@WebServlet("/MappingEsameServlet")
public class MappingEsameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static MappingEsameManager manager = new MappingEsameManager(db, username, password);


    public MappingEsameServlet() {
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


        System.out.println("Aggiunto in pagina: " + page);


        try {
            if (action != null) {
                if (action.equalsIgnoreCase("save")) {
                    String nomeEsameInterno = request.getParameter("esameInterno");
                    String codiceEsameInterno = request.getParameter("codiceEsameInterno");
                    int ectsEsameInterno = Integer.parseInt(request.getParameter("ectsEsameInterno"));
                    String nomeEsameEsterno = request.getParameter("esameEsterno");
                    String codiceEsameEsterno = request.getParameter("codiceEsameEsterno");
                    int ectsEsameEsterno = Integer.parseInt(request.getParameter("ectsEsameEsterno"));
                    String linguaEsame = request.getParameter("linguaEsame");
                    String statoEsame = request.getParameter("statoEsame");
                    MappingEsame mappingEsame = new MappingEsame();
                    Esame esameInterno = new Esame();
                    Esame esameEsterno = new Esame();
                    esameInterno.setNome(nomeEsameInterno);
                    esameInterno.setCodice(codiceEsameInterno);
                    esameInterno.setECTS(ectsEsameInterno);
                    esameEsterno.setNome(nomeEsameEsterno);
                    esameEsterno.setCodice(codiceEsameEsterno);
                    esameEsterno.setECTS(ectsEsameEsterno);
                    mappingEsame.setLingua(linguaEsame);
                    mappingEsame.setStato(statoEsame);
                    mappingEsame.setEsameInterno(esameInterno);
                    mappingEsame.setEsameEsterno(esameEsterno);


                    manager.doSave(mappingEsame);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);
                } else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    manager.doDelete(id);
                } else if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));

                    MappingEsame mappingEsame = manager.doRetrieveById(id);

                } /*

                    ON HOLD

                    else if (action.equalsIgnoreCase("doRetrieveDocumentByUsernameStudent")){
                    String username = request.getParameter("username");
                    Collection<Documenti> documenti = manager.doRetrieveDocumentByUsernameStudent(username);
                }*/
            }
        } catch (Exception e){
            System.out.println("[DocumentiServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }



}

