package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class RecensioneModel implements InterfacciaDAO <RecensioneBean>{
	
	private DataSource ds = null;
	
	public RecensioneModel(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public RecensioneBean doRetrieveByKey(String code) throws SQLException {

		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		RecensioneBean bean = new RecensioneBean();
		int codice = Integer.parseInt(code);
		String selectSQL = "select * from recensione where Codice = '"+ codice +"';";
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setTesto(rs.getString("Testo"));
				bean.setData(rs.getString("Data_Recensione"));
				bean.setCodice(rs.getInt("Codice"));
				bean.setEmail(rs.getString("Email"));
				
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
	
		return bean;  //ritorniamo la lista delle recensioni presi dal database.
	}

	@Override
	public Collection<RecensioneBean> doRetriveAll(String code) throws SQLException {

		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		int codice = Integer.parseInt(code);
		Collection<RecensioneBean> recensioni = new LinkedList<RecensioneBean>();
		String selectSQL = "select * from recensione where Codice = '"+ codice +"';";
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				RecensioneBean bean = new RecensioneBean();
				bean.setTesto(rs.getString("Testo"));
				bean.setData(rs.getString("Data_Recensione"));
				bean.setCodice(rs.getInt("Codice"));
				bean.setEmail(rs.getString("Email"));
				recensioni.add(bean);
				
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
	
		return recensioni;  //ritorniamo la lista delle recensioni presi dal database.
	}

	@Override
	public void doSave(RecensioneBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String insertSQL = "Insert into recensione (Testo,Data_Recensione,Codice,Email) values ('"+item.getTesto()+"','"+item.getData()+"',"+item.getCodice()+",'"+item.getEmail()+"');";

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

	
	public void doUpdateRecensione(RecensioneBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String UpdateSQL = "Update recensione set Testo="+item.getTesto()+",Data_Recensione="+item.getData()+",Email="+item.getEmail()+" where codice = "+item.getCodice()+",and email ="+item.getEmail() +";";
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

	@Override
	public void doUpdate(RecensioneBean item) throws SQLException{
		
	}
	@Override
	public void doDelete(RecensioneBean item) throws SQLException {

		
	}

}