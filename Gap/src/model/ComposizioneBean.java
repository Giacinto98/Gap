package model;

import java.io.Serializable;

public class ComposizioneBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	int numeroOrdine;
	int codiceProdotto;
	int quantia;
	
	public ComposizioneBean()
	{
		numeroOrdine = -1;
		codiceProdotto = -1;
		quantia = -1; 
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public int getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(int codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public int getQuantia() {
		return quantia;
	}

	public void setQuantia(int quantia) {
		this.quantia = quantia;
	}
	
	
	

}
