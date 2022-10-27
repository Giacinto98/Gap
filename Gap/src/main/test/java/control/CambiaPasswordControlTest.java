package main.test.java.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import bean.CarrelloBean;
import bean.UtenteBean;
import control.AcquistoControl;
import control.CambiaPasswordControl;

public class CambiaPasswordControlTest {
	@Test
	public void testDoPost () throws ServletException, IOException {
		HttpServletRequest request = Mockito.mock (HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock (HttpServletResponse.class);
		HttpSession sessione = Mockito.mock(HttpSession.class);
		UtenteBean utente = new UtenteBean();
   	 	utente.setCf("dnlgnt98a05g813p");
   	 	utente.setCognome("Giacinto");
   	 	utente.setNome("Adinolfi");
   	 	utente.setEmail("giaci95@live.it");
   	 	utente.setPassword("Milka25@live");
   	 	utente.setTelefono("2365412569");
   	 	utente.setIndirizzo("Via alcide 24");
   	 	sessione.setAttribute("utente", utente);	
		Mockito.when(request.getParameter("password")).thenReturn("Franco25@live");
		Mockito.when(request.getParameter("passwordCorrente")).thenReturn("Milka25@live");
		CambiaPasswordControl serv = new CambiaPasswordControl();
		//serv.doPost (request, response);	
		String messaggio = (String) request.getAttribute("messaggio");
		assertEquals("Salvataggio riuscito", messaggio);
	}
}
