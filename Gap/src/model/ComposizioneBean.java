package model;

import java.io.Serializable;

public class ComposizioneBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	int idMateriale;
	double numeroOrdine;
	int codiceProdotto;
	int quantita;
	
	public ComposizioneBean()
	{
		numeroOrdine = -1;
		codiceProdotto = -1;
		quantita = -1; 
		idMateriale = -1;
	}

	public int getIdMateriale() {
		return idMateriale;
	}

	public void setIdMateriale(int idMateriale) {
		this.idMateriale = idMateriale;
	}

	public double getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(double casuale) {
		this.numeroOrdine = casuale;
	}

	public int getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(int codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantia) {
		this.quantita = quantia;
	}
	
	
	

}
