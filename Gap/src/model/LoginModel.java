package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;


public class LoginModel implements InterfacciaDAO <UtenteBean> {


	private DataSource ds = null; //instanziamo un datasource che ci serve per recuperare la connessione

	public LoginModel(DataSource ds) { 
		//costruttore, quando chiamiamo productmodel
		//dobbiamo dare in input un datasource, per avere 
		//tutte le informazioni sul database
		this.ds = ds;
	}
	
	@Override
	public UtenteBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
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

	@Override
	public Collection<UtenteBean> doRetriveAll(String order) throws SQLException {
		return null;
	}
	

	@Override
	public void doSave(UtenteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(UtenteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(UtenteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}

