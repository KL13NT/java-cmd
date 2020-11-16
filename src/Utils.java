import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
	public static void writeFile(String data, String path) throws IOException {
		File file = new File(App.pwd, path);
		file.createNewFile();

		FileWriter fr = new FileWriter(file, false);
		BufferedWriter br = new BufferedWriter(fr);

		br.write(data);

		br.close();
		fr.close();
	}

	public static void appendFile(String data, String path) throws IOException {
		File file = new File(App.pwd, path);
		FileWriter fr = new FileWriter(file, true);
		BufferedWriter br = new BufferedWriter(fr);

		br.write(data);

		br.close();
		fr.close();
	}
	// All methods here should be public static. Return types are up to you.

	public static Boolean arrayContains(String[] arr, String val) {
		for (String n : arr) {
			if (val.equalsIgnoreCase(n))
				return true;
		}

		return false;
	}
}
