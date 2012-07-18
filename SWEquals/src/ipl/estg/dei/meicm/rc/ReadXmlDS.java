package ipl.estg.dei.meicm.rc;

import ipl.estg.dei.meicm.rc.pojo.Classe;
import ipl.estg.dei.meicm.rc.pojo.Produto;
import ipl.estg.dei.meicm.rc.pojo.Propriedade;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 
 * <h>Ler os tokens para memória</p>
 * 
 */
public class ReadXmlDS {

	/**
	 * 
	 * @param fileName
	 *            ficheiro xml com os dados fonte
	 * @return uma lista de produtos. Transformou as linhas do ficheiro xml em
	 *         objectos
	 * @throws FileNotFoundException
	 *             não consegui abrir o ficheiro. Este deve ser colocado na
	 *             pasta resource do projecto
	 */
	public List<Produto> readDataSource(String fileName)
			throws FileNotFoundException {
		List<Produto> produtos = new ArrayList<Produto>();

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();

		try {
			// get lines
			NodeList noProdutos = (NodeList) xpath
					.evaluate("xml/linha", new InputSource(new FileReader(this
							.getClass().getClassLoader().getResource(fileName)
							.getPath())), XPathConstants.NODESET);

			for (int i = 0; i < noProdutos.getLength(); i++) {

				Produto p = new Produto();

				// line element
				Element e = (Element) noProdutos.item(i);

				// product line class attribute
				Classe c = new Classe();
				c.setFamilia((String) xpath.evaluate("produto/@familia", e,
						XPathConstants.STRING));

				// product line class value
				c.setValor((String) xpath.evaluate("produto/text()", e,
						XPathConstants.STRING));
				p.setClasse(c);

				// line product properties attribute value
				NodeList n = (NodeList) xpath.evaluate("propriedade/@atributo",
						e, XPathConstants.NODESET);

				for (int j = 0; j < n.getLength(); j++) {
					Propriedade prop = new Propriedade();
					prop.setAtributo(n.item(j).getNodeValue());
					prop.setValor((String) xpath.evaluate(
							"propriedade[@atributo='" + prop.getAtributo()
									+ "']", e, XPathConstants.STRING));

					p.getPropriedades().add(prop);
				}

				produtos.add(p);
			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return produtos;
	}
}
