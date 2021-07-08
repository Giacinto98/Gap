package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import model.ProdottoBean;
import model.ProdottoModel;

import org.json.JSONObject;

@WebServlet("/CarrelloControl")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		String nome = request.getParameter("nome");
		String colore = request.getParameter("colore");
		ProdottoModel prodotto = new ProdottoModel(ds);
		ProdottoBean bean = new ProdottoBean();
		
		try {
			bean = prodotto.doRetrieveByKey(nome);
		} catch (SQLException e) {
			System.out.println("Errore ricerca prodotto");
			e.printStackTrace();
		}
		
		//Assegna al bean carrello
		
		response.setContentType("application/json");
		JSONObject json = new JSONObject();
		try {
			json.put("number", 1);
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
