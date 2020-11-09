import java.util.*;
import java.io.*;

public class Terminal {
	// public void cp(String sourcePath, String destinationPath);

	// public void mv(String sourcePath, String destinationPath);

	public void rm(String sourcePath){
		File file=new File(sourcePath);
		file.delete();
	}

	public  void pwd(){
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

	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
	}
}