package ipl.estg.dei.meicm.rc;

import ipl.estg.dei.meicm.rc.pojo.Produto;
import ipl.estg.dei.meicm.rc.pojo.Propriedade;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteXmlOutput {

	public void write(List<Produto> produtos, String file) throws IOException {

		FileWriter out = new FileWriter("/home/jbatista/Desktop/" + file, false);
		
		out.write("<xml version=\"1.0\" encoding=\"utf-8\">\n");

		for (Produto p : produtos) {
			out.write("<linha valor=\"" + p.toString() +"\">\n");

			out.write("<produto familia=\"" + p.getClasse().getFamilia()
					+ "\">" + p.getClasse().getValor() + "</produto>\n");

			for (Propriedade prop : p.getPropriedades()) {
				out.write("<propriedade atributo=\"" + prop.getAtributo()
						+ "\">" + prop.getValor() + "</propriedade>\n");
			}

			for (Propriedade propS : p.getSinonimo().getPropriedades()) {
				boolean exist = false;

				for (Propriedade prop : p.getPropriedades()) {
					if (propS.getAtributo().equals(prop.getAtributo())
							&& propS.getValor().equals(prop.getValor())) {
						exist = true;
						break;
					}
				}

				if (!exist)
					out.write("<propriedade atributo=\"" + propS.getAtributo()
							+ "\">" + propS.getValor() + "</propriedade>\n");
			}

			out.write("</linha>\n");
		}

		out.write("</xml>");

		out.flush();
		out.close();
	}
}
