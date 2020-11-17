import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	public static String ls(ArrayList<String> args) throws FileNotFoundException {
		File file;

		if (args.isEmpty())
			file = new File(App.pwd);

		else
			file = new File(args.get(0));

		if (!file.exists())
			throw new FileNotFoundException("The system cannot find the file specified.");

		String files[] = file.list();
		String filesString = "";

		for (int i = 0; i < files.length; i++)
			filesString += files[i] + "\n";

		System.out.println(filesString);
		return filesString;
	}

	public static String rm(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File file = new File(Paths.get(App.pwd).resolve(args.get(0)).toString());

		if (!file.exists())
			throw new FileNotFoundException("The system cannot find the file specified.");

		if (!file.delete())
			throw new IOException("The system cannot find the file specified.");

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
			file = new File(App.pwd, args.get(i));

			if (!file.exists())
				throw new FileNotFoundException("The system cannot find the file specified.");

			scan = new Scanner(file);

			while (scan.hasNextLine())
				output += scan.nextLine() + "\n";
		}

		System.out.println(output);

		scan.close();
		return output;
	}

	public static String date(ArrayList<String> args) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");

		String formatted = format.format(datetime);

		System.out.println(formatted);

		return formatted;
	}

	public static String mkdir(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File Parentdir = new File(Paths.get(App.pwd).resolve(args.get(0)).toString());

		if (Parentdir.exists())
			throw new FileAlreadyExistsException("File already exist with same name");

		if (Parentdir.mkdir())
			return "";
		else
			throw new IOException("Failed to create directory");
	}

	public static String rmdir(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply at least 1 parameter");

		File file = new File(Paths.get(App.pwd).resolve(args.get(0)).toString());

		if (!file.exists())
			throw new FileNotFoundException("The system cannot find the directory specified.");

		if (!file.isDirectory())
			throw new FileNotFoundException("The system cannot find the directory specified.");

		if (file.list().length > 0)
			throw new DirectoryNotEmptyException("The directory is not empty.");

		file.delete();

		return "";
	}

	public static String cp(ArrayList<String> params) throws Exception {
		if (params.size() < 2)
			throw new Exception("Must provide at least 2 parameters");

		String sourcePath = params.get(0);
		String destinationPath = params.get(1);

		if (!Files.exists(Paths.get(sourcePath)))
			throw new FileNotFoundException("Source file not found");

		File sourceFile = new File(Paths.get(App.pwd).resolve(sourcePath).toString());
		File destFile = new File(Paths.get(App.pwd).resolve(destinationPath).toString());

		copy(sourceFile, destFile);
		return "";
	}

	public static String mv(ArrayList<String> args) throws IOException {
		String sourcePath = Paths.get(App.pwd).resolve(args.get(0)).toString();
		String destinationPath = Paths.get(App.pwd).resolve(args.get(1)).toString();

		if (!Files.exists(Paths.get(sourcePath)))
			throw new FileNotFoundException("Source file not found");

		File sourceFile = new File(sourcePath);
		File destFile = new File(destinationPath);

		move(sourceFile, destFile);
		return "";
	}

	private static void copy(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		input = new FileInputStream(source);
		output = new FileOutputStream(dest);

		byte[] buffer = new byte[1024];
		int length;

		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}

		input.close();
		output.close();
	}

	private static void move(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		input = new FileInputStream(source);
		output = new FileOutputStream(dest);

		byte[] buffer = new byte[1024];
		int length;

		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}

		input.close();
		output.close();
		source.delete();
	}

	public static String cd(ArrayList<String> args) throws IOException {
		Path pwd = Paths.get(App.pwd);
		Path inputPath = Paths.get(args.get(0));
		String resolvedPath = pwd.resolve(inputPath).toFile().getCanonicalPath();

		if (!Files.exists(Paths.get(resolvedPath)))
			throw new FileNotFoundException("The system cannot find the path specified.");

		App.pwd = resolvedPath;
		return "";
	}

	public static String more(ArrayList<String> args) throws Exception {
		if (args.size() < 2)
			throw new Exception("Must supply at least 2 parameter");

		File file = new File(App.pwd, args.get(0));

		if (!file.exists())
			throw new FileNotFoundException("The system cannot find the file specified.");

		int nLines = Integer.valueOf(args.get(1));
		String output = "";
		String input = "";

		Scanner scan = new Scanner(file);

		for (int i = 0; i < nLines; i++) {
			if (!scan.hasNextLine()) {
				scan.close();
				return output;
			}

			String line = scan.nextLine();
			output += line;

			System.out.println(line);
		}

		while (scan.hasNextLine()) {
			if (App.scan.hasNextLine()) {
				input = App.scan.nextLine();
			}

			if (input.equalsIgnoreCase("")) {
				String line = scan.nextLine();
				output += line;

				System.out.print(line);
			}

			else if (input.equalsIgnoreCase("q")) {
				scan.close();
				return output;
			}

			else if (input.equalsIgnoreCase(" ")) {
				for (int i = 0; i < nLines; i++) {
					if (!scan.hasNextLine())
						break;

					String line = scan.nextLine();
					output += line;

					System.out.println(line);
				}
			}
		}

		scan.close();
		return output;
	}

	public static String args(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must provide 1 parameter");

		String cmd = args.get(0);

		File file = new File("./args/" + cmd + ".txt");

		if (!file.exists())
			throw new Exception("Command help page not found");

		Scanner sc = new Scanner(file);

		String output = "";

		while (sc.hasNextLine())
			output += sc.nextLine() + "\n";

		sc.close();

		System.out.println(output);

		return output;
	}

	public static String help(ArrayList<String> args) throws IOException {
		File file = new File("help.txt");

		System.out.println(file.getCanonicalPath());

		Scanner sc = new Scanner(file);

		String output = "";

		while (sc.hasNextLine())
			output += sc.nextLine() + "\n";

		sc.close();

		System.out.println(output);
		return output;
	}

	public static String clear(ArrayList<String> args) {
		System.out.println("\r\n".repeat(5000));

		return "";
	}

}
