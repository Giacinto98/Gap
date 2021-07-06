package control;

import java.util.Base64;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.LoginModel;
import model.UtenteBean;


@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			String user = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
			LoginModel login = new LoginModel(ds);
			UtenteBean utente = new UtenteBean();
			try 
			{
				utente = login.cercaUtente(user, password);
			} 
			catch (SQLException e) 
			{
				System.out.println("Eccezione login utente");
				e.printStackTrace();
			}
			if(utente.getRuolo().equals("utente") || utente.getRuolo().equals("amministratore"))
			{
				HttpSession sessione = request.getSession(true); //restituisce la sessione se esiste, altrimenti la crea nuova
				sessione.setAttribute("utente", utente);
				String index1 = response.encodeURL("/Index.jsp"); //URL rewriting
				//System.out.println(index1);
					getServletContext().getRequestDispatcher(index1).forward(request, response); //rimandiamo l'output alla parte view (jsp)			
			}
			else 
			{
				response.sendRedirect("loginUser.jsp");
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
