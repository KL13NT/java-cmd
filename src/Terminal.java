
/**
 * Terminal commands go here
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Terminal {

	public static void cp(ArrayList<String> params) throws IOException {
		String sourcePath = params.get(0);
		String destinationPath = params.get(1);

		if (Files.exists(Paths.get(sourcePath)))
		{
			File sourceFile = new File(sourcePath);
			File destFile = new File(destinationPath);
	
			copy(sourceFile, destFile);
		}
		else System.out.println("Source file not found");
	}

	public static void mv(ArrayList<String> params) throws IOException {
		String sourcePath = params.get(0);
		String destinationPath = params.get(1);
		
		if (Files.exists(Paths.get(sourcePath)))
		{
			File sourceFile = new File(sourcePath);
			File destFile = new File(destinationPath);
		
			move(sourceFile, destFile);
		}
		else System.out.println("Source file not found");
	}

	// TEST COMPLETED
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

	// TEST COMPLETED
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

	public static void cd(ArrayList<String> params) throws IOException
	{
		//TODO: Using path objects in cd command by resolving the input path
		//https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html#resolve-java.lang.String-
		
		Path PWD = Paths.get(App.pwd);
		Path inputPath = Paths.get(params.get(0));
		String resolvedPath = PWD.resolve(inputPath).toFile().getCanonicalPath();
		
		if (Files.exists(Paths.get(resolvedPath))){
			System.out.println("new Path: " + resolvedPath);
			App.pwd = resolvedPath;	

		}else System.out.println("The system cannot find the path specified.");
	}

	public static void main(String[] args) throws Exception {
		try
		{
			System.out.println("Hello, World!");
	
			ArrayList<String> test = new ArrayList<String>();
			test.add("test.txt");
			test.add("F:\\College\\Operating Systems\\haha.txt");
			mv(test);

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}