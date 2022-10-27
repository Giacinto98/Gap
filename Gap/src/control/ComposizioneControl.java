package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.ComposizioneBean;
import bean.ProdottoBean;
import bean.RecensioneBean;
import model.OrdineModel;
import model.ProdottoModel;
import model.RecensioneModel;

@WebServlet("/ComposizioneControl")
public class ComposizioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		String numeroOrdine = request.getParameter("idOrdine");
		Collection<ComposizioneBean> composizioni = new LinkedList<ComposizioneBean>();
		OrdineModel model = new OrdineModel(ds);
		try {
			composizioni = model.composizioneOrdine(numeroOrdine);
		} catch (SQLException e) {
			System.out.println("Errore recupero composizione dell'ordine" + numeroOrdine);
			e.printStackTrace();
		}
		
		ProdottoModel modelProd = new ProdottoModel(ds);
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>();
		Iterator<ComposizioneBean> it = composizioni.iterator(); //iteriamo i prodotti
		Collection<RecensioneBean> recensioni = new LinkedList<RecensioneBean>();
		Collection<RecensioneBean> recensioniProdotto = new LinkedList<RecensioneBean>();
		RecensioneModel modelRecensione = new RecensioneModel(ds);
		
		while(it.hasNext()) //fin quando ho prodotti
		{
			ComposizioneBean composizione = (ComposizioneBean) it.next(); 
			int codice = composizione.getCodiceProdotto();
			ProdottoBean prodotto = null;
			try {
				prodotto = modelProd.doRetrieveByCodice(codice);
			} catch (SQLException e) {
				System.out.println("Errore recupero prodotto dalla composizione");
				e.printStackTrace();
			}
			if(prodotto != null)
				prodotti.add(prodotto);
			else
				System.out.println("Prodotto non inserito poichè uguale null");
			
			String codice1 = Integer.toString(prodotto.getCodice());
			try {
				recensioniProdotto = modelRecensione.doRetriveAll(codice1);
				Iterator<RecensioneBean> itertator = recensioniProdotto.iterator();
				while(itertator.hasNext()) //fin quando ho prodotti
				{
					RecensioneBean recensione = (RecensioneBean) itertator.next(); 
					recensioni.add(recensione);
				
				}
			} catch (SQLException e) {
				System.out.println("Errore recupero recensione del prodotto");
				e.printStackTrace();
			}
				
		}
		request.setAttribute("recensioni", recensioni);
		request.setAttribute("prodotti", prodotti);
		getServletContext().getRequestDispatcher(response.encodeURL("/paginaVisualizzazioneProdottiOrdine.jsp")).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
