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


@WebServlet("/ModificaControl")
public class ModificaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		response.encodeURL("ModificaControl");
		ProdottoBean bean = new ProdottoBean();
		ProdottoBean bean1 = new ProdottoBean();
		ProdottoModel model = new ProdottoModel (ds);

		
		try
		{
		bean.setCodice(Integer.parseInt(request.getParameter("codice")));
		bean.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		bean.setSconto(Integer.parseInt(request.getParameter("sconto")));
		bean.setPrezzo(Integer.parseInt(request.getParameter("prezzo")));
		}
		catch (NumberFormatException e)
		{
			try 
			{
			request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
			}catch (SQLException a) {} 
			request.setAttribute("errore2","Inserire valori numerici");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
			dispatcher.forward(request, response);
			return;
		}
		if(bean.getSconto() > 100 || bean.getSconto() < 1)
		{
			try 
			{
			request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
			}catch (SQLException e) {} 
			request.setAttribute("errore2","Sconto può variare tra 1 e 100");
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
				request.setAttribute("errore2","Inserire un codice esistente");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
				dispatcher.forward(request, response);
				return;
			}
			model.doUpdateCatalogo(bean);
		} catch (SQLException e) {
			System.out.println("Eccezione modifica prodotto");
			e.printStackTrace();
		}
		try 
		{
		request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
		}catch (SQLException e) {} 
		request.setAttribute("errore2","Modifica avvenuta con successo");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/admin/modifica.jsp"));
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
