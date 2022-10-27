package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
import bean.UtenteBean;
import model.UtenteModel;


@WebServlet("/CambiaPasswordControl")
public class CambiaPasswordControl extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		String password = request.getParameter("password");
		String passCorr = request.getParameter("passwordCorrente");
		HttpSession sessione = request.getSession(false);
		UtenteBean utente = new UtenteBean();
		if (sessione != null)
		{
			 utente = (UtenteBean) sessione.getAttribute("utente");
			 if(passCorr.equals(utente.getPassword()))
			 {
				 utente.setPassword(password);
			 }	
			 else
			 {
				 request.setAttribute("errore", "Password attuale non corretta");
				 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/cambioPassword.jsp"));
				 dispatcher.forward(request, response);
				 return;
			 }
		}
		
		UtenteModel model = new UtenteModel(ds);
		try {
			model.doUpdate(utente);
		} catch (SQLException e) {
			System.out.println("Errore cambio password a utente");
			e.printStackTrace();
		}
		request.setAttribute("messaggio", "Salvataggio riuscito");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/profilo.jsp"));
		dispatcher.forward(request, response);
	
	}

}
