package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class CarrelloBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	ArrayList<ProdottoBean> prodotti;
	ArrayList<MaterialeBean> materiali;
	int quantita;
	
	
	public CarrelloBean()
	{
		prodotti = new ArrayList<ProdottoBean>();
		materiali = new ArrayList<MaterialeBean>();
		quantita = -1;
	}
	
	public ProdottoBean getIndex(int j)
	{
		for(int i=0; i<prodotti.size(); i++)
		{
			if(i == j)
				return prodotti.get(i);
		}
		return null;
	}
	
	public float getPrezzoTotale()
	{
		float tot=0;
		for(int i=0; i<prodotti.size(); i++)
		{
			if(prodotti.get(i).getSconto() > 0)
			{
				float sconto = prodotti.get(i).getPrezzo() - ((prodotti.get(i).getPrezzo() * prodotti.get(i).getSconto())/100);
				tot += (prodotti.get(i).getQuantita()) * sconto;
			}
			else
				tot += (prodotti.get(i).getQuantita()) * (prodotti.get(i).getPrezzo());
		}
		return tot;
	}
	
	public ArrayList<ProdottoBean> getProdotti ()
	{
		return prodotti;
	}
	
	public ArrayList<MaterialeBean> getMateriali ()
	{
		return materiali;
	}
	

	public void inserisciElemento(DataSource ds, String nome, String id) 
	{
		ProdottoModel modelProd = new ProdottoModel (ds);
		ProdottoBean prodotto = new ProdottoBean ();
		MaterialeModel modelMater = new MaterialeModel (ds);
		MaterialeBean materiale = new MaterialeBean();
		
		try {
			prodotto = modelProd.doRetrieveByKey(nome);
		} catch (SQLException e) {
			System.out.println("Errore ricerca prodotto nel db");
			e.printStackTrace();
		} 
		
		try {
			materiale = modelMater.doRetrieveByKey(id);
		} catch (SQLException e) {
			System.out.println("Errore ricerca materiale associato al prodotto");
			e.printStackTrace();
		}
		
		int i=0;
		for(; i<prodotti.size(); i++)
		{
			if(prodotto.getCodice() == prodotti.get(i).getCodice())
			{
				if(materiali.get(i).getId() == materiale.getId())
				{
					int quantita=prodotti.get(i).getQuantita() + 1;
					prodotti.get(i).setQuantita(quantita);
					break;
				}
			}
		}
		
		if(i >= prodotti.size())
		{
			int count = 1;
			prodotto.setQuantita(1);
			prodotti.add(prodotto);
			materiali.add(materiale);
		}
	}
	
	
	
	public void eliminaProdotto(int codiceProdotto, int idMateriale)
	{
		int i=0;
		for(; i<prodotti.size(); i++)
		{
			if(codiceProdotto == prodotti.get(i).getCodice())
			{
				if(materiali.get(i).getId() == idMateriale)
				{
					prodotti.remove(i);
					materiali.remove(i);
					return;
				}
			}
		}
	}
	
	
	public void diminuisciQuantitaProdotto(int codiceProdotto, int idMateriale)
	{
		int i=0;
		for(; i<prodotti.size(); i++)
		{
			if(codiceProdotto == prodotti.get(i).getCodice())
			{
				if(materiali.get(i).getId() == idMateriale)
				{
					if(prodotti.get(i).getQuantita() > 1)
					{
						int quantita = prodotti.get(i).getQuantita();
						quantita = quantita - 1 ;
						prodotti.get(i).setQuantita(quantita);
					}
					else
					{
						prodotti.remove(i);
						materiali.remove(i);
						return;
					}
				}
			}
		}
	}
	
	public void aumentaQuantitaProdotto(int codiceProdotto, int idMateriale)
	{
		int i=0;
		for(; i<prodotti.size(); i++)
		{
			if(codiceProdotto == prodotti.get(i).getCodice())
			{
				if(materiali.get(i).getId() == idMateriale)
				{
					if(prodotti.get(i).getQuantita() > 1)
					{
						int quantita = prodotti.get(i).getQuantita();
						quantita = quantita + 1 ;
						prodotti.get(i).setQuantita(quantita);
					}
				}
			}
		}
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
