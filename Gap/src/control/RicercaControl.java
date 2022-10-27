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


@WebServlet("/RicercaControl")
public class RicercaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		ProdottoBean bean = new ProdottoBean();
		ProdottoModel model = new ProdottoModel(ds);
		try {
			bean=model.doRetrieveByKey(request.getParameter("ricerca"));
		} catch (SQLException e) {
			System.out.println("Errore recupero del prodottoda ricercare");
			e.printStackTrace();
		}
		

		if(bean.getCodice() != -1)
		{
			request.setAttribute("prodotto", bean);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/paginaProdotto.jsp"));
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/paginaProdottoNonTrovato.jsp"));
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
