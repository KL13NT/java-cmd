import java.util.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
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

	public void rm(ArrayList<String> args)throws Exception{
		if(args.size()<1) throw new Exception("Must supply 1 parameter");
		else{
		File file = new File(args.get(0));
		file.delete();
		}
	}

	public void pwd() {
		System.out.println(System.getProperty("user.dir"));
	}

	public void cat(ArrayList<String> paths) throws Exception {
		if(paths.size()<1)throw new Exception("Must provide 1 parameter");
		else{
			File object;
			Scanner scan = null;
			for (int i = 0; i < paths.size(); i++) {
				object = new File(paths.get(i));
				scan = new Scanner(object);
				while (scan.hasNextLine()) {
					System.out.println(scan.nextLine());
					// No new lines
					// System.out.print(scan.nextLine());
	
				}
			}
			scan.close();
		}
	}

	public void date() {

		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
		System.out.println(format.format(datetime));
	}

	public void mkdir(ArrayList<String> Path) throws Exception{
		// if name is provided then name the dir
		try {
			if (Path.size() < 1)
				throw new Exception("Must supply 1 parameter");
			else {
				File Parentdir;
				if (Utils.isAbsolutePath(Path.get(0))) {
					// this is absolutepath
					Parentdir = new File(Path.get(0));
				} else // is relative path, so create dir in working directory
					Parentdir = new File(System.getProperty("user.dir") + "/" + Path);
				// check if this Folder already exist
				if (Parentdir.exists())
					throw new FileAlreadyExistsException("");
				Parentdir.mkdir();
			}
		} catch (FileAlreadyExistsException e) {
			System.out.println("File already exist with same name");
		}

	}

	public void rmdir(ArrayList<String> Path) throws Exception {
		if (Path.size() < 1)
				throw new Exception("Must supply 1 parameter");
				else{	
					if (!Utils.isAbsolutePath(Path.get(0)))
					Path.set(0,System.getProperty("user.dir") + "/" + Path.get(0));
				File file = new File(Path.get(0));
				file.delete();
			}
	
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
	}
}