package pt.ipl.estg.dei.miecm.rc;

import java.io.IOException;

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

		trans.readFile(FILE_A);
	}
}
