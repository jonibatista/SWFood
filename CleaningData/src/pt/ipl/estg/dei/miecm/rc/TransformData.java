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

				// remove special chars... it's only allowed alphanumeric and space
				// characters
				line = line.replaceAll("[^a-zA-Z0-9 ]+","");

				content.add(line);
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
					.getResource(fileName).getPath(), true);

			for (String line : content) {
				writer.write(line);
			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		str = str.replaceAll("[ˆ‡‹]+", "a");

		// replace by <i><p>A</p><i>
		str = str.replaceAll("[ËçåÌ]+", "A");

		// replace by <i><p>e</p><i>
		str = str.replaceAll("[]+", "e");

		// replace by <i><p>E</p><i>
		str = str.replaceAll("[éƒæ]+", "E");

		// replace by <i><p>i</p><i>
		str = str.replaceAll("[“’”]+", "i");

		// replace by <i><p>I</p><i>
		str = str.replaceAll("[íêë]+", "I");

		// replace by <i><p>o</p><i>
		str = str.replaceAll("[˜—›™]+", "o");

		// replace by <i><p>O</p><i>
		str = str.replaceAll("[ñîïÍ]+", "O");

		// replace by <i><p>u</p><i>
		str = str.replaceAll("[œ]+", "u");

		// replace by <i><p>U</p><i>
		str = str.replaceAll("[ôòó]+", "U");

		return str;
	}
}
