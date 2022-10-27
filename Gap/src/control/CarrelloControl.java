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

@WebServlet("/CarrelloControl")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		String nome = request.getParameter("nome");
		String idMateriale = request.getParameter("idMateriale");
		CarrelloBean carrello = new CarrelloBean();
		HttpSession sessione = request.getSession(false);
		if (sessione != null)
		{
			 carrello = (CarrelloBean) sessione.getAttribute("carrello");
		}
		
		ProdottoModel modelProd = new ProdottoModel (ds);
		ProdottoBean prodotto = new ProdottoBean ();
		MaterialeBean materiale = new MaterialeBean();
		
		try {
			prodotto = modelProd.doRetrieveByKey(nome);
		} catch (SQLException e) {
			System.out.println("Errore ricerca prodotto nel db");
			e.printStackTrace();
		} 
		
		try {
			materiale = modelProd.doRetriveByKeyMateriale(idMateriale);
		} catch (SQLException e) {
			System.out.println("Errore ricerca materiale associato al prodotto");
			e.printStackTrace();
		}
		
		ArrayList<ProdottoBean> prodotti = carrello.getProdotti();
		ArrayList<MaterialeBean> materiali = carrello.getMateriali();
		int i=0;
		for (; i<prodotti.size(); i++)
		{
			if(prodotto.getCodice() == prodotti.get(i).getCodice())
			{
				if(materiali.get(i).getId() == materiale.getId())
				{
					int quantita=prodotti.get(i).getQuantita() + 1;
					prodotti.get(i).setQuantita(quantita);
					carrello.setProdotti(prodotti);
					carrello.setMateriali(materiali);
					break;
				}
			}
		}
			if(i >= prodotti.size())
			{
				prodotto.setQuantita(1);
				float sconto = prodotto.getSconto();
				if(sconto > 0)
				{
				sconto = ((prodotto.getSconto() * prodotto.getPrezzo()) / 100);
				prodotto.setPrezzo(prodotto.getPrezzo() - sconto);
				carrello.addProdotto(prodotto, materiale);
				}
			}
		//carrello.inserisciElemento (prodotto, materiale);
		carrello.setPrezzoTotale(prodotto.getPrezzo());
		response.setContentType("application/json");
		JSONObject json = new JSONObject();
		try {
			json.put("number", carrello.getQuantita());
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
