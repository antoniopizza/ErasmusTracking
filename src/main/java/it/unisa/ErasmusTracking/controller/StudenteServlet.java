/*package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;

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
*/
/*@WebServlet("/StudenteServlet")
public class StudenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static IStudenteDao manager = new StudenteManager(db, username, password);


    public DocumentiServlet() {
        super();
    }



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
  /*  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
        String page = request.getParameter("page");


        System.out.println("Aggiunto in pagina: " + page);


        try {
            if (action != null) {
                if (action.equalsIgnoreCase("save")) {
                    String nome = request.getParameter("nome");
                    String data_caricamento = request.getParameter("data_caricamento");
                    String url = request.getParameter("url");
                    int proprietario = Integer.parseInt(request.getParameter("proprietario"));
                    Documenti documento = new Documenti();
                    documento.setNome(nome);
                    documento.setDataCaricamento(data_caricamento);
                    documento.setUrl(url);
                    documento.setProprietario(proprietario);
                    manager.doSave(documento);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);
                } else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    manager.doDelete(id);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));

                    Documenti documento =(Documenti) manager.doRetrieveById(id);
                    request.removeAttribute("documento");
                    request.setAttribute("documento", documento);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveDocumentByUsernameStudent")){
                    String username = request.getParameter("username");
                    List<Documenti> documenti = manager.doRetrieveByUsernameStudent(username);
                    request.removeAttribute("listaDocumenti");
                    request.setAttribute("listaDocumenti", documenti);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                }  else if (action.equalsIgnoreCase("doRetrieveAll")){
                    List<Documenti> documenti = (ArrayList<Documenti>) manager.doRetrieveAll();
                    request.removeAttribute("listaDocumenti");
                    request.setAttribute("listaDocumenti", documenti);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);

                }  else if (action.equalsIgnoreCase("doRetrieveByIdAccount")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    List<Documenti> documenti = manager.doRetrieveByIdAccount(id);
                    request.removeAttribute("listaDocumenti");
                    request.setAttribute("listaDocumenti", documenti);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);
                }

            }
        } catch (Exception e){
            System.out.println("[DocumentiServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }



}
*/