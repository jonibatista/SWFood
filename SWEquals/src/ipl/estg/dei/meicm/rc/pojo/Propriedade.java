package ipl.estg.dei.meicm.rc.pojo;

public class Propriedade {
	private String atributo;
	private String valor;
	private double peso;

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
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
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if (!(obj instanceof Propriedade))
			return false;
		if (((Propriedade)obj).getAtributo() == atributo && ((Propriedade)obj).getValor() == valor)
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "<propriedade atributo=\"" + atributo + "\">" + valor + "</propriedade>";
	}
}
