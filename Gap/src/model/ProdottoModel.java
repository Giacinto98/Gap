package model;
//classe che implementa le operazioni CRUD

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class ProdottoModel implements InterfacciaDAO <ProdottoBean> 
{
	private DataSource ds = null; //instanziamo un datasource che ci serve per recuperare la connessione

	public ProdottoModel(DataSource ds) { 
		//costruttore, quando chiamiamo productmodel
		//dobbiamo dare in input un datasource, per avere 
		//tutte le informazioni sul database
		this.ds = ds;
	}
	
	@Override
	public ProdottoBean doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String selectSQL = "select * from prodotto where Nome = '"+ nome +"';";
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
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
		return bean; //ritorniamo la lista dei prodotti presi dal database.
	}
	

	@Override
	public Collection<ProdottoBean> doRetriveAll(String order) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from prodotto order by sconto desc;";
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>(); //arrey di prodotti che vengono recuperati dal database e poi inseriti nell'array
		
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(selectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				ProdottoBean bean = new ProdottoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
				prodotti.add(bean);
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
		return prodotti; //ritorniamo la lista dei prodotti presi dal database.
	}
	

	@Override
	public void doSave(ProdottoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(ProdottoBean item) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		ProdottoBean bean = new ProdottoBean();
		String SelectSQL = "select * from prodotto where codice = "+item.getCodice()+";";
		try {
			connection = ds.getConnection(); //recuperiamo la connessione dal datasource passato in input nel costruttore della classe
			preparedStatement = connection.prepareStatement(SelectSQL); //accediamo alla connessione e passiamo alla funzione la stringa SQL
			ResultSet rs = preparedStatement.executeQuery(); //esguiamo la query come facevamo nanche in db del primo sempestre
			while(rs.next()) //scorriamo i valori che ci vengono restituiti e li mettima nel bean dedicato a questa tabella
			{
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setAltezza(rs.getInt("altezza"));
				bean.setProfondita(rs.getInt("profondita"));
				bean.setLarghezza(rs.getInt("larghezza"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSconto(rs.getInt("sconto"));
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
			
		
		try
		{
			int quantita = (bean.getQuantita() - item.getQuantita());
			String UpdateSQL = "Update prodotto set quantita = '"+quantita+"' where codice = '"+item.getCodice()+"';";
			System.out.println(UpdateSQL);
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
	public void doDelete(ProdottoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
