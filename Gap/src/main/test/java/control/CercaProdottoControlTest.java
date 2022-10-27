package main.test.java.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import bean.UtenteBean;
import control.CambiaPasswordControl;

public class CercaProdottoControlTest {
	@Test
	public void testDoPost () throws ServletException, IOException {
		HttpServletRequest request = Mockito.mock (HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock (HttpServletResponse.class);
		Mockito.when(request.getParameter("bottone")).thenReturn("zelbio");
		CambiaPasswordControl serv = new CambiaPasswordControl();
		//serv.doPost (request, response);	
		String messaggio = (String) request.getAttribute("messaggio");
		assertEquals("Ricerca riuscita", messaggio);
	}
}
