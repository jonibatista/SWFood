package ipl.estg.dei.meicm.rc.pojo;

public class Classe {
	private String familia;
	private String valor;
	private double peso;

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return "<classe familia=\"" + familia + "\">" + valor + "</classe>";
	}
}
