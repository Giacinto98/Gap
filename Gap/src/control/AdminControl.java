package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.OrdineModel;


@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		response.encodeURL("AdminControl");
		OrdineModel model = new OrdineModel(ds);
		try 
		{
		request.setAttribute("ordini", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
		} 
		
		catch (SQLException e) {
			System.out.println(e); //sampiamo per visualizzarla duante la fase di debugghing nella console
		}
		getServletContext().getRequestDispatcher(response.encodeURL("/admin/paginaAdmin.jsp")).forward(request, response); //rimandiamo l'output alla parte view (jsp)
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
