package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.RegModel;
import model.UtenteBean;

@WebServlet("/RegControl")
public class RegControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String error = "";
		UtenteBean utente = new UtenteBean();
		utente.setCf(request.getParameter("cf"));
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		utente.setEmail(request.getParameter("email"));
		utente.setPassword(request.getParameter("password"));
		utente.setIndirizzo(request.getParameter("indirizzo"));
		utente.setTelefono(request.getParameter("telefono"));
		
		if(utente.getCf() == null || utente.getCf().equals(""))
			error = error + "Inserire codice fiscale/n ";
		else
			request.setAttribute("cf", utente.getCf());
		
		
		if(utente.getNome() == null || utente.getNome().equals(""))
			error = error + "Inserire nome/n ";
		else
			request.setAttribute("nome", utente.getNome());
		
		
		if(utente.getCognome() == null || utente.getCognome().equals(""))
			error = error + "Inserire cognome/n ";
		else
			request.setAttribute("cognome", utente.getCognome());
		
		if(utente.getEmail() == null || utente.getEmail().equals(""))
			error = error + "Inserire email/n";
		else
			request.setAttribute("email", utente.getEmail());
		
		
		if(utente.getPassword() == null || utente.getPassword().equals(""))
			error = error + "Inserire password/n";
		else
			request.setAttribute("password", utente.getPassword());
		
		
		if(utente.getIndirizzo() == null || utente.getIndirizzo().equals(""))
			error = error + "Inserire indirizzo/n ";
		else
			request.setAttribute("indirizzo", utente.getIndirizzo());
		
		
		if(utente.getTelefono() == null || utente.getTelefono().equals(""))
			error = error + "Inserire telefono/n ";
		else
			request.setAttribute("telefono", utente.getTelefono());
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		RegModel registrazione = new RegModel(ds);
		
		Boolean var = null;
		
		try {
			var = registrazione.cercaSimili(utente);
		} catch (SQLException e1) {
			System.out.println("Eccezzione confronto email utente");
			e1.printStackTrace();
		}
		
		if(var != null && var == true)
		{	
			try
			{
			registrazione.doSave(utente); //salva utente nel db
			} catch (SQLException e1) 
				{
					System.out.println("Eccezzione salvataggio utente");
					e1.printStackTrace();	
				}
		}
		else if(var != null && var == false)
				{
				error = "Non è possibile registrarsi con questa email";
				}
		
		
		if(error != null && !error.equals(""))
		{
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/Registrazione.jsp"));
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/regEffettuata.jsp"));
			dispatcher.forward(request, response);
		}
	}

}
