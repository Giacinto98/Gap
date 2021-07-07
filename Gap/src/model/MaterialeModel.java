package model;
//classe che implementa le operazioni CRUD

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class MaterialeModel implements InterfacciaDAO <MaterialeBean> 
{
	private DataSource ds = null;
	
	public MaterialeModel (DataSource ds)
	{
		this.ds = ds;
	}
	
	
	@Override
	public Collection<MaterialeBean> doRetriveAll(String nomeProdotto) throws SQLException {
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

	@Override
	public MaterialeBean doRetrieveByKey(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(MaterialeBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(MaterialeBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(MaterialeBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
