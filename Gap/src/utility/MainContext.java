package utility;

//E il main che viene chiamato prima di ogni altra cosa
//Ci aiuta ad interfacciarci meglio col database

import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener  //Per far capire a tomcat che è un listener posso anche metterlo nel web.xml 
public class MainContext implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Startup web application"); //Messaggio di benvenuto

		ServletContext context = sce.getServletContext(); //accede  al contesto della servlet
		DataSource ds = null; // creiamo una risorsa settata a null

		try {
			Context initCtx = new InitialContext(); //contesto di accesso ai servizi di tomcat
			Context envCtx = (Context) initCtx.lookup("java:comp/env"); 
			ds = (DataSource) envCtx.lookup("jdbc/Gap"); //cose da fare quasi standard tranne per la stringa che deve essere uguale a quella del file web.xml
			Connection con = null; //crea una connessione per il database

			try {
				con = ds.getConnection();
				DatabaseMetaData metaData = con.getMetaData(); //possiamo leggere tutti i metadata di accesso
				System.out.println("DBMS name:" + metaData.getDatabaseProductName());
				System.out.println("DBMS version:" + metaData.getDatabaseProductVersion());
				//Stampiamo delle informazioni non necessarie
			} finally {
				if (con != null)
					con.close(); //indipendentemente da quello che succede chiudiamo la connessione con il database.
			}

		} catch (SQLException | NamingException e) {
			System.out.println(e);
		}

		context.setAttribute("DataSource", ds); //rendiamo ds disponibile a tutte le servlet aggiungendo un attributo DataSource salvandoci ds
		//qualisasi servlet che accede al contesto riceve il datasource di accesso.
		System.out.println("DataSource creation: " + ds.toString()); //non necessaria, solo una stampa

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext(); //ci prendiamo il contesto
		context.removeAttribute("dataSource"); //togliamo l'attributo datasource
		System.out.println("Shutdown web aplication");
		//Potevamo anche rimanere la funzione contextDestroyde vuota, per chiudere l'applicazione

	}
}