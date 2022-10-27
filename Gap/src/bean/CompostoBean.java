package bean;

import java.io.Serializable;

public class CompostoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int idMateriale;
	int codiceProdotto;
	
	public CompostoBean()
	{
		idMateriale = -1;
		codiceProdotto = -1;
	}

	public int getIdMateriale() {
		return idMateriale;
	}

	public void setIdMateriale(int idMateriale) {
		this.idMateriale = idMateriale;
	}

	public int getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(int codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}
}
