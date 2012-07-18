package ipl.estg.dei.meicm.rc;

import ipl.estg.dei.meicm.rc.pojo.Produto;
import ipl.estg.dei.meicm.rc.pojo.Propriedade;

import java.util.List;

/**
 * 
 * <h1>Faz o mqtching entre os produtos</h1>
 * 
 * <p>
 * Para um produto do data source A escolhe o produto correspondente no data
 * source B. </br> O produto escolhido em B é o que tiver o peso mais elevado.
 * </p>
 */
public class Equals {

	/**
	 * 
	 * @param produtosA lista de produtos do data source A
	 * @param produtosB lista de produtos do data source B
	 * @return lista de produtos (A+B)
	 */
	public List<Produto> matchProducts(List<Produto> produtosA, List<Produto> produtosB) {
		// EQUALS
		for (Produto pA : produtosA) {
			for (Produto pB : produtosB) {
				double sum = 0;

				if (pA.getClasse().getFamilia()
						.equals(pB.getClasse().getFamilia())
						&& pA.getClasse().getValor()
								.equals(pB.getClasse().getValor())) {
					sum += pB.getClasse().getPeso();

					// EQUALS PARA OS PRODUTOS
					for (Propriedade propA : pA.getPropriedades()) {
						for (Propriedade propB : pB.getPropriedades()) {
							if (propA.getAtributo().equals(propB.getAtributo())
									&& propA.getValor()
											.equals(propB.getValor())) {
								sum += pB.getClasse().getPeso();
								break;
							}
						}
					}

					// se o total dos pesos for o mais elevado adiciona
					pA.setSinonimo(pA.getPesoTotal() < sum ? pB : pA
							.getSinonimo());
					pA.setPesoTotal(pA.getPesoTotal() < sum ? sum : pA
							.getPesoTotal());
				}
			}
			System.out.println(pA.toString() + " == "
					+ pA.getSinonimo().toString());
		}
		
		return produtosA;
	}
}
