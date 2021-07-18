package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.RecensioneBean;
import model.RecensioneModel;


@WebServlet("/RecensioneControl")
public class RecensioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.encodeURL("RecensioneControl");
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); //recuperiamo il datasource
		String recensione = request.getParameter("recensione");
		RecensioneModel model = new RecensioneModel(ds);
		RecensioneBean rec = new RecensioneBean();
		rec.setCodice(Integer.parseInt(request.getParameter("codice")));
		rec.setEmail(request.getParameter("email"));
		rec.setTesto(request.getParameter("recensione"));
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedString = localDate.format(formatter);
		rec.setData(formattedString);
		try {
			model.doSave(rec);
		} catch (SQLException e) {
			System.out.println("Errore salvataggio recensione nel database");
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/recensioneEffettuata.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
