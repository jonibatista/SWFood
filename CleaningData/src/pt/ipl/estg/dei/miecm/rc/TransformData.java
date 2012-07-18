package pt.ipl.estg.dei.miecm.rc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TransformData {
	private ArrayList<String> content;

	public TransformData() {
		// TODO Auto-generated constructor stub
		content = new ArrayList<String>();
	}

	/**
	 * <p>
	 * Clean and keeps the data in memory.
	 * </p>
	 * 
	 * @param fileName
	 *            the data source name
	 */
	public void cleanData(String fileName) {
		String line = "";
		content.clear();

		try {
			File file = new File(this.getClass().getClassLoader()
					.getResource(fileName).getPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));

			// BufferedReader reader = new BufferedReader(new FileReader(this
			// .getClass().getClassLoader().getResource(fileName)
			// .getPath()));

			while ((line = reader.readLine()) != null) {

				// remove accents markes
				line = replaceAccentsBySimilarChar(line.toLowerCase());

				// remove special chars... it's only allowed alphanumeric and
				// space
				// characters
				line = line.replaceAll("[^a-zA-Z0-9 .%/!]+", "");

				// meio gordo deve ser interpretado como uma única palavra,
				// entre outros
				// line = line.replaceAll("meio gordo","meio_gordo");
				// line = line.replaceAll("em po","em_po");
				// line = line.replaceAll("sem_alcool","sem_alcool");

				// remover dados irrelevantes
				line = line.replaceAll(" d[aeo]s ", " ");
				line = line.replaceAll(" d[aeo] ", " ");
				//line = line.replaceAll("meio gordo", "meio_gordo");
				line = line.replaceAll(" d[aeo] ", " ");
				//line = line.replaceAll("nao alcoolicas", "");

				// mudar a ordem dos tokens
				/*if (line.contains("destiladas")) {
					line = line.replace("destiladas", "");
					line += " destiladas";
				}

				if (line.contains("fermentadas")) {
					line = line.replace("fermentadas", "");
					line += " fermentadas";
				}

				if (line.contains("alcoolicas")) {
					line = line.replace("alcoolicas", "");
					line += " alcoolicas";
				}

				if (line.contains(" cola ") || line.contains(" cola") || line.contains(" coca ")
						|| line.contains("coca-cola") || line.contains("cola cola")) {
					line = line.replace(" cola ", " ");
					line = line.replace(" coca ", " ");
					line = line.replace("coca cola", "");
					line = line.replace("coca-cola", "");
					line = "cola " + line;
				}
				
				if (line.contains("gasosa")){
					line = line.replace("gasosa", "");
					line = "gasosa " + line;
				}*/

				// TODO
				// each line only can end with % or alphanumeric
				// !line.endsWith("[^a-zA-Z0-9 .%]"); => replace("")

				line = line.replaceAll("[  ]+", " ");
				content.add(line.trim());
				System.out.println(content.get(content.size() - 1));
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Write the clean data to file.
	 * </p>
	 * 
	 * @param fileName
	 *            the output data source file
	 */
	public void saveFile(String fileName) {
		FileWriter writer;

		try {
			writer = new FileWriter(this.getClass().getClassLoader()
					.getResource(fileName).getPath(), false);

			for (String line : content) {
				writer.write(line + "\n");
			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private String removeDupsWhitespaces(String s, char cToRemove){ char c =
	 * s.charAt(0);
	 * 
	 * for (int i = 1; i < s.length(); i++) { if(c == s.charAt(i)) // if the
	 * characters are duplicated s.re }
	 * 
	 * return s; }
	 */

	/**
	 * <p>
	 * Replacing accents by their counterparts
	 * </p>
	 * 
	 * @param str
	 *            string with accents
	 * @return string without accents
	 */
	public String replaceAccentsBySimilarChar(String str) {

		// replace by <i><p>a</p><i>
		str = str.replaceAll("[àáã]+", "a");

		// replace by <i><p>A</p><i>
		str = str.replaceAll("[ÁÀÃÂ]+", "A");

		// replace by <i><p>e</p><i>
		str = str.replaceAll("[éèẽê]+", "e");

		// replace by <i><p>E</p><i>
		str = str.replaceAll("[ÉÈÊẼ]+", "E");

		// replace by <i><p>i</p><i>
		str = str.replaceAll("[íìĩî]+", "i");

		// replace by <i><p>I</p><i>
		str = str.replaceAll("[ÍÌÎĨ]+", "I");

		// replace by <i><p>o</p><i>
		str = str.replaceAll("[óòõô]+", "o");

		// replace by <i><p>O</p><i>
		str = str.replaceAll("[ÓÒÕÔ]+", "O");

		// replace by <i><p>u</p><i>
		str = str.replaceAll("[úùũû]+", "u");

		// replace by <i><p>U</p><i>
		str = str.replaceAll("[ÚÙÛŨ]+", "U");

		// replace by <i><p>Ç</p><i>
		str = str.replaceAll("[çÇ]+", "c");

		return str;
	}
}
