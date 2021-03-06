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
		int codiceRimozione = carrello.getIndexDiUnProdotto(codiceProdotto, idMateriale);
				JSONObject json = new JSONObject();
		try {
			String riferimento = carrello.getIndex(codiceRimozione).getCodice() + "_" + carrello.getIndexMateriale(codiceRimozione).getId() + "sezioneProdotto";			
			carrello.eliminaProdotto(codiceProdotto, idMateriale);
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
