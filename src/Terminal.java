import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	public static String ls(ArrayList<String> args) {
		File CurrentDir;

		if (args.isEmpty())
			CurrentDir = new File(App.pwd);

		else
			CurrentDir = new File(args.get(0));

		String files[] = CurrentDir.list();
		String filesString = "";

		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i]);
			filesString += files[i] + "\n";
		}

		return filesString;
	}

	public static String rm(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File file = new File(args.get(0));
		file.delete();

		return "";
	}

	public static String pwd(ArrayList<String> args) {
		System.out.println(App.pwd);

		return App.pwd;
	}

	public static String cat(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File file;
		String output = "";
		Scanner scan = null;

		for (int i = 0; i < args.size(); i++) {
			file = new File(args.get(i));
			scan = new Scanner(file);

			while (scan.hasNextLine())
				output += scan.nextLine();
		}

		System.out.println(output);

		scan.close();
		return output;
	}

	public static String date(ArrayList<String> args) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");

		System.out.println(format.format(datetime));

		return format.format(datetime);
	}

	public static String mkdir(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File Parentdir = new File(App.pwd, args.get(0));

		if (Parentdir.exists())
			throw new FileAlreadyExistsException("File already exist with same name");

		Parentdir.mkdir();
		return "";
	}

	public static String rmdir(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File file = new File(App.pwd, args.get(0));
		file.delete();

		return "";
	}
}