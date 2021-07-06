package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.ProdottoBean;
import model.ProdottoModel;


@WebServlet("/CercaProdottoControl")
public class CercaProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.encodeURL("CercaProdottoControl");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		ProdottoModel model = new ProdottoModel (ds); //creiamo un product model che abbiamo instaziato e che ci permette di recuperare i dati che poi inserirà nel bean da leggere
		ProdottoBean prodotto = new ProdottoBean();
		try {
			prodotto = model.doRetrieveByKey(request.getParameter("bottone"));
		} catch (SQLException e) {
			System.out.println("Eccezzione lanciata dalla ricerca prodotto");
			e.printStackTrace();
		}
		if(prodotto != null)
			{
			request.setAttribute("prodotto", prodotto);
			getServletContext().getRequestDispatcher("/paginaProdotto.jsp").forward(request, response);
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
