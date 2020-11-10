import java.io.IOException;
import java.util.ArrayList;

public class App {

	public static String pwd = System.getProperty("user.dir");

	public static void cd(ArrayList<String> params) throws IOException
	{
		String Path = params.get(0);
		//TODO: Using path objects in cd command by resolving the input path
		//https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html#resolve-java.lang.String-
	}
	public static void main(String[] args) {
		try {
			System.out.println("Hello");

			// Invoking commands can be done using reflection

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
