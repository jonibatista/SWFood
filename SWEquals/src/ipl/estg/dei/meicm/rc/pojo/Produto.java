package ipl.estg.dei.meicm.rc.pojo;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	private Classe classe;
	private List<Propriedade> propriedades;
	private double pesoTotal;
	private Produto sinonimo;

	public Produto() {
		propriedades = new ArrayList<Propriedade>();
		pesoTotal = 0;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public List<Propriedade> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<Propriedade> propriedades) {
		this.propriedades = propriedades;
	}

	public double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public Produto getSinonimo() {
		return sinonimo;
	}

	public void setSinonimo(Produto sinonimo) {
		this.sinonimo = sinonimo;
	}

	@Override
	public String toString() {
		String str = classe.getValor();

		for (Propriedade p : propriedades) {
			str += " " + p.getValor();
		}

		return str;
	}
}
