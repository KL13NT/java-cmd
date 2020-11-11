import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

	public static String pwd = System.getProperty("user.dir");

	public static void cd(ArrayList<String> params) throws IOException
	{
		//TODO: Using path objects in cd command by resolving the input path
		//https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html#resolve-java.lang.String-
		
		Path PWD = Paths.get(pwd);
		Path inputPath = Paths.get(params.get(0));
		String resolvedPath = PWD.resolve(inputPath).toFile().getCanonicalPath();
		
		if (Files.exists(Paths.get(resolvedPath))){
			System.out.println("new Path: " + resolvedPath);
			pwd = resolvedPath;	

		}else System.out.println("The system cannot find the path specified.");
	}
	public static void main(String[] args) {
		try {
			System.out.println("Hello");
			ArrayList<String> test = new ArrayList<String>();
			test.add("./source");
			cd(test);
			// Invoking commands can be done using reflection

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
