package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.CarrelloBean;
import model.ComposizioneBean;
import model.ComposizioneOrdineModel;
import model.OrdineBean;
import model.OrdineModel;
import model.UtenteBean;


@WebServlet("/AcquistoControl")
public class AcquistoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//alla servlet serve il numero di prodotto e le rispettive quantità 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		response.encodeURL("AcquistoControl");
		UtenteBean utente = new UtenteBean();
		HttpSession sessione = request.getSession(false);
		if (sessione != null)
		{
			utente = (UtenteBean) sessione.getAttribute("utente");
		}
		CarrelloBean carrello = (CarrelloBean) sessione.getAttribute("carrello");
		OrdineBean ordine = new OrdineBean();
		
		if(carrello != null )
		{
			ordine.setEmail(utente.getEmail());
			ordine.setNumeroProdotti(carrello.getQuantita());
			ordine.setDataOrdine(LocalDate.now());
			ordine.setPrezzoTotale(carrello.getPrezzoTotale());
			OrdineModel modelOrdine = new OrdineModel(ds);
			try {
				modelOrdine.doSave(ordine);
			} catch (SQLException e) {
				System.out.println("Eccezione salvataggio ordine in tabella");
				e.printStackTrace();
			}
			ComposizioneOrdineModel composizioneModel = new ComposizioneOrdineModel(ds);
			ComposizioneBean composizione = new ComposizioneBean();
			for(int i=0; i<carrello.getQuantita();i++)
			{	
				composizione.setCodiceProdotto(carrello.getIndex(i).getCodice());
				
				try {
					composizione.setNumeroOrdine(modelOrdine.getOrdine(ordine).getNumeroOrdine());
				} catch (SQLException e1) {
					System.out.println("Eccezione ricerca ordine");
					e1.printStackTrace();
				}
				
				composizione.setQuantia(carrello.getIndex(i).getQuantita());
				try {
					composizioneModel.doSave(composizione);
				} catch (SQLException e) {
					System.out.println("Eccezione salvataggio composizione ordine");
					e.printStackTrace();
				}
			}
			
		}
		else
			System.out.println("errore passaggio prodotti");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
