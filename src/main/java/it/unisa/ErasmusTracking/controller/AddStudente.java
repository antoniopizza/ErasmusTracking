package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IAccountDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.StudenteManager;



@WebServlet("/AddStudente")
public class AddStudente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static IStudenteDao manager = new StudenteManager(db, username, password);
  static IAccountDao account = new AccountManager(db, username, password);


  public AddStudente() {
    super();
  }

  /**
   *doGet.
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request,response);
  }

  /**
   *doPost.
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
    Account utente = (Account) request.getSession().getAttribute("utente");
    int coordinatore = utente.getId();
    String page = request.getParameter("page");
    String update = request.getParameter("update");
    Studente studente = new Studente();


    if (update.equalsIgnoreCase("1")) {
      String dataDiNascita = request.getParameter("data_di_nascita");
      String luogoDiNascita = request.getParameter("luogo_di_nascita");
      String sesso = request.getParameter("sesso");
      String nazionalita = request.getParameter("nazionalita");
      String telefono = request.getParameter("telefono");
      int annoAccademico = Integer.parseInt(request.getParameter("anno_accademico"));
      studente.setDataDiNascita(dataDiNascita);
      studente.setLuogoDiNascita(luogoDiNascita);
      studente.setSesso(sesso);
      studente.setNazionalita(nazionalita);
      studente.setTelefono(telefono);
      studente.setAnnoAccademico(annoAccademico);


    } else if (!update.equalsIgnoreCase("1")) {
      String cicloStudi = request.getParameter("ciclo_di_studi");
      String matricola = request.getParameter("matricola");
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String tipo = request.getParameter("tipo");
      studente.setMatricola(matricola);
      studente.setNome(nome);
      studente.setCognome(cognome);
      studente.setEmail(email);
      studente.setPassword(password);
      studente.setRuolo("studente");
      studente.setTipo(tipo);
      studente.setIdCoordinatore(coordinatore);
      studente.setCicloDiStudi(cicloStudi);

    }

    try {
      if (update.equalsIgnoreCase("1")) {
        studente.setId(utente.getId());
        studente.setEmail(utente.getEmail());
        studente.setPassword(utente.getPassword());
        studente.setRuolo("studente");
        manager.doUpdate(studente);
      } else {
        manager.doSave(studente);
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
    }


    response.setContentType("text/html");

    //DA MODIFICARE NON APPENA CI SONO LE JSP
    RequestDispatcher dispositivo = null;
    ServletContext context = request.getSession().getServletContext();

    if (update.equalsIgnoreCase("1")) {
      if (page.equalsIgnoreCase("learning-agreement")) {
        dispositivo = context.getRequestDispatcher(
            "/LearningAgreementServlet?action=doRetrieveByStudente");
      } else if (page.equalsIgnoreCase("profile")) {
        dispositivo = context.getRequestDispatcher(
            "/AccountServlet?action=doRetrieveById&id=" + studente.getId());
      }
      dispositivo.forward(request, response);
    } else {
      dispositivo = context.getRequestDispatcher(
          "/StudenteServlet?action=doRetrieveByCoordinatore");
      dispositivo.forward(request, response);
    }

  }

}