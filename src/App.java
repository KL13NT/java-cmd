import java.util.Scanner;
import java.util.Stack;

public class App {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to os-cli!");

		while (true) {
			try {
				if (!scan.hasNextLine())
					continue;

				String input = scan.nextLine();

				Stack<Node> tree = Parser.parse(input); // tree of all nodes

				// Invoking commands can be done using reflection

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
}
