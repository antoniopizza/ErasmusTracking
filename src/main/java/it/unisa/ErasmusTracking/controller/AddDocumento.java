package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;

@WebServlet("/AddDocumento")
@MultipartConfig(fileSizeThreshold
    = 1024 * 1024, maxFileSize
    = 1024 * 1024 * 5, maxRequestSize
    = 1024 * 1024 * 5 * 5)
public class AddDocumento extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";
  private static final String UPLOAD_DIR = "uploads";

  static IDocumentoDao manager = new DocumentiManager(db, username, password);

  public AddDocumento() {
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
    String fileName = null;
    int fileSize = 0;
    InputStream inputStream = null;
    Part filePart = request.getPart("url");

    if (filePart != null) {
      inputStream = filePart.getInputStream();
      fileSize = (int) filePart.getSize();
    }

    Account account = (Account)request.getSession().getAttribute("utente");
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dateFormatted = date.format(formatter); //data in dd/mm/yyyy

    Documenti documento = new Documenti();
    documento.setNome(request.getParameter("filename"));
    documento.setFileSize(fileSize);
    documento.setInputStream(inputStream);
    documento.setDataCaricamento(dateFormatted);
    documento.setProprietario(account.getId());

    try {
      manager.doSave(documento);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }


    //DA MODIFICARE NON APPENA CI SONO LE JSP
    RequestDispatcher dispositivo =
        getServletContext().getRequestDispatcher("/DocumentiServlet?action=doRetrieveAll");
    dispositivo.forward(request, response);

  }

  private String getFileName(Part part) {
    String contentDisp = part.getHeader("content-disposition");
    System.out.println("content-disposition header= " + contentDisp);
    String[] tokens = contentDisp.split(";");
    for (String token : tokens) {
      if (token.trim().startsWith("filename")) {
        return token.substring(token.indexOf("=") + 2, token.length() - 1);
      }
    }
    return "";
  }

}
