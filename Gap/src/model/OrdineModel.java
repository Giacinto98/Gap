package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class OrdineModel implements InterfacciaDAO <OrdineBean>{
	
private DataSource ds = null;
	
	public OrdineModel (DataSource ds)
	{
		this.ds = ds;
	}

	public OrdineBean getOrdine(OrdineBean ordine) throws SQLException
	{
		OrdineBean ordine1 = new OrdineBean();
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from ordine where email = '"+ordine.getEmail()+"' and prezzo_totale= "+ordine.getPrezzoTotale()+" and num_prodotti="+ordine.getNumeroProdotti()+" and data_Ordine = '"+ordine.getDataOrdine()+"'";
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				ordine1.setEmail(rs.getString("email"));
				ordine1.setNumeroOrdine(rs.getInt("num_ordine"));
				ordine1.setNumeroProdotti(rs.getInt("Num_Prodotti"));
				String data = rs.getString("data_ordine");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate localDate = LocalDate.parse(data, formatter);
				ordine1.setDataOrdine(localDate);
				ordine1.setPrezzoTotale(rs.getInt("Prezzo_Totale"));
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
		System.out.println(ordine1.getNumeroOrdine());
		return ordine1; //ritorniamo la lista dei prodotti presi dal database.
	}
	
	@Override
	public OrdineBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OrdineBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String updateSQL = "insert into ordine (Email,Prezzo_Totale,Num_Prodotti,data_Ordine) values ('"+ordine.getEmail()+"',"+ordine.getPrezzoTotale()+","+ordine.getNumeroProdotti()+",'"+ordine.getDataOrdine()+"');";
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
	public void doUpdate(OrdineBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(OrdineBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
