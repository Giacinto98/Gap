package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.CarrelloBean;
import bean.ComposizioneBean;
import bean.MaterialeBean;
import bean.OrdineBean;
import bean.ProdottoBean;
import bean.UtenteBean;
import model.OrdineModel;
import model.ProdottoModel;


@WebServlet("/AcquistoControl")
public class AcquistoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//alla servlet serve il numero di prodotto e le rispettive quantità 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		UtenteBean utente = new UtenteBean();
		HttpSession sessione = request.getSession(false);
		if (sessione != null)
		{
			utente = (UtenteBean) sessione.getAttribute("utente");
		}
		CarrelloBean carrello = (CarrelloBean) sessione.getAttribute("carrello");
		OrdineBean ordine = new OrdineBean();
		String numeroCarta = (String) request.getParameter("numeroCarta");
		String meseScadenza = (String) request.getParameter("meseScadenza");
		String annoScadenza = (String) request.getParameter("annoScadenza");
		String cvv = (String) request.getParameter("cvv");
		
		if(!numeroCarta.matches("-?\\d+") || numeroCarta.length() > 16 || numeroCarta.length() < 16 )
		{
			request.setAttribute("errore","Numero carta non corretto");
			getServletContext().getRequestDispatcher(response.encodeURL("/datiCartaAcquisto.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)				
			return;
		}
		
		if(!meseScadenza.matches("-?\\d+") || meseScadenza.length() > 2 || Integer.parseInt(meseScadenza) > 12 || Integer.parseInt(meseScadenza) < 0)
		{
			request.setAttribute("errore","Mese scadenza non corretto");
			getServletContext().getRequestDispatcher(response.encodeURL("/datiCartaAcquisto.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)				
			return;
		}
		
		if(!annoScadenza.matches("-?\\d+") || annoScadenza.length() > 4 || Integer.parseInt(annoScadenza) < 2022)
		{
			request.setAttribute("errore","Anno scadenza non corretto");
			getServletContext().getRequestDispatcher(response.encodeURL("/datiCartaAcquisto.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)				
			return;
		}
		
		if(!cvv.matches("-?\\d+") || cvv.length() > 3 || cvv.length() < 3)
		{
			request.setAttribute("errore","CVV non valido");
			getServletContext().getRequestDispatcher(response.encodeURL("/datiCartaAcquisto.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)				
			return;
		}
		
		if(carrello != null )
		{
			double casuale = Math.random() * 100000;
			ordine.setNumeroOrdine(casuale);
			ordine.setEmail(utente.getEmail());
			ordine.setNumeroProdotti(carrello.getQuantita());
			ordine.setDataOrdine(LocalDate.now());
			ordine.setPrezzoTotale(carrello.getPrezzoTotale());
			ordine.setNumeroCarta(numeroCarta);
			ordine.setAnnoScadenzaCarta(annoScadenza);
			ordine.setMeseScadenzaCarta(meseScadenza);
			ordine.setCvvCarta(cvv);
			OrdineModel modelOrdine = new OrdineModel(ds);
			try {
				modelOrdine.doSave(ordine);
			} catch (SQLException e) {
				System.out.println("Eccezione salvataggio ordine in tabella");
				e.printStackTrace();
			}
			
			ProdottoModel prodottoModel = new ProdottoModel(ds);
			ComposizioneBean composizione = new ComposizioneBean();
			
			ArrayList<ProdottoBean> prodotti = carrello.getProdotti();
			ArrayList<MaterialeBean> materiali = carrello.getMateriali();
			
			ProdottoBean prodotto1 = new ProdottoBean();
			MaterialeBean materiale1 = new MaterialeBean();
			
			int i=0;
			while(i<carrello.getProdotti().size())
			{	
				for(int j=0; j<prodotti.size(); j++)
				{
					if(j == i)
					{
						prodotto1 = prodotti.get(i);
						materiale1 = materiali.get(i);
					}	
				}
				composizione.setIdMateriale(materiale1.getId());
				//composizione.setIdMateriale(carrello.getIndexMateriale(i).getId());
				composizione.setCodiceProdotto(prodotto1.getCodice());
				//composizione.setCodiceProdotto(carrello.getIndex(i).getCodice());
				composizione.setNumeroOrdine(casuale);
				
				composizione.setQuantita(prodotti.get(i).getQuantita());
				//composizione.setQuantita(carrello.getIndex(i).getQuantita());
				
				try {
					modelOrdine.doSaveComposizione(composizione);
				} catch (SQLException e) {
					System.out.println("Eccezione salvataggio composizione ordine");
					e.printStackTrace();
				}
				
				try {
					//ProdottoBean prodotto = carrello.getIndex(i);
					prodottoModel.doUpdateQuantita(prodotto1);
				} catch (SQLException e) {
					System.out.println("Eccezione salvataggio quantità modificata del prodotto");
					e.printStackTrace();
				}
				i++;
			}	
		}
		request.setAttribute("messaggio", "Salvataggio riuscito");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/acquistoEffettuato.jsp"));
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
