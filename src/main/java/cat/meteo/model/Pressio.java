package cat.meteo.model;

public class Pressio {
	
	public enum Unitat { KILOPASCAL,HECTOPASCAL,MILIBAR }
	
	private Float valor;
	Unitat unitat;
	
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
