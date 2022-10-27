package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import bean.ComposizioneBean;
import bean.OrdineBean;
import bean.UtenteBean;

public class OrdineModel implements InterfacciaDAO <OrdineBean>{

private Connection connection = null;
private DataSource ds = null;
	
	public OrdineModel (DataSource ds)
	{
		this.ds = ds;
	}
	
	public OrdineModel(Connection con) { 
		this.connection = con;
	}
	
	//query web app
	@Override
	public void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String updateSQL = "insert into ordine (Num_Ordine,Email,Prezzo_Totale,Num_Prodotti,data_Ordine, NumeroCarta, MeseScadenzaCarta, AnnoScadenzaCarta, cvv) values ("+ ordine.getNumeroOrdine() +",'"+ordine.getEmail()+"',"+ordine.getPrezzoTotale()+","+ordine.getNumeroProdotti()+",'"+ordine.getDataOrdine()+"'" + ",'"+ordine.getNumeroCarta()+"'" + ",'"+ordine.getMeseScadenzaCarta()+"'" + ",'"+ordine.getAnnoScadenzaCarta()+"'" + ",'"+ordine.getCvvCarta()+"');";                                                                        
		try  
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(updateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
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
	public void doSave(OrdineBean ordine) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateSQL = "insert into ordine (Num_Ordine,Email,Prezzo_Totale,Num_Prodotti,data_Ordine, NumeroCarta, MeseScadenzaCarta, AnnoScadenzaCarta, cvv) values ("+ ordine.getNumeroOrdine() +",'"+ordine.getEmail()+"',"+ordine.getPrezzoTotale()+","+ordine.getNumeroProdotti()+",'"+ordine.getDataOrdine()+"'" + ",'"+ordine.getNumeroCarta()+"'" + ",'"+ordine.getMeseScadenzaCarta()+"'" + ",'"+ordine.getAnnoScadenzaCarta()+"'" + ",'"+ordine.getCvvCarta()+"');";                                                                        
		try  
		{
			preparedStatement = connection.prepareStatement(updateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			preparedStatement.executeUpdate(); //esguiamo la query come facevamo nanche in db del primo sempestre
		}
		finally { //rilasiamo tutte le risorse che abbiamo
			
			if(preparedStatement != null)
				preparedStatement.close();
		}
	}*/
	
	
	//query web app
	public void doSaveComposizione(ComposizioneBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String updateSQL = "insert into composizione (Num_ordine,codice,quantita,Id_Materiale) values ("+item.getNumeroOrdine()+","+item.getCodiceProdotto()+","+item.getQuantita()+","+item.getIdMateriale()+");";
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(updateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
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
	
	
	//query  DBunit
	/*public void doSaveComposizione(ComposizioneBean item) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateSQL = "insert into composizione (Num_ordine,codice,quantita,Id_Materiale) values ("+item.getNumeroOrdine()+","+item.getCodiceProdotto()+","+item.getQuantita()+","+item.getIdMateriale()+");";
		try
		{
			preparedStatement = connection.prepareStatement(updateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
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
	
	
public Collection<ComposizioneBean> composizioneOrdine(String order) throws SQLException
{
	Connection connection = null; //creo connessione 
	PreparedStatement preparedStatement = null;
	String selectSQL = "select * from composizione where num_ordine='"+order+"';";
	Collection<ComposizioneBean> composizioni = new LinkedList<ComposizioneBean>();		
	try {
		connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
		preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
		ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
		while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
		{
			ComposizioneBean bean = new ComposizioneBean();
			bean.setCodiceProdotto(rs.getInt("codice"));
			bean.setIdMateriale(rs.getInt("id_materiale"));
			bean.setNumeroOrdine(rs.getInt("Num_Ordine"));
			bean.setQuantita(rs.getInt("Quantita"));
			composizioni.add(bean);
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
	return composizioni;
}
	
	@Override
	public OrdineBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Collection<OrdineBean> doRetriveAllForUtente(UtenteBean utente) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from ordine where email = '"+utente.getEmail()+"'order by data_Ordine desc;";
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>(); //arrey di prodotti che vengono recuperati dal database e poi inseriti nell'array
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				OrdineBean bean = new OrdineBean();
				bean.setEmail(rs.getString("email"));
				bean.setNumeroOrdine(rs.getInt("num_ordine"));
				bean.setNumeroProdotti(rs.getInt("num_prodotti"));
				String data = rs.getString("data_Ordine");
				LocalDate localDate = LocalDate.parse(data);
				bean.setDataOrdine(localDate);
				bean.setPrezzoTotale(rs.getFloat("Prezzo_Totale"));
				bean.setNumeroCarta(rs.getString("NumeroCarta"));
				bean.setMeseScadenzaCarta(rs.getString("MeseScadenzaCarta"));
				bean.setAnnoScadenzaCarta(rs.getString("AnnoScadenzaCarta"));
				bean.setCvvCarta(rs.getString("Cvv"));
				ordini.add(bean);
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
		return ordini;
	}

	//query webb app
	@Override
	public Collection<OrdineBean> doRetriveAll(String order) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from ordine order by data_Ordine desc;";
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>(); //arrey di prodotti che vengono recuperati dal database e poi inseriti nell'array
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				OrdineBean bean = new OrdineBean();
				bean.setEmail(rs.getString("email"));
				bean.setNumeroOrdine(rs.getInt("num_ordine"));
				bean.setNumeroProdotti(rs.getInt("num_prodotti"));
				String data = rs.getString("data_Ordine");
				LocalDate localDate = LocalDate.parse(data);
				bean.setDataOrdine(localDate);
				bean.setPrezzoTotale(rs.getFloat("Prezzo_Totale"));
				bean.setNumeroCarta(rs.getString("NumeroCarta"));
				bean.setMeseScadenzaCarta(rs.getString("MeseScadenzaCarta"));
				bean.setAnnoScadenzaCarta(rs.getString("AnnoScadenzaCarta"));
				bean.setCvvCarta(rs.getString("Cvv"));
				ordini.add(bean);
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
		return ordini;
	}
	
	
	//query DBuni
	/*public List<OrdineBean> doRetriveAll() throws SQLException {
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from ordine order by data_Ordine desc;";
		List<OrdineBean> ordini = new ArrayList<>(); //arrey di prodotti che vengono recuperati dal database e poi inseriti nell'array
		try {
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				OrdineBean bean = new OrdineBean();
				bean.setEmail(rs.getString("email"));
				bean.setNumeroOrdine(rs.getInt("num_ordine"));
				bean.setNumeroProdotti(rs.getInt("num_prodotti"));
				String data = rs.getString("data_Ordine");
				LocalDate localDate = LocalDate.parse(data);
				bean.setDataOrdine(localDate);
				bean.setPrezzoTotale(rs.getFloat("Prezzo_Totale"));
				bean.setNumeroCarta(rs.getString("NumeroCarta"));
				bean.setMeseScadenzaCarta(rs.getString("MeseScadenzaCarta"));
				bean.setAnnoScadenzaCarta(rs.getString("AnnoScadenzaCarta"));
				bean.setCvvCarta(rs.getString("Cvv"));
				ordini.add(bean);
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
		return ordini;
	}*/
	

	@Override
	public void doUpdate(OrdineBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(OrdineBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
