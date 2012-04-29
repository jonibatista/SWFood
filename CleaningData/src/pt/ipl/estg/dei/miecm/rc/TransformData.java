package pt.ipl.estg.dei.miecm.rc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.Normalizer;
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
				line = Normalizer.normalize(line, Normalizer.Form.NFD);
				line = line.replaceAll("[^\\p{ASCII}]", "").toLowerCase();

				content.add(line);
				System.out.println(content.get(content.size() - 1));
			}

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
}
