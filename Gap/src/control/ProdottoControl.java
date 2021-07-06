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

@WebServlet("/ProdottoControl")
public class ProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


  //accediamo al db con la get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.encodeURL("ProdottoControl");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		ProdottoModel model = new ProdottoModel (ds); //creiamo un product model che abbiamo instaziato e che ci permette di recuperare i dati che poi inserirà nel bean da leggere
		
		try 
		{
		request.setAttribute("prodotti", model.doRetriveAll("")); //passiamo alla request l'array di prodotti nella variabile products
		} 
		
		catch (SQLException e) {
			System.out.println(e); //sampiamo per visualizzarla duante la fase di debugghing nella console
			request.setAttribute("errore", e.getMessage()); //creiamo un attributo errore dove mettiamo l'errore che abbiamo avuto nel lancio dell'eccezione
		}
		getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response); //rimandiamo l'output alla parte view (jsp)
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
