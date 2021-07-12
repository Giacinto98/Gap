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
	
	@Override
	public OrdineBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.parse(data, formatter);
				bean.setDataOrdine(localDate);
				bean.setPrezzoTotale(rs.getFloat("Prezzo_Totale"));
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

	@Override
	public void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String updateSQL = "insert into ordine (Num_Ordine,Email,Prezzo_Totale,Num_Prodotti,data_Ordine) values ("+ ordine.getNumeroOrdine() +",'"+ordine.getEmail()+"',"+ordine.getPrezzoTotale()+","+ordine.getNumeroProdotti()+",'"+ordine.getDataOrdine()+"');";
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
