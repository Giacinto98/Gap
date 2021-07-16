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

import model.CarrelloBean;
import model.LoginModel;
import model.UtenteBean;


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
		HttpSession sessione = request.getSession(false);
		UtenteBean utente = new UtenteBean();
		if (sessione != null)
		{
			 utente = (UtenteBean) sessione.getAttribute("utente");
			 utente.setPassword(password);
		}
		
		LoginModel model = new LoginModel(ds);
		try {
			model.nuovaPassword(utente);
		} catch (SQLException e) {
			System.out.println("Errore cambio password a utente");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo.jsp");
		dispatcher.forward(request, response);
	
	}

}
