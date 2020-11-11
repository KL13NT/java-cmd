import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class App {
	static String pwd = System.getProperty("user.dir");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to os-cli!");

		while (true) {
			System.out.print("\nOS-CLI | " + System.getProperty("user.dir") + ">");

			try {
				if (!scan.hasNextLine())
					continue;

				String input = scan.nextLine();

				Stack<Node> tree = Parser.parse(input); // tree of all nodes
				Iterator<Node> it = tree.iterator();
				Stack<String> results = new Stack<>();

				Boolean shouldPipe = false;

				while (it.hasNext()) {
					Node current = it.next();

					if (current.type.equals("GENERIC")) {
						String command = current.val;
						ArrayList<String> params = new ArrayList<>();

						while (it.hasNext()) {
							current = it.next();

							if (current.type.equals("GENERIC")) {
								params.add(current.val);
							} else
								break;
						}

						ArrayList<String> currentParams = params;

						if (shouldPipe) {
							currentParams.add(results.peek());
							shouldPipe = false;
						}

						switch (command) {
							case "pwd": {
								results.push(Terminal.pwd(currentParams));
								break;
							}
							default: {
								throw new Exception("'" + command + "'" + " is not recognized as a command,");
							}
						}
					}

					if (current.type.equalsIgnoreCase("TOKEN")) {
						String token = current.val;

						if (token.equals("|")) {
							System.out.println("SHOULD PIPE");
							shouldPipe = true;
						} else if (token.equals(">")) {
							System.out.println("REDIRECT");
							String path = it.next().val;

							Utils.saveToFile(results.peek(), path);
						}
					}
				}
				System.out.println("DONE");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e);
				System.out.println(e.getStackTrace());
			}
		}

	}
}
