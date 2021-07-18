package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.*;

@WebServlet("/VisualizzaOrdiniControl")
public class VisualizzaOrdiniControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.encodeURL("VisualizzaOrdiniControl");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		UtenteBean user = new UtenteBean();
		OrdineModel model = new OrdineModel(ds);
		HttpSession sessione = request.getSession(false);
		if(sessione!=null)
		{
			user = (UtenteBean) sessione.getAttribute("utente");
		}
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		try {
			ordini = model.doRetriveAllForUtente(user);
		} catch (SQLException e) {
			System.out.println("Errore ricerca ordini dell'utente");
			e.printStackTrace();
		}
		request.setAttribute("ordini", ordini);
		getServletContext().getRequestDispatcher("/paginaOrdiniUtente.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
