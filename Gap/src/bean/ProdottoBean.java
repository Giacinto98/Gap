package bean;

import java.io.Serializable;

public class ProdottoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int codice;
	String nome;
	int altezza;
	int profondita;
	int larghezza;
	String tipologia;
	int quantita;
	float prezzo;
	int sconto;
	
	public ProdottoBean()
	{
		codice = -1;
		nome = "";
		altezza = 0;
		profondita = 0;
		larghezza = 0;
		tipologia = "";
		quantita = 0;
		prezzo = 0;
		sconto = 0;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}

	public int getProfondita() {
		return profondita;
	}

	public void setProfondita(int profondita) {
		this.profondita = profondita;
	}

	public int getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

}
