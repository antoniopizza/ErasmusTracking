package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.StudenteManager;



@WebServlet("/StudenteServlet")
public class StudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static IStudenteDao manager = new StudenteManager(db, username, password);


  public StudenteServlet() {
    super();
  }



  /**
   * doGet.
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   *
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Riceve il parametro per capire quale azione effettuare
    String action = request.getParameter("action");
    //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
    String page = request.getParameter("page");

    response.setContentType("text/html");
    ServletContext context = request.getSession().getServletContext();

    try {
      if (action != null) {
        if (action.equalsIgnoreCase("doRetrieveById")) {
          int id = Integer.parseInt(request.getParameter("id"));

          Studente studente = (Studente) manager.doRetrieveById(id);
          request.removeAttribute("studente");
          request.setAttribute("studente", studente);



          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo = context.getRequestDispatcher("/utente.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("doRetrieveByEmail")) {
          String email = request.getParameter("email");
          Studente studente = (Studente) manager.doRetrieveByEmail(email);
          request.removeAttribute("studente");
          request.setAttribute("studente", studente);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo = context.getRequestDispatcher("/utente.jsp");
          dispositivo.forward(request, response);

        }  else if (action.equalsIgnoreCase("doRetrieveAll")) {
          List<Studente> studenti = (ArrayList<Studente>) manager.doRetrieveAll();
          request.removeAttribute("listaStudenti");
          request.setAttribute("listaStudenti", studenti);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo = context.getRequestDispatcher("/utente.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("doRetrieveByCoordinatore")) {
          Account coordinatore = (Account) request.getSession().getAttribute("utente");
          List<Studente> studenti =
              (ArrayList<Studente>) manager.doRetrieveByCoordinatore(coordinatore.getId());
          //System.out.print(studenti.toString());
          request.removeAttribute("listaStudenti");
          request.setAttribute("listaStudenti", studenti);

          RequestDispatcher dispositivo = context.getRequestDispatcher("/utente.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("doUpdateLearningAgreement")) {
          Account account = (Account) request.getSession().getAttribute("utente");
          Studente studente = new Studente();
          studente.setCognome(account.getCognome());
          studente.setNome(account.getNome());
          studente.setId(account.getId());
          studente.setEmail(account.getEmail());
          studente.setPassword(account.getPassword());

          String nome = request.getParameter("nome");
          String cognome = request.getParameter("cognome");
          String email = request.getParameter("email");
          String dataDiNascita = request.getParameter("data_di_nascita");
          String luogoDiNascita = request.getParameter("luogo_di_nascita");
          String codiceMateria = request.getParameter("codice_materia");
          String telefono = request.getParameter("telefono");
          int annoAccademico = Integer.parseInt(request.getParameter("anno_accademico"));

          studente.setNome(nome);
          studente.setCognome(cognome);
          studente.setEmail(email);
          studente.setDataDiNascita(dataDiNascita);
          studente.setLuogoDiNascita(luogoDiNascita);
          studente.setCodiceMateria(codiceMateria);
          studente.setTelefono(telefono);
          studente.setAnnoAccademico(annoAccademico);

          IStudenteDao manager = new StudenteManager(db, username, password);
          manager.doUpdate(studente);

          //DA MODIFICARE NON APPENA CI SONO LE JSP
          RequestDispatcher dispositivo = context.getRequestDispatcher("/learning-agreement.jsp");
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
