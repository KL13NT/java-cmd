import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
	// All methods here should be public static. Return types are up to you.

	public static void saveToFile(String data, String path) throws IOException {
		new File(path).createNewFile();

		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		writer.write(data);

		writer.close();
	}
}
