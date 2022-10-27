package main.test.java.control;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.Mockito;

import bean.UtenteBean;
import control.LoginControl;

public class LoginServletTest {

	@Test
	public void testDoPost () throws ServletException, IOException {
		HttpServletRequest request = Mockito.mock (HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock (HttpServletResponse.class);
		Mockito.when(request.getParameter("username")).thenReturn("giaci95@live.it");
		Mockito.when(request.getParameter("password")).thenReturn("root");
		LoginControl serv = new LoginControl();
		//serv.doGet (request, response);	
		HttpSession sessione = request.getSession(false);
		UtenteBean utente = (UtenteBean) sessione.getAttribute("utente");
		assertEquals("giaci95@live.it",utente.getEmail());
	}
}
