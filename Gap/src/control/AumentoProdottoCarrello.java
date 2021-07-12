package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import model.CarrelloBean;


@WebServlet("/AumentoProdottoCarrello")
public class AumentoProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
	//System.out.println("siamo qui");
		String  codice = request.getParameter("codiceProdotto");
		String id = request.getParameter("idMateriale");
	//System.out.println("codice " + codice + " id " + id);
		int codiceProdotto = Integer.parseInt(codice);
		int idMateriale = Integer.parseInt(id);
	//System.out.println("Dopo parse: " + codiceProdotto + " " + idMateriale);
		CarrelloBean carrello = new CarrelloBean();
		HttpSession sessione = request.getSession(false);
		if (sessione != null)
		{
			 carrello = (CarrelloBean) sessione.getAttribute("carrello");
		}
	//System.out.println("Quantita non aggiornata " +  carrello.getIndex(carrello.getIndexDiUnProdotto(codiceProdotto, idMateriale)).getQuantita());
		carrello.aumentaQuantitaProdotto(codiceProdotto, idMateriale);
		int codiceAumento = carrello.getIndexDiUnProdotto(codiceProdotto, idMateriale);
		if(codiceAumento != -1)
		{
	//System.out.println("Quantita aggiornata = " +  carrello.getIndex(carrello.getIndexDiUnProdotto(codiceProdotto, idMateriale)).getQuantita());
			//int quantita = carrello.getIndex(codiceAumento).getQuantita();
			//carrello.getIndex(codiceAumento).setQuantita(quantita);
			JSONObject json = new JSONObject();
			try {
				json.put("quantita", carrello.getIndex(codiceAumento).getQuantita());
				json.put("totale", carrello.getQuantita());
				String riferimento = carrello.getIndex(codiceAumento).getCodice() + "_" + carrello.getIndexMateriale(codiceAumento).getId();
	//System.out.println(riferimento);		
				json.put("riferimento", riferimento);
				json.put("prezzoTot", carrello.getPrezzoTotale());
			} catch (JSONException e) {
				System.out.println("Eccezione numero elementi carrello");
				e.printStackTrace();
			}
			response.getWriter().print(json.toString());
		}
		else
			System.out.println("Errore funzione getIndexDiUnProdotto");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
