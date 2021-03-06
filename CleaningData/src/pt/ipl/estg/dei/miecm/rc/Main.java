package pt.ipl.estg.dei.miecm.rc;

public class Main {
	public static final String FILE_A = "BD-A_source.txt";
	public static final String FILE_B = "BD-B_source.txt";
	public static final String FILE_A_CLEAN = "BD-A_clean.txt";
	public static final String FILE_B_CLEAN = "BD-B_clean.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TransformData trans = new TransformData();

		// data source A
		trans.cleanData(FILE_A);
		trans.saveFile(FILE_A_CLEAN);

		// data source B
		trans.cleanData(FILE_B);
		trans.saveFile(FILE_B_CLEAN);
	}
}
