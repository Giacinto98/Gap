package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class TipologiaModel implements InterfacciaDAO <ProdottoBean> {

	
	private DataSource ds = null; //instanziamo un datasource che ci serve per recuperare la connessione

	public TipologiaModel(DataSource ds) { 
		//costruttore, quando chiamiamo productmodel
		//dobbiamo dare in input un datasource, per avere 
		//tutte le informazioni sul database
		this.ds = ds;
	}
	
	@Override
	public ProdottoBean doRetrieveByKey(String tipologia) throws SQLException {
		return null;
	}

	@Override
	public Collection<ProdottoBean> doRetriveAll(String tipologia) throws SQLException {
		Connection connection = null; //creo connessione 
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "select * from prodotto where tipologia = '"+tipologia+"' order by sconto desc;";
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(ProdottoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
