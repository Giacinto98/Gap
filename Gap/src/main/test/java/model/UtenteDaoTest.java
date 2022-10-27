package main.test.java.model;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bean.UtenteBean;
import model.UtenteModel;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Serve dipendenza dal vecchio junit, purtroppo, è un problema di DBUnit che dipende da una vecchia classe
public class UtenteDaoTest {
    private static IDatabaseTester tester;
    private UtenteModel utenteDAO;

    @BeforeAll
    static void setUpAll() throws ClassNotFoundException {
      tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'C:/Users/giaci/git/Gap/Gap/db/schema.sql'","sa","");
       tester.setSetUpOperation(DatabaseOperation.REFRESH);
        tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        //JdbcDataSource ds = new JdbcDataSource(); 
    }

    private static void refreshDataSet(String filename) throws Exception {
    	IDataSet initialState = new FlatXmlDataSetBuilder().build(new File(filename));
        tester.setDataSet(initialState);
        tester.onSetup();
    }

    @BeforeEach
    public void setUp() throws Exception {
        refreshDataSet("C:/Users/giaci/git/Gap/Gap/db/init.xml"); 
        
        utenteDAO = new UtenteModel(tester.getConnection().getConnection());
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        tester.onTearDown();
    }
    
    
    @Test
    public void testModificaPassword() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/ModificaPasswordExpected.xml"))
    			 .getTable("UTENTE");
    	 UtenteBean utenteModificato = new UtenteBean();
    	 utenteModificato.setCf("dnlgnt98a05g813p");
    	 utenteModificato.setCognome("Giacinto");
    	 utenteModificato.setNome("Adinolfi");
    	 utenteModificato.setEmail("giaci95@live.it");
    	 utenteModificato.setPassword("Milka25@live");
    	 utenteModificato.setTelefono("2365412569");
    	 utenteModificato.setIndirizzo("Via alcide 24");
    	 utenteDAO.doUpdate(utenteModificato);
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("UTENTE");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    }
    
    
    @Test
    public void testRegistrazione() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/RegExpected.xml"))
    			 .getTable("UTENTE");
    	 ITable tabellaAspettataRuolo = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/RegExpected.xml"))
    			 .getTable("UTENTE_Ruolo");
    	 
    	 UtenteBean utenteInserito = new UtenteBean();
    	 utenteInserito.setCf("dnlgnt95a05g813p");
    	 utenteInserito.setCognome("Kalifa");
    	 utenteInserito.setNome("Marco");
    	 utenteInserito.setEmail("marko65@live.it");
    	 utenteInserito.setPassword("Marco14@live");
    	 utenteInserito.setTelefono("3256415789");
    	 utenteInserito.setIndirizzo("Via Fiumana 25");
    	 utenteDAO.doSave(utenteInserito);
    	 UtenteRuolo utenteRuolo = new UtenteRuolo ();
    	 
    	 utenteRuolo.setEmail("marko65@live.it");
    	 utenteRuolo.setRuolo("utente");
    	 
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("UTENTE");
    	 ITable tabellaAttualeRuolo = tester.getConnection().createDataSet().getTable("UTENTE_RUOLO");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    	 Assertion.assertEquals(new SortedTable(tabellaAspettataRuolo), new SortedTable(tabellaAttualeRuolo));
    }
    
    @Test
    public void testLoginErratoTC1_3 () throws Exception {
    	UtenteBean utenteCercato = new UtenteBean();
    	utenteCercato.setEmail("franco95@live.it");
    	utenteCercato.setPassword("root");
    	UtenteBean utenteTrovato =  new UtenteBean();
    	utenteTrovato = utenteDAO.cercaUtente(utenteCercato.getEmail(), utenteCercato.getPassword());
    	assertEquals("", utenteTrovato.getEmail());
    	assertEquals("", utenteTrovato.getPassword());
    }
    
    @Test
    public void testLoginErratoTC1_2 () throws Exception {
    	UtenteBean utenteCercato = new UtenteBean();
    	utenteCercato.setEmail("giaci95@live.it");
    	utenteCercato.setPassword("ciao");
    	UtenteBean utenteTrovato =  new UtenteBean();
    	utenteTrovato = utenteDAO.cercaUtente(utenteCercato.getEmail(), utenteCercato.getPassword());
    	assertEquals("", utenteTrovato.getEmail());
    	assertEquals("", utenteTrovato.getPassword());

    }
    
    @Test
    public void testLoginTC1_1 () throws Exception {
    	UtenteBean utenteCercato = new UtenteBean();
    	utenteCercato.setEmail("giaci95@live.it");
    	utenteCercato.setPassword("root");
    	UtenteBean utenteTrovato =  new UtenteBean();
    	utenteTrovato = utenteDAO.cercaUtente(utenteCercato.getEmail(), utenteCercato.getPassword());
    	assertEquals(utenteCercato.getEmail(), utenteTrovato.getEmail());
    	assertEquals(utenteCercato.getPassword(), utenteTrovato.getPassword());
    }
    
    private class UtenteRuolo {
    	private String email;
    	private String ruolo;
    	
    	public UtenteRuolo(){
    	}
    	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public void setRuolo(String ruolo) {
			this.ruolo = ruolo;
		}
    }
   
}