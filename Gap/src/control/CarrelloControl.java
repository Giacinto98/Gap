package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import model.ProdottoBean;
import model.ProdottoModel;

import org.json.JSONObject;

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
		
		
		carrello.inserisciElemento(ds, nome, idMateriale);
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
