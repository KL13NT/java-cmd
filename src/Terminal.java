import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Terminal {
	// public void cp(String sourcePath, String destinationPath);

	// public void mv(String sourcePath, String destinationPath);
	public void ls() {
		File CurrentDir = new File(System.getProperty("user.dir"));
		String files[] = CurrentDir.list();
		for (int i = 0; i < files.length; i++)
			System.out.println(files[i]);
	}

	public void rm(String sourcePath) {
		File file = new File(sourcePath);
		file.delete();
	}

	public void pwd() {
		System.out.println(System.getProperty("user.dir"));
	}

	public void cat(String[] paths) throws FileNotFoundException {
		File object;
		Scanner scan = null;
		for (int i = 0; i < paths.length; i++) {
			object = new File(paths[i]);
			scan = new Scanner(object);
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
				// No new lines
				// System.out.print(scan.nextLine());

			}
		}
		scan.close();
	}

	public void date() {

		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
		System.out.println(format.format(datetime));
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
	}
}