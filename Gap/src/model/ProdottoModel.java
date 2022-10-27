package model;
//classe che implementa le operazioni CRUD

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import bean.CompostoBean;
import bean.MaterialeBean;
import bean.ProdottoBean;

public class ProdottoModel implements InterfacciaDAO <ProdottoBean> 
{
	private DataSource ds = null; //instanziamo un datasource che ci serve per recuperare la connessione
	private Connection connection = null;
	
	public ProdottoModel(DataSource ds) { 
		this.ds = ds;
	}
	
	public ProdottoModel(Connection con) { 
		this.connection = con;
	}
	
	public Collection<ProdottoBean> doRetriveAllTipologia(String tipologia) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "select * from prodotto where tipologia = '"+tipologia+"' order by sconto desc;";
		
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>(); //arrey di prodotti che vengono recuperati dal database e poi inseriti nell'array
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				ProdottoBean bean = new ProdottoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
				prodotti.add(bean);
			}
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return prodotti; //ritorniamo la lista dei prodotti presi dal database.
	}
	
	
	public MaterialeBean doRetriveByKeyMateriale (String id) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "Select * from materiale where id = '"+ id +"';";
		MaterialeBean materiale = new MaterialeBean ();
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				materiale.setId(rs.getInt("Id"));
				materiale.setTipologiaMateriale(rs.getString("Tipologia_Materiale"));
				materiale.setColore(rs.getString("Colore"));
			}
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return materiale;
	}
	
	
	public Collection<MaterialeBean> doRetriveAllMateriale (String nomeProdotto) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "Select * from prodotto, materiale, composto where prodotto.nome='"+nomeProdotto+"' and prodotto.codice=composto.codice and materiale.id=composto.id;";
		Collection<MaterialeBean> materiali = new LinkedList<MaterialeBean>();
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				MaterialeBean materiale = new MaterialeBean();
				materiale.setId(rs.getInt("Id"));
				materiale.setTipologiaMateriale(rs.getString("Tipologia_Materiale"));
				materiale.setColore(rs.getString("Colore"));
				materiali.add(materiale);
			}
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return materiali;
	}
	
	
	public ProdottoBean doRetrieveByCodice(int codice) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String selectSQL = "select distinct * from prodotto where codice = '"+ codice +"';";
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
			}
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return bean; //ritorniamo la lista dei prodotti presi dal database.
	}
	
	
	//query sito web
	@Override
	public ProdottoBean doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String selectSQL = "select * from prodotto where Nome = '"+ nome +"';";
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
			}
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return bean; //ritorniamo la lista dei prodotti presi dal database.
	}
	
	//metodo DBunit
	/*@Override
	public ProdottoBean doRetrieveByKey(String nome) throws SQLException {
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String selectSQL = "select * from prodotto where Nome = '"+ nome +"';";
		
		try {
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
			}
		} finally {
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return bean; //ritorniamo la lista dei prodotti presi dal database.
	}*/
	

	@Override
	public Collection<ProdottoBean> doRetriveAll(String order) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from prodotto order by sconto desc;";
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>(); //arrey di prodotti che vengono recuperati dal database e poi inseriti nell'array
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				ProdottoBean bean = new ProdottoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
				prodotti.add(bean);
			}
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return prodotti; //ritorniamo la lista dei prodotti presi dal database.
	}
	
	//query web app
	@Override
	public void doSave(ProdottoBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String insertSQL = "Insert into prodotto (Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('"+item.getNome()+"',"+item.getAltezza()+","+item.getProfondita()+","+item.getLarghezza()+",'"+item.getTipologia()+"',"+item.getQuantita()+","+item.getPrezzo()+","+item.getSconto()+");";

		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(insertSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(insertSQL); //esguiamo la query come facevamo nanche in db del primo sempestre
			
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}	
	}
	
	//query DBunit
	/*@Override
	public void doSave(ProdottoBean item) throws SQLException {
		PreparedStatement preparedStatement = null;
		String insertSQL = "Insert into prodotto (codice,Nome,Altezza,Profondita,Larghezza,Tipologia,Quantita,Prezzo,Sconto) values ('"+item.getCodice()+"','"+item.getNome()+"',"+item.getAltezza()+","+item.getProfondita()+","+item.getLarghezza()+",'"+item.getTipologia()+"',"+item.getQuantita()+","+item.getPrezzo()+","+item.getSconto()+");";
		preparedStatement = connection.prepareStatement(insertSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
		preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		if(preparedStatement != null)
			preparedStatement.close();
	}*/
	
	//query web app
	public void doSaveComposizioneProdotto(CompostoBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String insertSQL = "Insert into Composto (Id,Codice) values ("+item.getIdMateriale()+","+item.getCodiceProdotto()+");";
	try {
		connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
		preparedStatement = connection.prepareStatement(insertSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
		preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		
	} finally { //rilasiamo tutte le risorse che abbiamo
		try {
		if(preparedStatement != null)
			preparedStatement.close();
		} finally {
		if(connection != null)
			connection.close();
		}
	}		
	}
	
	//query DBunit
	/*public void doSaveComposizioneProdotto(CompostoBean item) throws SQLException {
		PreparedStatement preparedStatement = null;
		String insertSQL = "Insert into Composto (Id,Codice) values ("+item.getIdMateriale()+","+item.getCodiceProdotto()+");";
	try {
		preparedStatement = connection.prepareStatement(insertSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
		preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		
	} finally { //rilasiamo tutte le risorse che abbiamo
		try {
		if(preparedStatement != null)
			preparedStatement.close();
		} finally {
		if(connection != null)
			connection.close();
		}
	}		
	}*/
	
	//query web app
	public void doUpdateCatalogo (ProdottoBean item) throws SQLException
	{
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "Update prodotto set quantita="+item.getQuantita()+",sconto="+item.getSconto()+",prezzo="+item.getPrezzo()+" where codice = "+item.getCodice()+";";
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(UpdateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
	}
	
	//query DBunit
	/*public void doUpdateCatalogo (ProdottoBean item) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "Update prodotto set quantita="+item.getQuantita()+",sconto="+item.getSconto()+",prezzo="+item.getPrezzo()+" where codice = "+item.getCodice()+";";
		try
		{
			preparedStatement = connection.prepareStatement(UpdateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
	}*/
	
	
	public void doUpdateQuantita(ProdottoBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String SelectSQL = "select * from prodotto where codice = "+item.getCodice()+";";
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(SelectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
			}
			
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
			
		
		try
		{
			int quantita = (bean.getQuantita() - item.getQuantita());
			String UpdateSQL = "Update prodotto set quantita = '"+quantita+"' where codice = '"+item.getCodice()+"';";
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(UpdateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		
	}
	
	public int restituisciQuantita(ProdottoBean prodotto) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String SelectSQL = "select * from prodotto where codice = "+prodotto.getCodice()+";";
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(SelectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
			}
			
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return bean.getQuantita();
	}
	
	@Override
	public void doUpdate (ProdottoBean item){
	
	}

	//query web app
	@Override
	public void doDelete(ProdottoBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "DELETE FROM prodotto WHERE codice="+item.getCodice()+";";
		String UpdateSQL1 = "DELETE FROM composto WHERE codice="+item.getCodice()+";";
		
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(UpdateSQL1); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(UpdateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
	}
	
	//query DBunit
	/*@Override
	public void doDelete(ProdottoBean item) throws SQLException {
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "DELETE FROM prodotto WHERE codice="+item.getCodice()+";";
		String UpdateSQL1 = "DELETE FROM composto WHERE codice="+item.getCodice()+";";
		
		try
		{
			preparedStatement = connection.prepareStatement(UpdateSQL1); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			if(preparedStatement != null)
				preparedStatement.close();
		}
		try
		{
			preparedStatement = connection.prepareStatement(UpdateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			if(preparedStatement != null)
			
			if(connection != null)
			connection.close();
		}
	}*/
}
