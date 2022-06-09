package API;

import java.sql.Date;

public class pagamenti {

	public int id;
	public Date data;
	public String esito;
	public double valore;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
}
