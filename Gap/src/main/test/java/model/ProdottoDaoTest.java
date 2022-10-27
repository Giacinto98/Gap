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

import bean.CompostoBean;
import bean.ProdottoBean;
import bean.UtenteBean;
import model.ProdottoModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

public class ProdottoDaoTest {
    private static IDatabaseTester tester;
    private ProdottoModel prodottoDAO;

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
        refreshDataSet("C:/Users/giaci/git/Gap/Gap/db/initGestoreCatalogo.xml"); 
        
        prodottoDAO = new ProdottoModel(tester.getConnection().getConnection());
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        tester.onTearDown();
    }
    
    @Test
    public void testRicercaProdottoTC7_3 () throws Exception {
    	ProdottoBean prodottoCercato = new ProdottoBean();
    	prodottoCercato.setNome("cadex");
    	ProdottoBean prodottoTrovato =  new ProdottoBean();
    	prodottoTrovato = prodottoDAO.doRetrieveByKey(prodottoCercato.getNome());
    	assertEquals("", prodottoTrovato.getNome());
    }
    
    @Test
    public void testRicercaProdottoTC7_2 () throws Exception {
    	ProdottoBean prodottoCercato = new ProdottoBean();
    	prodottoCercato.setNome("");
    	ProdottoBean prodottoTrovato =  new ProdottoBean();
    	prodottoTrovato = prodottoDAO.doRetrieveByKey(prodottoCercato.getNome());
    	assertEquals("", prodottoTrovato.getNome());
    }
    
    @Test
    public void testRicercaProdottoTC7_1 () throws Exception {
    	ProdottoBean prodottoCercato = new ProdottoBean();
    	prodottoCercato.setNome("zeus");
    	ProdottoBean prodottoTrovato =  new ProdottoBean();
    	prodottoTrovato = prodottoDAO.doRetrieveByKey(prodottoCercato.getNome());
    	assertEquals(prodottoCercato.getNome(), prodottoTrovato.getNome());
    }
    
    
    @Test
    public void testModificaProdottoTC6_2() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/initGestoreCatalogo.xml"))
    			 .getTable("PRODOTTO");
    	 ProdottoBean prodotto = new  ProdottoBean();
    	 prodotto.setCodice(57);
    	 prodotto.setPrezzo(150);    	 
    	 prodotto.setQuantita(550);
    	 prodotto.setSconto(50);
    	 prodottoDAO.doUpdateCatalogo(prodotto);
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("PRODOTTO");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    }
    
    
    @Test
    public void testModificaProdotto() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/modificaProdottoExpected.xml"))
    			 .getTable("PRODOTTO");
    	 ProdottoBean prodotto = new  ProdottoBean();
    	 prodotto.setCodice(2);
    	 prodotto.setPrezzo(150);    	 
    	 prodotto.setQuantita(550);
    	 prodotto.setSconto(50);
    	 prodottoDAO.doUpdateCatalogo(prodotto);
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("PRODOTTO");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    }
    
    @Test
    public void testRimozioneProdottoTC5_2() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/initGestoreCatalogo.xml"))
    			 .getTable("PRODOTTO");
    	 ITable tabellaAspettataComposizione = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/initGestoreCatalogo.xml"))
    			 .getTable("COMPOSTO");
    	 ProdottoBean prodotto = new ProdottoBean();
    	 prodotto.setCodice(56);
    	 CompostoBean composto = new CompostoBean();
    	 composto.setCodiceProdotto(56);
    	 prodottoDAO.doDelete(prodotto);
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("PRODOTTO");
    	 ITable tabellaAttualeComposizone = tester.getConnection().createDataSet().getTable("COMPOSTO");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    	 Assertion.assertEquals(new SortedTable(tabellaAspettataComposizione), new SortedTable(tabellaAttualeComposizone));
    }
    
    @Test
    public void testRimozioneProdottoTC5_1() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/rimozioneProdottoExpected.xml"))
    			 .getTable("PRODOTTO");
    	 ITable tabellaAspettataComposizione = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/rimozioneProdottoExpected.xml"))
    			 .getTable("COMPOSTO");
    	 ProdottoBean prodotto = new ProdottoBean();
    	 prodotto.setCodice(2);
    	 CompostoBean composto = new CompostoBean();
    	 composto.setCodiceProdotto(2);
    	 prodottoDAO.doDelete(prodotto);
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("PRODOTTO");
    	 ITable tabellaAttualeComposizone = tester.getConnection().createDataSet().getTable("COMPOSTO");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    	 Assertion.assertEquals(new SortedTable(tabellaAspettataComposizione), new SortedTable(tabellaAttualeComposizone));
    }
    
    
    @Test
    public void testAggiuntaProdotto() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/gestoreCatalogoExpected.xml"))
    			 .getTable("PRODOTTO");
    	 ITable tabellaAspettataComposizione = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/gestoreCatalogoExpected.xml"))
    			 .getTable("COMPOSTO");
    	 ProdottoBean prodotto = new ProdottoBean();
    	 prodotto.setAltezza(155);
    	 prodotto.setCodice(3);
    	 prodotto.setLarghezza(350);
    	 prodotto.setNome("ciao");
    	 prodotto.setPrezzo(250);
    	 prodotto.setProfondita(140);
    	 prodotto.setQuantita(50);
    	 prodotto.setSconto(5);
    	 prodotto.setTipologia("classica");
    	 prodottoDAO.doSave(prodotto);
    	 
    	 CompostoBean composto = new CompostoBean();
    	 composto.setCodiceProdotto(3);
    	 composto.setIdMateriale(1);
    	 prodottoDAO.doSaveComposizioneProdotto(composto);
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("PRODOTTO");
    	 ITable tabellaAttualeComposizone = tester.getConnection().createDataSet().getTable("COMPOSTO");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    	 Assertion.assertEquals(new SortedTable(tabellaAspettataComposizione), new SortedTable(tabellaAttualeComposizone));
    } 
}
