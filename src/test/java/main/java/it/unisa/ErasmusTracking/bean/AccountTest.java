package main.java.it.unisa.ErasmusTracking.bean;


import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;


import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
private Account tepk = new Account();
private Account tepkfull = new Account(12,"Dario","Scola","darioscola015@gmail.com","123456","CAMPIONE");

    @Test
    void testSetandGet()  {
        Documenti documento = new Documenti(12,"doc","12012018","url",123);
        ArrayList<Documenti> docs = new ArrayList<>();
        docs.add(documento);



        assertNotNull(tepk);

        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setCognome("Scola");
        assertEquals("Scola", tepk.getCognome());

        tepk.setRuolo("CAMPIONE");
        assertEquals("CAMPIONE",tepk.getRuolo());

        tepk.setId(12);
        assertEquals(12,tepk.getId());

        tepk.setEmail("darioscola015@gmail.com");
        assertEquals("darioscola015@gmail.com",tepk.getEmail());

        tepk.setPassword("bellissimo");
        assertEquals("bellissimo",tepk.getPassword());

        tepk.setDoc(docs);
        assertEquals(docs,tepk.getDoc());

    }
    @Test
    void testaddanddeletedoc(){
        tepk= new Account();

        Documenti documento = new Documenti(12,"doc","12012018","url",123);

        tepk.addDocumento(documento);
        assertEquals(documento,tepk.getDoc().get(0));

        tepk.delDocumento(documento);

      if(tepk.getDoc().contains(documento))
          System.exit(1);




    }

    @Test
    public void equalstest(){
        Documenti documento = new Documenti(12,"doc","12012018","url",123);
        ArrayList<Documenti> docs = new ArrayList<>();
        docs.add(documento);
        tepk = new Account();
        assertFalse(tepk.equals(null));
        assertTrue(tepk.equals(tepk));
        Object o = new Object();
        assertFalse(tepk.equals(o));
        Account other = new Account();
        //entrambi not null
        tepk.setNome("Dario");
        other.setNome("Dario");

        tepk.setCognome("Scola");
        other.setCognome("Scola");

        tepk.setRuolo("CAMPIONE");
        other.setRuolo("CAMPIONE");

        tepk.setId(12);
        other.setId(12);

        tepk.setEmail("darioscola015@gmail.com");
        other.setEmail("darioscola015@gmail.com");

        tepk.setPassword("123456");
        other.setPassword("123456");


        //uno not null
        tepk.setNome(null);
        other.setNome("Dario");

        tepk.setCognome(null);
        tepk.setCognome("Scola");

        tepk.setRuolo(null);
        other.setRuolo("CAMPIONE");

        tepk.setId(0);
        other.setId(12);

        tepk.setEmail(null);
        other.setEmail("darioscola015@gmail.com");

        tepk.setPassword(null);
        other.setPassword("123456");


assertFalse(tepk.equals(other));
        //entrambi null
        tepk.setNome(null);
        other.setNome(null);

        tepk.setCognome(null);
        other.setCognome(null);

        tepk.setRuolo(null);
        other.setRuolo(null);

        tepk.setId(0);
        other.setId(0);

        tepk.setEmail(null);
        other.setEmail(null);

        tepk.setPassword(null);
        other.setPassword(null);


        assertFalse(tepk.equals(other));



    }

@Test
public String tostringtest(){

    Account tepk = new Account(); // you didn't supply the object, so I guessed
    //riempo account
    tepk.setNome("Dario");
    tepk.setCognome("Scola");
    tepk.setRuolo("CAMPIONE");
    tepk.setId(12);
    tepk.setEmail("darioscola015@gmail.com");
    tepk.setPassword("123456");


    String expected = "id= 12\n"
            +
            "nome= Dario\n"
            +
            "cognome= Scola\n"
            +
            "email= darioscola015@gmail.com\n"
            +
            "password= 123456\n"
            +
            "ruolo= CAMPIONE\n"
            +
            "Lista doc="+ tepk.getDoc()+"\n"; // put the expected value here
    assertEquals(expected, tepk.toString());
    return tepk.toString();



}

@Test
    protected Object clonetest() throws CloneNotSupportedException {
    Account tepk = new Account(); // you didn't supply the object, so I guessed
    //riempo account
    tepk.setNome("Dario");
    tepk.setCognome("Scola");
    tepk.setRuolo("CAMPIONE");
    tepk.setId(12);
    tepk.setEmail("darioscola015@gmail.com");
    tepk.setPassword("123456");
Account b = (Account) super.clone();
    assertEquals(b,tepk);


    return tepk.clone();
}

   }