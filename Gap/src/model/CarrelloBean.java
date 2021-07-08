package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class CarrelloBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	Collection<ProdottoBean> prodotti;
	ProdottoBean prodotto;
	MaterialeBean materiale;
	
	
	public CarrelloBean()
	{
		prodotti = new LinkedList<ProdottoBean>();
		prodotto = null;
		materiale = null;
	}
	
}
