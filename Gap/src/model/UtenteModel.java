package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import bean.UtenteBean;

public class UtenteModel implements InterfacciaDAO<UtenteBean> {

	private DataSource ds = null;
	private Connection connection = null;
	
	public UtenteModel (DataSource ds) { 
		this.ds = ds;
	}
	
	public UtenteModel (Connection ds) { 
		this.connection = ds;
	}
	
	
	@Override
	public UtenteBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		//inserire join per prendere ruolo nellla stringa
		String selectSQL = "select * from utente where Email='"+code+"';";
		UtenteBean user = new UtenteBean(); 
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				user.setCf(rs.getString("CF"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Passw"));
				user.setIndirizzo(rs.getString("Indirizzo_Fatturazione"));
				user.setTelefono(rs.getString("Telefono"));
				user.setRuolo("");
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
		return user; //ritorna l'utente cercato all'interno del database	
	}
	
	//query dbunit
	/*public UtenteBean cercaUtente (String email, String password) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from utente, ruolo, utente_ruolo where utente.email=utente_ruolo.email and utente_ruolo.ruolo=ruolo.nome and utente.Email ='"+email+"' and utente.passw='"+password+"';";
		UtenteBean user = new UtenteBean(); 
		
		try {
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				user.setCf(rs.getString("CF"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Passw"));
				user.setIndirizzo(rs.getString("Indirizzo_Fatturazione"));
				user.setTelefono(rs.getString("Telefono"));
				user.setRuolo(rs.getString("Ruolo"));
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
		return user; //ritorna l'utente cercato all'interno del database	
	}*/
	
	
	//query sito web
	public UtenteBean cercaUtente (String email, String password) throws SQLException
	{
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		//inserire join per prendere ruolo nellla stringa
		String selectSQL = "select * from utente, ruolo, utente_ruolo where utente.email=utente_ruolo.email and utente_ruolo.ruolo=ruolo.nome and utente.Email ='"+email+"' and utente.passw='"+password+"';";
		UtenteBean user = new UtenteBean(); 
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				user.setCf(rs.getString("CF"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Passw"));
				user.setIndirizzo(rs.getString("Indirizzo_Fatturazione"));
				user.setTelefono(rs.getString("Telefono"));
				user.setRuolo(rs.getString("Ruolo"));
			}
			//System.out.println(user.getEmail() +"  "+ user.getPassword() );
			
		} finally { //rilasiamo tutte le risorse che abbiamo
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
		return user; //ritorna l'utente cercato all'interno del database	
	}
	
	
	public boolean cercaSimili (UtenteBean item) throws SQLException
	{
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "Select Email from Utente";
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(UpdateSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				UtenteBean bean = new UtenteBean();
				bean.setEmail(rs.getString("Email"));
				if(bean.getEmail().equals(item.getEmail()))
					return false;
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
		return true;
	}

	
	// metodo sito web
	@Override
	public void doSave(UtenteBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "Insert into Utente (CF,Nome,Cognome,Email,Passw,Indirizzo_Fatturazione,Telefono) values ('"+item.getCf()+"','"+item.getNome()+"','"+item.getCognome()+"','"+item.getEmail()+"','"+item.getPassword()+"','"+item.getIndirizzo()+"','"+item.getTelefono()+"');";
		String UpdateSQL1 = "Insert into Utente_Ruolo (Email, Ruolo) values ('"+item.getEmail()+"','utente');";
		try
		{
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(UpdateSQL);
			preparedStatement.executeUpdate();
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
			preparedStatement = connection.prepareStatement(UpdateSQL1);
			preparedStatement.executeUpdate();
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
	
	
	//metodo DBunit
	/*@Override
	public void doSave(UtenteBean item) throws SQLException {
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "Insert into Utente (CF,Nome,Cognome,Email,Passw,Indirizzo_Fatturazione,Telefono) values ('"+item.getCf()+"','"+item.getNome()+"','"+item.getCognome()+"','"+item.getEmail()+"','"+item.getPassword()+"','"+item.getIndirizzo()+"','"+item.getTelefono()+"');";
		String UpdateSQL1 = "Insert into Utente_Ruolo (Email, Ruolo) values ('"+item.getEmail()+"','utente');";
		try
		{
			preparedStatement = connection.prepareStatement(UpdateSQL);
			preparedStatement.executeUpdate();
		}
		finally { 
			if(preparedStatement != null)
				preparedStatement.close();
		}
		try
		{
			preparedStatement = connection.prepareStatement(UpdateSQL1);
			preparedStatement.executeUpdate();
		}
		finally {
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			} finally {
			if(connection != null)
				connection.close();
			}
		}
	}*/
	
	
	//codice DBUnit
	/*@Override
	public void doUpdate(UtenteBean utente) throws SQLException {
		PreparedStatement preparedStatement = null;
		String updateSQL ="update utente set passw = '"+ utente.getPassword()+"' where email = '"+utente.getEmail()+"';";
		try {
			preparedStatement = connection.prepareStatement(updateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
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
	
	
	//codice per web app
	@Override
	public void doUpdate(UtenteBean utente) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String updateSQL ="update utente set passw = '"+ utente.getPassword()+"' where email = '"+utente.getEmail()+"';";
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(updateSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
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
	

	@Override
	public void doDelete(UtenteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Collection<UtenteBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
