package main.java.it.unisa.ErasmusTracking.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;

@WebServlet("/DocumentiServlet")
public class DocumentiServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";
  private final int ARBITARY_SIZE = 4096;

  static IDocumentoDao manager = new DocumentiManager(db, username, password);

  public DocumentiServlet() {
    super();
  }

  /**
   * doGet.
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Riceve il parametro per capire quale azione effettuare
    String action = request.getParameter("action");
    //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
    String page = request.getParameter("page");
    System.out.println("Aggiunto in pagina: " + page);
    try {
      if (action != null) {
        if (action.equalsIgnoreCase("doRetrieveById")) {
          int id = Integer.parseInt(request.getParameter("id"));
          Documenti documento = (Documenti) manager.doRetrieveById(id);
          request.removeAttribute("documento");
          request.setAttribute("documento", documento);
          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);
        } else if (action.equalsIgnoreCase("doRetrieveDocumentByUsernameStudent")) {
          String username = request.getParameter("username");
          List<Documenti> documenti = manager.doRetrieveByUsernameStudent(username);
          request.removeAttribute("listaDocumenti");
          request.setAttribute("listaDocumenti", documenti);
          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);
        } else if (action.equalsIgnoreCase("doRetrieveAll")) {
          List<Documenti> documenti = (ArrayList<Documenti>) manager.doRetrieveAll();
          request.removeAttribute("listaDocumenti");
          request.setAttribute("listaDocumenti", documenti);
          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/documenti.jsp");
          dispositivo.forward(request, response);
        } else if (action.equalsIgnoreCase("doRetrieveByIdAccount")) {
          Account account = (Account) request.getSession().getAttribute("utente");
          List<Documenti> documenti = manager.doRetrieveByIdAccount(account.getId());
          request.removeAttribute("listaDocumenti");
          request.setAttribute("listaDocumenti", documenti);
          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/documenti.jsp");
          dispositivo.forward(request, response);
        } else if (action.equalsIgnoreCase("downloadById")) {
          int id = Integer.parseInt(request.getParameter("id"));
          Documenti documento = (Documenti) manager.doRetrieveById(id);
          response.setContentType("application/pdf");
          response.setHeader("Content-disposition", "attachment; filename=" + documento.getNome() + ".pdf");
          try (InputStream in = new FileInputStream(documento.getUrl());
               OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[ARBITARY_SIZE];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
              out.write(buffer, 0, ARBITARY_SIZE);
            }
            out.flush();
          }
        }

      }
    } catch (Exception e) {
      System.out.println("[DocumentiServlet.java] Errore: " + e);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
}


/*
package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import java.io.OutputStream;


@WebServlet("/DocumentiServlet")
public class DocumentiServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";
  private final int ARBITARY_SIZE = 4096;

  static IDocumentoDao manager = new DocumentiManager(db, username, password);


  public DocumentiServlet() {
    super();
  }



  */
/**
 * doGet.
 *
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 *
 *//*


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Riceve il parametro per capire quale azione effettuare
    String action = request.getParameter("action");
    //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
    String page = request.getParameter("page");


    System.out.println("Aggiunto in pagina: " + page);


    try {
      if (action != null) {
        if (action.equalsIgnoreCase("doRetrieveById")) {
          int id = Integer.parseInt(request.getParameter("id"));

          Documenti documento = (Documenti) manager.doRetrieveById(id);
          request.removeAttribute("documento");
          request.setAttribute("documento", documento);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("doRetrieveDocumentByUsernameStudent")) {
          String username = request.getParameter("username");
          List<Documenti> documenti = manager.doRetrieveByUsernameStudent(username);
          request.removeAttribute("listaDocumenti");
          request.setAttribute("listaDocumenti", documenti);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);

        }  else if (action.equalsIgnoreCase("doRetrieveAll")) {
          List<Documenti> documenti = (ArrayList<Documenti>) manager.doRetrieveAll();
          request.removeAttribute("listaDocumenti");
          request.setAttribute("listaDocumenti", documenti);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/documenti.jsp");
          dispositivo.forward(request, response);

        }  else if (action.equalsIgnoreCase("doRetrieveByIdAccount")) {
          Account account = (Account) request.getSession().getAttribute("utente");

          List<Documenti> documenti = manager.doRetrieveByIdAccount(account.getId());
          request.removeAttribute("listaDocumenti");
          request.setAttribute("listaDocumenti", documenti);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/documenti.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("downloadById")) {
          int id = Integer.parseInt(request.getParameter("id"));
          Documenti documento = (Documenti) manager.doRetrieveById(id);
          request.removeAttribute("documento");
          request.setAttribute("documento", documento);

          System.out.println(Integer.toString(documento.getInputStream().available()));
          //Blob fileData = documento.getInputStream();

          response.setHeader("Content-Type", "application/pdf");

          response.setHeader(
              "Content-Length", Integer.toString(documento.getInputStream().available()));

          response.setHeader(
              "Content-Disposition", "inline; filename=\"" + documento.getNome() + ".pdf" + "\"");

          InputStream is = documento.getInputStream();
          OutputStream out = response.getOutputStream();

          byte[] bytes = new byte[1024];
          int bytesRead;

          while ((bytesRead = is.read(bytes)) != -1) {
            response.getOutputStream().write(bytes, 0, ARBITARY_SIZE);
          }
        }  else if(action.equalsIgnoreCase("delete")){
          int id=Integer.parseInt(request.getParameter("id"));

          manager.doDelete(id);

          RequestDispatcher dispositivo =
                  getServletContext().getRequestDispatcher(
                          "/DocumentiServlet?action=doRetrieveAll");
          dispositivo.forward(request, response);

        }

      }
    } catch (Exception e) {
      System.out.println("[DocumentiServlet.java] Errore: " + e);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }



}

*/
