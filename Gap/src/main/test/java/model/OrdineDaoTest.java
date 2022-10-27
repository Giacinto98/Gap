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

import bean.ComposizioneBean;
import bean.OrdineBean;
import model.OrdineModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdineDaoTest {
    private static IDatabaseTester tester;
    private OrdineModel ordineDAO;

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
        refreshDataSet("C:/Users/giaci/git/Gap/Gap/db/initOrdine.xml"); 
        ordineDAO = new OrdineModel(tester.getConnection().getConnection());
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        tester.onTearDown();
    }
    
    
    @Test
    public void testRestituzioneOrdine() throws Exception {
        OrdineBean ordine = new OrdineBean();
        ordine.setEmail("giaci95@live.it");
		ordine.setNumeroOrdine(1);
		ordine.setNumeroProdotti(2);
		LocalDate data = LocalDate.of(2021, 12, 12);
		ordine.setDataOrdine(data);
		ordine.setPrezzoTotale(1500);
		ordine.setNumeroCarta("1542569857451256");
		ordine.setMeseScadenzaCarta("03");
		ordine.setAnnoScadenzaCarta("2025");
		ordine.setCvvCarta("845");
        
        OrdineBean ordine1 = new OrdineBean();
        ordine1.setEmail("marko65@live.it");
     	ordine1.setNumeroOrdine(2);
     	ordine1.setNumeroProdotti(1);
     	LocalDate data1 = LocalDate.of(2020, 03, 24);
     	ordine1.setDataOrdine(data1);
     	ordine1.setPrezzoTotale(2000);
     	ordine1.setNumeroCarta("1475569857451256");
     	ordine1.setMeseScadenzaCarta("09");
     	ordine1.setAnnoScadenzaCarta("2026");
     	ordine1.setCvvCarta("654");
        
        
        List<OrdineBean> expectedOrdini = new ArrayList<>();
        expectedOrdini.add(ordine);
        expectedOrdini.add(ordine1);
        //con la attuale riga di codice non va, funziona con la riga di codice
        //commentata
        List<OrdineBean> actualOrdini = (List<OrdineBean>) ordineDAO.doRetriveAll(null);
        //List<OrdineBean> actualOrdini = (List<OrdineBean>) ordineDAO.doRetriveAll();
        for(int i=0; i<actualOrdini.size(); i++)
        {
     	   assertEquals(actualOrdini.get(i).getAnnoScadenzaCarta(),expectedOrdini.get(i).getAnnoScadenzaCarta());
     	   assertEquals(actualOrdini.get(i).getCvvCarta(),expectedOrdini.get(i).getCvvCarta());
     	   assertEquals(actualOrdini.get(i).getDataOrdine(),expectedOrdini.get(i).getDataOrdine());
     	   assertEquals(actualOrdini.get(i).getEmail(),expectedOrdini.get(i).getEmail());
     	   assertEquals(actualOrdini.get(i).getMeseScadenzaCarta(),expectedOrdini.get(i).getMeseScadenzaCarta());
     	   assertEquals(actualOrdini.get(i).getNumeroOrdine(),expectedOrdini.get(i).getNumeroOrdine());
     	   assertEquals(actualOrdini.get(i).getNumeroCarta(),expectedOrdini.get(i).getNumeroCarta());
     	   assertEquals(actualOrdini.get(i).getNumeroProdotti(),expectedOrdini.get(i).getNumeroProdotti());
     	   assertEquals(actualOrdini.get(i).getPrezzoTotale(),expectedOrdini.get(i).getPrezzoTotale());
        }     
    }
    
    
   @Test
    public void testAggiuntaOrdine() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/inserimentoOrdineExpected.xml"))
    			 .getTable("ORDINE");
    	 ITable tabellaAspettataComposizione = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/inserimentoOrdineExpected.xml"))
    			 .getTable("COMPOSIZIONE");
    	 OrdineBean ordine = new OrdineBean();
    	 ordine.setNumeroOrdine(3);
    	 ordine.setEmail("marko65@live.it");
    	 ordine.setPrezzoTotale(200);
    	 ordine.setNumeroProdotti(2);
    	 LocalDate data = LocalDate.of(2021, 12, 30);
    	 ordine.setDataOrdine(data);
    	 ordine.setNumeroCarta("1475569857451256");
    	 ordine.setAnnoScadenzaCarta("2026");
    	 ordine.setMeseScadenzaCarta("09");
    	 ordine.setCvvCarta("654");
    	 ordineDAO.doSave(ordine);
    	 
    	 ComposizioneBean composto = new ComposizioneBean();
    	 composto.setNumeroOrdine(3);
    	 composto.setCodiceProdotto(1);
    	 composto.setQuantita(2);
    	 composto.setIdMateriale(1);
    	 ordineDAO.doSaveComposizione(composto);
    	 
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("ORDINE");
    	 ITable tabellaAttualeComposizone = tester.getConnection().createDataSet().getTable("COMPOSIZIONE");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    	 Assertion.assertEquals(new SortedTable(tabellaAspettataComposizione), new SortedTable(tabellaAttualeComposizone));
    } 
}