package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudenteManagerTest {
  private static StudenteManager classUnderTest;
  private static CoordinatoriManager coordinatoreManager;
  private static AccountManager m;
  private static Studente bean;
  private static String matricola ="0215456332";

  @BeforeAll
  static void setUp() throws Exception {
    classUnderTest = new StudenteManager("erasmustracking","root","root");
    coordinatoreManager = new CoordinatoriManager("erasmustracking","root","root");
    m =new AccountManager("erasmusTracking","root","root");
  }

  @Test
  void doSave() throws SQLException {
    System.out.println("doSave");

    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(1);

    boolean ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());

    coordinatore = list.get(list.size()-1);


    System.out.println("doRetrieveByIdCoordinatore");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, list.size());
    bean = list1.get(list1.size()-1);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    //bean con data nascita e luogo nascita null
    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(1);

     ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, list1.size());
    bean = list1.get(list1.size()-1);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doDelete() {
    System.out.println("doDelete");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    ArrayList<Studente> list = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, list.size());
    bean = list.get(list.size()-1);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);

    /**bean vuoto*/
    ok= false;
    bean = new Studente();
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }
  }

  @Test
  void doRetrieveAll() {
    System.out.println("doRetrieveAll");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    List<Studente> list = classUnderTest.doRetrieveAll();
    assertNotEquals(0,list.size());
    bean = list.get(list.size()-1);

    try{

      assertEquals(2,list.size());

    }catch(Exception e){
      e.printStackTrace();
    }

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveById() {
    System.out.println("doRetrieveById");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    List<Studente> list = classUnderTest.doRetrieveAll();
    assertNotEquals(0,list.size());
    bean = list.get(list.size()-1);

    Studente ris = new Studente();
    ok = false;
    try{
      ris = classUnderTest.doRetrieveById(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void doRetrieveByIdCoordinatore() {
    System.out.println("doRetrieveByIdCoordninatore");


    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(1);

    boolean ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());

    coordinatore = list.get(list.size()-1);



    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    List<Studente> list1 = (List<Studente>) classUnderTest.doRetrieveByCoordinatore(bean.getIdCoordinatore());
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());
    Iterator<Studente> i = list1.iterator();

    ok = true;
    while (i.hasNext()) {
      Studente bean = i.next();
      if (bean.getIdCoordinatore() != coordinatore.getId()) {
        ok = false;
      }
    }
    assertTrue(ok);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);


  }

  @Test
  void doRetrieveByMatricola() {
    System.out.println("doRetrieveByMatricola");

    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(1);

    boolean ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());

    coordinatore = list.get(list.size()-1);


    System.out.println("doRetrieveByIdCoordninatore");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    bean = classUnderTest.doRetrieveByMatricola(bean.getMatricola());

    ok = true;
    if (!matricola.equals(bean.getMatricola())) {
      ok = false;
    }

    assertTrue(ok);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveByEmail() {
    System.out.println("doRetrieveByEmail");

    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(1);

    boolean ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());

    coordinatore = list.get(list.size()-1);


    System.out.println("doRetrieveByIdCoordninatore");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list.size());

    Studente ris = classUnderTest.doRetrieveByEmail("aleoale@live.it");

    ok = true;
    if (!ris.equals(bean)) {
      ok = false;
    }



    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoUpdate() {
    System.out.println("doUpdate");

    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(1);

    boolean ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());

    coordinatore = list.get(list.size()-1);


    System.out.println("doRetrieveByIdCoordninatore");

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list.size());
    bean.setNome("Faber");
    bean.setCognome("Bell");
    bean.setEmail("bell92@gmail.com");
    bean.setPassword("olè");

    try{
      classUnderTest.doUpdate(bean);
      ok = true;

    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);



    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }
}