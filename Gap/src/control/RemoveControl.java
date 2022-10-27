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

import bean.ProdottoBean;
import model.ProdottoModel;


@WebServlet("/RemoveControl")
public class RemoveControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		response.encodeURL("RemoveControl");
		ProdottoBean bean = new ProdottoBean();
		ProdottoBean bean1 = new ProdottoBean(); 
		ProdottoModel model = new ProdottoModel (ds);
		
		try
		{
		bean.setCodice(Integer.parseInt(request.getParameter("codice")));
		}
		catch (NumberFormatException e)
		{
			try 
			{
			request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
			}catch (SQLException a) {} 
			request.setAttribute("errore1","Inserire valori numerici");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
			dispatcher.forward(request, response);
			return;
		}
		
		try {
			bean1 = model.doRetrieveByCodice(bean.getCodice());
			if (bean1.getCodice() == -1)
			{
				try 
				{
				request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
				}catch (SQLException e) {} 
				request.setAttribute("errore1","Inserire un codice esistente");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
				dispatcher.forward(request, response);
				return;
			}
			model.doDelete(bean);
		} catch (SQLException e) {
			try 
			{
			request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
			}catch (SQLException a) {} 
			request.setAttribute("errore1","Inserire un codice esistente");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
			dispatcher.forward(request, response);
			return;
		}
		try 
		{
		request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
		}catch (SQLException e) {} 
		request.setAttribute("errore1","Rimozione con successo");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
		dispatcher.forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
