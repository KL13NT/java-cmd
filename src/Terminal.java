import java.util.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Terminal {
	// public void cp(String sourcePath, String destinationPath);

	// public void mv(String sourcePath, String destinationPath);
	public String[] ls(ArrayList<String> path) {
		File CurrentDir;
		if (path.isEmpty())
			CurrentDir = new File(App.pwd); // Working directory
		else
			CurrentDir = new File(path.get(0)); // specific path
		String files[] = CurrentDir.list();
		for (int i = 0; i < files.length; i++)
			System.out.println(files[i]);
		return files;
	}

	public void rm(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must supply 1 parameter");
		File file = new File(args.get(0));
		file.delete();
	}

	public String pwd(ArrayList<String> args) {
		System.out.println(App.pwd);
		return App.pwd;
	}

	public String cat(ArrayList<String> paths) throws Exception {
		if (paths.size() < 1)
			throw new Exception("Must provide 1 parameter");
		File object;
		String output="";
		Scanner scan = null;
		for (int i = 0; i < paths.size(); i++) {
			object = new File(paths.get(i));
			scan = new Scanner(object);
			while (scan.hasNextLine()) {
				output+=scan.nextLine();
			}
		}
		System.out.println(output);
		scan.close();
		return output;
	}

	public String date(ArrayList<String> args) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
		System.out.println(format.format(datetime));
		return format.format(datetime);
	}

	public void mkdir(ArrayList<String> paths) throws Exception {
		// if name is provided then name the dir
		try {
			if (paths.size() < 1)
				throw new Exception("Must supply 1 parameter");
			File Parentdir = new File(App.pwd, paths.get(0));
			// check if this Folder already exist
			if (Parentdir.exists())
				throw new FileAlreadyExistsException("");
			Parentdir.mkdir();
		} catch (FileAlreadyExistsException e) {
			System.out.println("File already exist with same name");
		}
	}

	public void rmdir(ArrayList<String> paths) throws Exception {
		if (paths.size() < 1)
			throw new Exception("Must supply 1 parameter");
		File file = new File(App.pwd, paths.get(0));
		file.delete();
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
	}
}