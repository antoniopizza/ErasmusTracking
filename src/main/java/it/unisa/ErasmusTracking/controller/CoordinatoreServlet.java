package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.model.dao.ICoordinatoreDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.CoordinatoriManager;

@WebServlet("/CoordinatoreServlet")
public class CoordinatoreServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static ICoordinatoreDao manager = new CoordinatoriManager(db, username, password);


  public CoordinatoreServlet() {
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




    try {
      if (action != null) {
        if (action.equalsIgnoreCase("doRetrieveById")) {
          int id = Integer.parseInt(request.getParameter("id"));

          Coordinatore coordinatore = (Coordinatore) manager.doRetrieveById(id);
          request.removeAttribute("coordinatore");
          request.setAttribute("coordinatore", coordinatore);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("doRetrieveByEmail")) {
          String email = request.getParameter("email");
          Coordinatore coordinatore = (Coordinatore) manager.doRetrieveByEmail(email);
          request.removeAttribute("coordinatore");
          request.setAttribute("coordinatore", coordinatore);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);

        }  else if (action.equalsIgnoreCase("doRetrieveAll")) {
          List<Coordinatore> coordinatori = (ArrayList<Coordinatore>) manager.doRetrieveAll();
          request.removeAttribute("listaCoordinatori");
          request.setAttribute("listaCoordinatori", coordinatori);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/newCliente.jsp");
          dispositivo.forward(request, response);

        }

      }
    } catch (Exception e) {
      System.out.println("[CoordinatoreServlet.java] Errore: " + e);
    }
  }

  /**
   * doPost.
   *
   * @param request
   *
   * @param response
   *
   * @throws ServletException
   *
   * @throws IOException
   *
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doGet(request, response);
  }



}