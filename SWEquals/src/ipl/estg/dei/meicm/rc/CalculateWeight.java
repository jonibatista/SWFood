package ipl.estg.dei.meicm.rc;

import ipl.estg.dei.meicm.rc.pojo.Produto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

/**
 * 
 * <h1>Calcular os pesos dos tokens</h1>
 * 
 */
public class CalculateWeight {

	public List<Produto> calculate(List<Produto> prods, String pesos) throws FileNotFoundException{
		List<Produto> produtos = prods;
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		

		for (int i = 0; i < produtos.size(); i++) {
			// get lines
			try {
				produtos.get(i).getClasse().setPeso(Double.parseDouble((String) xpath
									.evaluate("xml/classe[@familia='" + produtos.get(i).getClasse().getFamilia() + "']/text()", new InputSource(new FileReader(this
											.getClass().getClassLoader().getResource(pesos)
											.getPath())), XPathConstants.STRING)));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int j = 0; j < produtos.get(i).getPropriedades().size(); j++){
				try {
					produtos.get(i).getPropriedades().get(j).setPeso(Double.parseDouble((String) xpath
									.evaluate("xml/propriedade[@atributo='" + produtos.get(i).getPropriedades().get(j).getAtributo() + "']/text()", new InputSource(new FileReader(this
											.getClass().getClassLoader().getResource(pesos)
											.getPath())), XPathConstants.STRING)));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return produtos; 
	}
}
