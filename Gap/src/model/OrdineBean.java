package model;

import java.io.Serializable;
import java.time.LocalDate;

public class OrdineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	double numeroOrdine;
	String email;
	float prezzoTotale;
	int numeroProdotti;
	LocalDate dataOrdine;
	
	public OrdineBean()
	{
		numeroOrdine = -1;
		email = "";
		prezzoTotale = -1;
		numeroProdotti = -1;
	}

	public double getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(double numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public int getNumeroProdotti() {
		return numeroProdotti;
	}

	public void setNumeroProdotti(int numeroProdotti) {
		this.numeroProdotti = numeroProdotti;
	}

	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(LocalDate localDate) {
		this.dataOrdine = localDate;
	}
	
	
}
