package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import bean.CarrelloBean;
import bean.MaterialeBean;
import bean.ProdottoBean;
import model.ProdottoModel;


@WebServlet("/AumentoProdottoCarrello")
public class AumentoProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		String  codice = request.getParameter("codiceProdotto");
		String id = request.getParameter("idMateriale");
		int codiceProdotto = Integer.parseInt(codice);
		int idMateriale = Integer.parseInt(id);
		CarrelloBean carrello = new CarrelloBean();
		HttpSession sessione = request.getSession(false);
		if (sessione != null)
		{
			 carrello = (CarrelloBean) sessione.getAttribute("carrello");
		}
			try {
			ProdottoModel model = new ProdottoModel (ds);
			ProdottoBean prodotto = model.doRetrieveByCodice(codiceProdotto);
			int quantita = model.restituisciQuantita(prodotto);
			
			ArrayList<ProdottoBean> prodotti = carrello.getProdotti();
			ArrayList<MaterialeBean> materiali = carrello.getMateriali();
			
			
			for(int i = 0; i<prodotti.size(); i++)
			{
				if(codiceProdotto == prodotti.get(i).getCodice())
				{
					if(materiali.get(i).getId() == idMateriale)
					{
						int quantitaProdottoCarrello = prodotti.get(i).getQuantita();
						if(quantita > quantitaProdottoCarrello)
						{
							quantitaProdottoCarrello = quantitaProdottoCarrello + 1 ;
							prodotti.get(i).setQuantita(quantitaProdottoCarrello);
							
						}
					}
				}
			}		
		//carrello.aumentaQuantitaProdotto(quantita, codiceProdotto, idMateriale);	
		
			int codiceAumento = -1;	
			for(int i=0; i<prodotti.size();i++)
			{
				if((prodotti.get(i).getCodice() == codiceProdotto) && (materiali.get(i).getId() == idMateriale))
					codiceAumento = i;
			}
		//int codiceAumento = carrello.getIndexDiUnProdotto(codiceProdotto, idMateriale);
		
		if(codiceAumento != -1)
		{
			JSONObject json = new JSONObject();
			try {
				
				ProdottoBean prodotto1 = new ProdottoBean();
				MaterialeBean materiale1 = new MaterialeBean();
				for(int i=0; i<prodotti.size(); i++)
				{
					if(i == codiceAumento)
					{
						prodotto1 = prodotti.get(i);
						materiale1 = materiali.get(i);
						carrello.setPrezzoTotale(prodotto1.getPrezzo());
					}
				}
				//json.put("quantita", carrello.getIndex(codiceAumento).getQuantita());
				json.put("quantita", prodotto1.getQuantita());
				
				
				json.put("totale", carrello.getQuantita());
				
				
				String riferimento = prodotto1.getCodice() + "_" + materiale1.getId();
				//String riferimento = carrello.getIndex(codiceAumento).getCodice() + "_" + carrello.getIndexMateriale(codiceAumento).getId();
			
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
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
