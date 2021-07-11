package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

public class ComposizioneOrdineModel implements InterfacciaDAO<ComposizioneBean>{

private DataSource ds = null;
	
	public ComposizioneOrdineModel (DataSource ds)
	{
		this.ds = ds;
	}
	
	
	@Override
	public ComposizioneBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ComposizioneBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(ComposizioneBean item) throws SQLException {
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

	@Override
	public void doUpdate(ComposizioneBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(ComposizioneBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
