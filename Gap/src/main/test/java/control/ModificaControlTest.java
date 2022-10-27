package main.test.java.control;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.mockito.Mockito;
import control.RegControl;

public class ModificaControlTest {
	@Test
	public void testDoGet () throws ServletException, IOException {
		HttpServletRequest request = Mockito.mock (HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock (HttpServletResponse.class);
		Mockito.when(request.getParameter("codice")).thenReturn("8");
		Mockito.when(request.getParameter("quantita")).thenReturn("150");
		Mockito.when(request.getParameter("sconto")).thenReturn("15");
		Mockito.when(request.getParameter("prezzo")).thenReturn("1500");
		RegControl serv = new RegControl();
		//serv.doGet (request, response);
		String messaggio = (String) request.getAttribute("errore2");
		assertEquals("Modifica avvenuta con successo", messaggio);
	}
}
