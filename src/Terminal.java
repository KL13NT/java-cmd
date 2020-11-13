import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

		File file = new File(args.get(0));

		if (!file.exists())
			throw new FileNotFoundException("The system cannot find the file specified.");

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

			if (!file.exists())
				throw new FileNotFoundException("The system cannot find the file specified.");

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

		String formatted = format.format(datetime);

		System.out.println(formatted);

		return formatted;
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

		if (!file.exists())
			throw new FileNotFoundException("The system cannot find the file specified.");

		file.delete();

		return "";
	}

	public static String cp(ArrayList<String> params) throws IOException {
		String sourcePath = params.get(0);
		String destinationPath = params.get(1);

		if (!Files.exists(Paths.get(sourcePath)))
			throw new FileNotFoundException("Source file not found");

		File sourceFile = new File(sourcePath);
		File destFile = new File(destinationPath);

		copy(sourceFile, destFile);
		return "";
	}

	public static String mv(ArrayList<String> args) throws IOException {
		String sourcePath = args.get(0);
		String destinationPath = args.get(1);

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
		// TODO: Using path objects in cd command by resolving the input path
		// https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html#resolve-java.lang.String-

		Path pwd = Paths.get(App.pwd);
		Path inputPath = Paths.get(args.get(0));
		String resolvedPath = pwd.resolve(inputPath).toFile().getCanonicalPath();

		if (!Files.exists(Paths.get(resolvedPath)))
			throw new FileNotFoundException("The system cannot find the path specified.");

		App.pwd = resolvedPath;
		return "";
	}
}