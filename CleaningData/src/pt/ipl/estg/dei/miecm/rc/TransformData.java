package pt.ipl.estg.dei.miecm.rc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TransformData {
	private ArrayList<String> content;

	public TransformData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fileName
	 * @return
	 */
	public ArrayList<String> cleanData(String fileName) {
		String line = "";
		ArrayList<String> content = new ArrayList<String>();

		try {
			File file = new File(this.getClass().getClassLoader()
					.getResource(fileName).getPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));

			// BufferedReader reader = new BufferedReader(new FileReader(this
			// .getClass().getClassLoader().getResource(fileName)
			// .getPath()));

			while ((line = reader.readLine()) != null) {

				content.add(line);
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	public void saveFile() {

	}
}