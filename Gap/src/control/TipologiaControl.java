package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.ProdottoModel;

@WebServlet("/TipologiaControl")
public class TipologiaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		String tipo = request.getParameter("tipo");
		ProdottoModel model = new ProdottoModel(ds);
		try 
		{
		request.setAttribute("prodotti1", model.doRetriveAllTipologia (tipo)); //passiamo alla request l'array di prodotti nella variabile products
		} 
		catch (SQLException e) {
			System.out.println(e); //sampiamo per visualizzarla duante la fase di debugghing nella console
		}
		getServletContext().getRequestDispatcher(response.encodeURL("/prodottoSpecifico.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
