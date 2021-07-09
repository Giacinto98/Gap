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
	
	

	public void inserisciElemento(DataSource ds, String nome, String id) 
	{
		ProdottoModel modelProd = new ProdottoModel (ds);
		ProdottoBean prodotto = new ProdottoBean ();
		
		try {
			prodotto = modelProd.doRetrieveByKey(nome);
		} catch (SQLException e) {
			System.out.println("Errore ricerca prodotto nel db");
			e.printStackTrace();
		} 
		
		/*int j=0;
		for(; j<prodotti.size(); j++)
		{
			if(prodotto.getCodice() == prodotti.get(j).getCodice())
			{
				break;
			}
		}
		
		if (j >= prodotti.size())
		{
			prodotto.setQuantita(1);
		}*/
		
		MaterialeModel modelMater = new MaterialeModel (ds);
		MaterialeBean materiale = new MaterialeBean();
		
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
					prodotto.setQuantita(prodotto.getQuantita() + 1);
				}
			}
		}
		
		if(i >= prodotti.size())
		{
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
		return prodotti.size();
	}	
}
