package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.JSONException;
import org.json.JSONObject;
import model.UtenteBean;
import model.LoginModel;


@WebServlet("/RecuperoDatiUtenteControl")
public class RecuperoDatiUtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.encodeURL("RecuperoDatiUtenteControl");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		LoginModel model = new LoginModel(ds);
		String email = request.getParameter("email");
		UtenteBean user = new UtenteBean();
		try {
			user = model.doRetrieveByKey(email);
		} catch (SQLException e) {System.out.println("Eccezione recupero utente da database");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		JSONObject json = new JSONObject();
		try {
			json.put("cf", user.getCf());
			json.put("email", user.getEmail());
			json.put("nome", user.getNome());
			json.put("cognome", user.getCognome());
			json.put("indirizzo", user.getIndirizzo());
			json.put("password", user.getPassword());
			json.put("telefono", user.getTelefono());
		} catch (JSONException e) {
			System.out.println("Eccezione numero elementi carrello");
			e.printStackTrace();
		}

		response.getWriter().print(json.toString());
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
