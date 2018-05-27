package cat.meteo.model;

public class Temperatura {

	public enum Unitat { FAHRENHEIT, CELSIUS };
	
	private Float valor;
	private Unitat unitat;
	
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Unitat getUnitat() {
		return unitat;
	}
	public void setUnitat(Unitat unitat) {
		this.unitat = unitat;
	}
	
	
	
}
