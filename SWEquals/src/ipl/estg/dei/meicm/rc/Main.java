package ipl.estg.dei.meicm.rc;

import ipl.estg.dei.meicm.rc.pojo.Produto;

import java.io.IOException;
import java.util.List;

public class Main {
	public static final String FILE_A = "BD-A_source.xml";
	public static final String FILE_B = "BD-B_source.xml";
	public static final String FILE_PESOS = "pesos.xml";
	public static final String FILE_OUTPUT = "output.xml";
	public static final String FILE_OWL = "";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("START...");
		ReadXmlDS calc = new ReadXmlDS();

		try {
			List<Produto> produtosA = calc.readDataSource(FILE_A);
			List<Produto> produtosB = calc.readDataSource(FILE_B);

			// calcular pesos
			CalculateWeight c = new CalculateWeight();
			produtosA = c.calculate(produtosA, FILE_PESOS);
			produtosB = c.calculate(produtosB, FILE_PESOS);

			// fazer o matching dos produtos
			Equals eq = new Equals();
			List<Produto> prods = eq.matchProducts(produtosA, produtosB);

			// escrever o output para um ficheiro final
			WriteXmlOutput wOut = new WriteXmlOutput();
			wOut.write(prods, FILE_OUTPUT);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("DONE...");

	}

}
