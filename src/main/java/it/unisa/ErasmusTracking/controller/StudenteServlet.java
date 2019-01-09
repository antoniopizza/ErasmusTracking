package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.StudenteManager;

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

                    Studente studente =(Studente) manager.doRetrieveById(id);
                    request.removeAttribute("studente");
                    request.setAttribute("studente", studente);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/utente.jsp");
                    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveByEmail")){
                    String email = request.getParameter("email");
                    Studente studente = (Studente) manager.doRetrieveByEmail(email);
                    request.removeAttribute("studente");
                    request.setAttribute("studente", studente);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/utente.jsp");
                    dispositivo.forward(request, response);

                }  else if (action.equalsIgnoreCase("doRetrieveAll")){
                    List<Studente> studenti = (ArrayList<Studente>) manager.doRetrieveAll();
                    request.removeAttribute("listaStudenti");
                    request.setAttribute("listaStudenti", studenti);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/utente.jsp");
                    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveByCoordinatore")) {
                     Account coordinatore = (Account) request.getSession().getAttribute("utente");
                     System.out.print(coordinatore.toString());
                     List<Studente> studenti = (ArrayList<Studente>) manager.doRetrieveByCoordinatore(coordinatore.getId());
                     //System.out.print(studenti.toString());
                     request.removeAttribute("listaStudenti");
                     request.setAttribute("listaStudenti", studenti);

                     //DA MODIFICARE NON APPENA CI SONO LE JSP
                     RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/utente.jsp");
                     dispositivo.forward(request, response);
                } else if (action.equalsIgnoreCase("doUpdateLearningAgreement")) {
                    Studente studente = (Studente) request.getSession().getAttribute("utente");
                    IStudenteDao manager = new StudenteManager(db, username, password);
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String email = request.getParameter("email");
                    String data_di_nascita = request.getParameter("data_di_nascita");
                    String luogo_di_nascita = request.getParameter("luogo_di_nascita");
                    String codice_materia = request.getParameter("codice_materia");
                    String telefono = request.getParameter("telefono");
                    int anno_accademico = Integer.parseInt(request.getParameter("anno_accademico"));

                    studente.setNome(nome);
                    studente.setCognome(cognome);
                    studente.setEmail(email);
                    studente.setDataDiNascita(data_di_nascita);
                    studente.setLuogoDiNascita(luogo_di_nascita);
                    studente.setCodiceMateria(codice_materia);
                    studente.setTelefono(telefono);
                    studente.setAnnoAccademico(anno_accademico);

                    manager.doUpdate(studente);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/learning-agreement.jsp");
                    dispositivo.forward(request, response);
                }
            }
        } catch (Exception e){
            System.out.println("[CoordinatoreServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }



}
