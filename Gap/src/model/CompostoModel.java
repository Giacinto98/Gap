package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

public class CompostoModel implements InterfacciaDAO<CompostoBean>{
	
	private DataSource ds = null; //instanziamo un datasource che ci serve per recuperare la connessione

	public CompostoModel(DataSource ds) { 
		//costruttore, quando chiamiamo productmodel
		//dobbiamo dare in input un datasource, per avere 
		//tutte le informazioni sul database
		this.ds = ds;
	}

	@Override
	public CompostoBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CompostoBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(CompostoBean item) throws SQLException {
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

	@Override
	public void doUpdate(CompostoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(CompostoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

}
