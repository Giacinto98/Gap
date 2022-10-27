package main.test.java.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.junit.Test;
import org.mockito.Mockito;

import bean.CarrelloBean;
import bean.UtenteBean;
import control.AcquistoControl;
import control.LoginControl;
import control.RegControl;

public class RegistrazioneControlTest {
	@Test
	public void testDoGet () throws ServletException, IOException {
		HttpServletRequest request = Mockito.mock (HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock (HttpServletResponse.class);
		Mockito.when(request.getParameter("cf")).thenReturn("dnlgnt95a25d120s");
		Mockito.when(request.getParameter("Nome")).thenReturn("Giacinto");
		Mockito.when(request.getParameter("cognome")).thenReturn("Adinolfi");
		Mockito.when(request.getParameter("email")).thenReturn("Marco95@live.it");
		Mockito.when(request.getParameter("password")).thenReturn("Marco95@live");
		Mockito.when(request.getParameter("indirizzo")).thenReturn("via cascata 14");
		Mockito.when(request.getParameter("telefono")).thenReturn("3256985745");
		RegControl serv = new RegControl();
		//serv.doPost (request, response);	
		String messaggio = (String) request.getAttribute("error");
		assertEquals("Salvataggio riuscito", messaggio);
	}
}