package model;

import java.io.Serializable;

public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String cf;
	String nome;
	String cognome;
	String email;
	String password;
	String indirizzo;
	String telefono;
	String ruolo;
	
	public UtenteBean ()
	{
		cf = "";
		nome = "";
		cognome = "";
		email = "";
		password = "";
		indirizzo = "";
		telefono = "";
		ruolo = "";
		
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo_fatturazione) {
		this.indirizzo = indirizzo_fatturazione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
}
