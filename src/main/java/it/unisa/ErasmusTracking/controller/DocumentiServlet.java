package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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

@WebServlet("/DocumentiServlet")
public class DocumentiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static IDocumentoDao manager = new DocumentiManager(db, username, password);


    public DocumentiServlet() {
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
                 if (action.equalsIgnoreCase("doRetrieveById")){
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
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/documenti.jsp");
                    dispositivo.forward(request, response);

                }  else if (action.equalsIgnoreCase("doRetrieveByIdAccount")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    List<Documenti> documenti = manager.doRetrieveByIdAccount(id);
                    request.removeAttribute("listaDocumenti");
                    request.setAttribute("listaDocumenti", documenti);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/documenti.jsp");
                    dispositivo.forward(request, response);
                } else if(action.equalsIgnoreCase("downloadById")) {
                     int id = Integer.parseInt(request.getParameter("id"));
                     Documenti documento = (Documenti) manager.doRetrieveById(id);
                     request.removeAttribute("documento");
                     request.setAttribute("documento", documento);

                     System.out.println(Integer.toString(documento.getInputStream().available()));
                     //Blob fileData = documento.getInputStream();

                     response.setHeader("Content-Type", "application/pdf");

                     response.setHeader("Content-Length", Integer.toString(documento.getInputStream().available()));

                     response.setHeader("Content-Disposition", "inline; filename=\"" + documento.getNome() + ".pdf" + "\"");

                     InputStream is = documento.getInputStream();


                     byte[] bytes = new byte[1024];
                     int bytesRead;

                     while ((bytesRead = is.read(bytes)) != -1) {
                         response.getOutputStream().write(bytes, 0, bytesRead);
                     }
                     is.close();

                        /*
                     RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/documenti.jsp");
                     dispositivo.forward(request, response);
                      */
                 }

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

