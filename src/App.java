import java.util.ArrayList;

public class App {

	public static String pwd = System.getProperty("user.dir");


	public static void main(String[] args) {
		try {
			System.out.println("Hello");
			ArrayList<String> test = new ArrayList<String>();
			// Invoking commands can be done using reflection

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
