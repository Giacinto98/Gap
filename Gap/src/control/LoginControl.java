package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import bean.CarrelloBean;
import bean.UtenteBean;
import model.UtenteModel;


@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			String user = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
			UtenteModel login = new UtenteModel(ds);
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
			if(utente.getRuolo().equals("utente"))
			{
				HttpSession sessione = request.getSession(true); //restituisce la sessione se esiste, altrimenti la crea nuova
				sessione.setAttribute("utente", utente);
				sessione.setAttribute("carrello", new CarrelloBean());
				getServletContext().getRequestDispatcher(response.encodeURL("/Index.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)			
				return;
			}
			
			if(utente.getRuolo().equals("GestoreOrdine"))
			{
				HttpSession sessione = request.getSession(true); //restituisce la sessione se esiste, altrimenti la crea nuova
				sessione.setAttribute("GestoreOrdine", utente);
				getServletContext().getRequestDispatcher(response.encodeURL("/admin/paginaAdmin.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)			
				return;
			}
			
			if(utente.getRuolo().equals("GestoreCatalogo"))
			{
				HttpSession sessione = request.getSession(true); //restituisce la sessione se esiste, altrimenti la crea nuova
				sessione.setAttribute("GestoreCatalogo", utente);
				getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)			
				return;
			}
			else 
			{
				request.setAttribute("errore","Inserire dati di accesso corretti.");
				getServletContext().getRequestDispatcher(response.encodeURL("/loginUser.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)			
				
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
