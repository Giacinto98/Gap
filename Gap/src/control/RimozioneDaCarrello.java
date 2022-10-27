package control;

import java.io.IOException;
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


@WebServlet("/RimozioneDaCarrello")
public class RimozioneDaCarrello extends HttpServlet {
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
		
		ArrayList<ProdottoBean> prodotti = carrello.getProdotti();
		ArrayList<MaterialeBean> materiali = carrello.getMateriali();
		
		int codiceRimozione = -1;	
		for(int i=0; i<prodotti.size();i++)
		{
			if((prodotti.get(i).getCodice() == codiceProdotto) && (materiali.get(i).getId() == idMateriale))
				codiceRimozione = i;
		}
		//int codiceRimozione = carrello.getIndexDiUnProdotto(codiceProdotto, idMateriale);
		
				JSONObject json = new JSONObject();
		try {
			ProdottoBean prodotto1 = new ProdottoBean();
			MaterialeBean materiale1 = new MaterialeBean();
			for(int i=0; i<prodotti.size(); i++)
			{
				if(i == codiceRimozione)
				{
					prodotto1 = prodotti.get(i);
					materiale1 = materiali.get(i);
					carrello.setPrezzoTotale(prodotto1.getPrezzo());
				}
			}
			String riferimento = prodotto1.getCodice() + "_" + materiale1.getId() + "sezioneProdotto";			
			
			int i=0;
			for(; i<prodotti.size(); i++)
			{
				if(codiceProdotto == prodotti.get(i).getCodice())
				{
					if(materiali.get(i).getId() == idMateriale)
					{
						prodotti.remove(i);
						materiali.remove(i);   
						carrello.setProdotti(prodotti);
						carrello.setMateriali(materiali);
						carrello.setPrezzoTotaleRimozione(prodotto1.getPrezzo());
					}
				}
			}
			//carrello.eliminaProdotto(codiceProdotto, idMateriale);
			json.put("riferimento", riferimento);
			json.put("prezzoTot", carrello.getPrezzoTotale());
			json.put("totale", carrello.getQuantita());
		} catch (JSONException e) {
			System.out.println("Eccezione numero elementi carrello");
			e.printStackTrace();
		}
		response.getWriter().print(json.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
