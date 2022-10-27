package bean;

import java.io.Serializable;

public class RecensioneBean implements Serializable {
	private static final long serialVersionUID = 1L;


String testo;
String data;
int codice;
String  email;

	public RecensioneBean() {
	testo="";
	data="";
	codice=-1;
	email="";
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}