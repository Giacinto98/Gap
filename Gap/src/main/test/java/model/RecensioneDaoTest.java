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
import bean.ProdottoBean;
import bean.RecensioneBean;
import model.OrdineModel;
import model.RecensioneModel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RecensioneDaoTest {
    private static IDatabaseTester tester;
    private RecensioneModel recensioneDAO;

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
        refreshDataSet("C:/Users/giaci/git/Gap/Gap/db/initRecensione.xml"); 
        recensioneDAO = new RecensioneModel(tester.getConnection().getConnection());
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        tester.onTearDown();
    }
    
   @Test
    public void testAggiuntaRecensione() throws Exception {
    	 ITable tabellaAspettata = new FlatXmlDataSetBuilder()
    			 .build(new File("C:/Users/giaci/git/Gap/Gap/db/inserimentoRecensioneExpected.xml"))
    			 .getTable("RECENSIONE");	 
    	 RecensioneBean recensione = new RecensioneBean();
    	 recensione.setEmail("franco@live.it");
    	 recensione.setTesto("Ottimo prodotto");
    	 recensione.setData("2021-05-01");
    	 recensione.setCodice(2);
    	 recensioneDAO.doSave(recensione); 
    	 ITable tabellaAttuale = tester.getConnection().createDataSet().getTable("RECENSIONE");
    	 Assertion.assertEquals(new SortedTable(tabellaAspettata), new SortedTable(tabellaAttuale));
    } 
   
   @Test
   public void testRecensioniDiUnProdotto() throws Exception {
       // Prepara output atteso
       RecensioneBean recensione = new RecensioneBean();
       recensione.setCodice(1);
       recensione.setData("2021-05-06");
       recensione.setEmail("franco25@live");
       recensione.setTesto("Fa un po pena");
       
       RecensioneBean recensione1 = new RecensioneBean();
       recensione1.setCodice(1);
       recensione1.setData("2020-05-06");
       recensione1.setEmail("marko25@live");
       recensione1.setTesto("Ottimo prodotto di arredamento");
       
       
       List<RecensioneBean> expectedRecensioni = new ArrayList<>();
       expectedRecensioni.add(recensione1);
       expectedRecensioni.add(recensione);
       List<RecensioneBean> actualRecensioni = (List<RecensioneBean>) recensioneDAO.doRetriveAll("1");
       for(int i=0; i<actualRecensioni.size(); i++)
       {
    	   assertEquals(actualRecensioni.get(i).getCodice(),expectedRecensioni.get(i).getCodice());
    	   assertEquals(actualRecensioni.get(i).getData(),expectedRecensioni.get(i).getData());
    	   assertEquals(actualRecensioni.get(i).getEmail(),expectedRecensioni.get(i).getEmail());
    	   assertEquals(actualRecensioni.get(i).getTesto(),expectedRecensioni.get(i).getTesto());
        }     
   }
}