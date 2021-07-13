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

import model.ProdottoBean;
import model.ProdottoModel;


@WebServlet("/ModificaControl")
public class ModificaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		response.encodeURL("ModificaControl");
		ProdottoBean bean = new ProdottoBean();
		bean.setCodice(Integer.parseInt(request.getParameter("codice")));
		bean.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		bean.setSconto(Integer.parseInt(request.getParameter("sconto")));
		bean.setPrezzo(Integer.parseInt(request.getParameter("prezzo")));
			ProdottoModel model = new ProdottoModel(ds);
		try {
			model.doUpdateProdotto(bean);
		} catch (SQLException e) {
			System.out.println("Eccezione modifica prodotto");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
