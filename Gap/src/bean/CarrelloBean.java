package bean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.ProdottoModel;

public class CarrelloBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	ArrayList<ProdottoBean> prodotti;
	ArrayList<MaterialeBean> materiali;
	int quantita;
	float prezzoTotale;
	
	
	public CarrelloBean()
	{
		prodotti = new ArrayList<ProdottoBean>();
		materiali = new ArrayList<MaterialeBean>();
		quantita = -1;
		prezzoTotale = 0;
	}
	
	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzo) {
		this.prezzoTotale += prezzo;
	}
	
	public void setPrezzoTotaleRimozione(float prezzo) {
		this.prezzoTotale -= prezzo;
	}
	
	public ArrayList<ProdottoBean> getProdotti ()
	{
		return prodotti;
	}
	
	public ArrayList<MaterialeBean> getMateriali ()
	{
		return materiali;
	}
	
	public void setProdotti(ArrayList<ProdottoBean> prodotti) {
		this.prodotti = prodotti;
	}

	public void setMateriali(ArrayList<MaterialeBean> materiali) {
		this.materiali = materiali;
	}

	public void addProdotto(ProdottoBean prodotto, MaterialeBean materiale) 
	{
		prodotti.add(prodotto);
		materiali.add(materiale);
	}


	public void svuotaCarrello ()
	{
		prodotti.removeAll(prodotti);
		materiali.removeAll(materiali);
	}
	
	public int getQuantita()
	{
		int count=0;
		for(int i=0; i<prodotti.size();i++)
			count += prodotti.get(i).getQuantita();
		return count;
	}	
}
