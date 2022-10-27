package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.MaterialeBean;
import bean.ProdottoBean;
import bean.RecensioneBean;
import model.ProdottoModel;
import model.RecensioneModel;


@WebServlet("/CercaProdottoControl")
public class CercaProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.encodeURL("CercaProdottoControl");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		ProdottoModel model = new ProdottoModel (ds); //creiamo un product model che abbiamo instaziato e che ci permette di recuperare i dati che poi inserirà nel bean da leggere
		ProdottoBean prodotto = new ProdottoBean();
		RecensioneModel recensione = new RecensioneModel(ds);
		Collection<RecensioneBean> recensioni = new LinkedList<RecensioneBean>();
		Collection<MaterialeBean> materiali = new LinkedList<MaterialeBean>();
		
		try {
			prodotto = model.doRetrieveByKey(request.getParameter("bottone"));
		} catch (SQLException e) {
			System.out.println("Eccezzione lanciata dalla ricerca prodotto");
			e.printStackTrace();
		}
		
		try {
			materiali = model.doRetriveAllMateriale(prodotto.getNome());
		} catch (SQLException e) {
			System.out.println("Eccezzione lanciata dalla ricerca del materiale");
			e.printStackTrace();
		} 
		
		String x =  Integer.toString(prodotto.getCodice());
		try {
			recensioni = recensione.doRetriveAll(x);
		} catch (SQLException e) {
			System.out.print("ERRORE RICERCA RECENSIONE cerca prodotto RIGA 57");
			e.printStackTrace();
		}
		
		if(prodotto.getCodice() != -1 && prodotto != null && prodotto.getQuantita() > 0)
		{
			request.setAttribute("materiali", materiali);
			request.setAttribute("prodotto", prodotto);
			request.setAttribute("recensioni",recensioni);
			request.setAttribute("messaggio", "Ricerca riuscita");
			getServletContext().getRequestDispatcher(response.encodeURL(response.encodeURL("/paginaProdotto.jsp"))).forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/paginaProdottoNonTrovato.jsp"));
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}