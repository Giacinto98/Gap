package bean;

public class MaterialeBean {
	
	int id;
	String tipologiaMateriale;
	String colore;
	
	public MaterialeBean()
	{
		id = -1;
		tipologiaMateriale = "";
		colore = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipologiaMateriale() {
		return tipologiaMateriale;
	}

	public void setTipologiaMateriale(String tipologiaMateriale) {
		this.tipologiaMateriale = tipologiaMateriale;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
}
