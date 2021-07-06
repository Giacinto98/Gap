package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

public class RegModel implements InterfacciaDAO <UtenteBean>{

	private DataSource ds = null;
	
	public RegModel(DataSource ds) { 
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

	@Override
	public Collection<UtenteBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	//salviamo l'utente all'interno del database
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

	@Override
	public void doUpdate(UtenteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(UtenteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

}
