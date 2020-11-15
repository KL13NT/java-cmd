import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class App {
	public static String pwd = System.getProperty("user.dir");
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("OS-CLI!");

		while (true) {
			System.out.print("\nOS-CLI | " + pwd + ">");

			try {
				if (!scan.hasNextLine())
					continue;

				String input = scan.nextLine();

				if (input.trim().length() == 0)
					continue;

				Boolean shouldPipe = false;
				Stack<String> results = new Stack<>();
				Stack<Node> tree = Parser.parse(input);
				Iterator<Node> it = tree.iterator();

				while (it.hasNext()) {
					Node current = it.next();

					if (current.type.equals("GENERIC")) {
						String command = current.val;

						if (command.equalsIgnoreCase("EXIT")) {
							scan.close();
							return;
						}

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
							case "ls": {
								results.push(Terminal.ls(currentParams));
								break;
							}
							case "rm": {
								results.push(Terminal.rm(currentParams));
								break;
							}
							case "pwd": {
								results.push(Terminal.pwd(currentParams));
								break;
							}
							case "cat": {
								results.push(Terminal.cat(currentParams));
								break;
							}
							case "date": {
								results.push(Terminal.date(currentParams));
								break;
							}
							case "mkdir": {
								results.push(Terminal.mkdir(currentParams));
								break;
							}
							case "rmdir": {
								results.push(Terminal.rmdir(currentParams));
								break;
							}
							case "cp": {
								results.push(Terminal.cp(currentParams));
								break;
							}
							case "mv": {
								results.push(Terminal.mv(currentParams));
								break;
							}
							case "cd": {
								results.push(Terminal.cd(currentParams));
								break;
							}
							case "more": {
								results.push(Terminal.more(currentParams));
								break;
							}
							default: {
								throw new Exception("'" + command + "'" + " is not recognized as a command,");
							}
						}
					}

					if (current.type.equalsIgnoreCase("TOKEN")) {
						String token = current.val;

						System.out.println(results);
						System.out.println(tree);

						if (token.equals("|")) {
							shouldPipe = true;
						} else if (token.equals(">")) {
							String path = it.next().val;

							Utils.writeFile(results.peek(), path);
						} else if (token.equals(">>")) {
							String path = it.next().val;

							Utils.appendFile(results.peek(), path);
						}
					}
				}
			} catch (Exception e) {
				if (e.getMessage() != null)
					System.out.println(e.getMessage());
				else
					System.out.println(e);

				e.printStackTrace();
			}
		}
	}
}
