import java.util.Stack;

public class Parser {
	public static Stack<Node> parse(String input) throws Exception {
		Stack<Node> tree = new Stack<>();
		String word = "";

		if ((input.charAt(0) == '|' || input.charAt(0) == '>'))
			throw new LocatedException("You must supply a command first", input, 1);

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			switch (c) {
				case ' ': {
					if (word.length() == 0 || (i > 0 && input.charAt(i - 1) == ' '))
						continue;

					tree.push(new Node("GENERIC", word));
					word = "";

					break;
				}

				case '|': {
					if (tree.empty() || tree.peek().val.equals("|"))
						throw new LocatedException("Must supply command", input, i);

					if (word.length() > 0)
						tree.push(new Node("GENERIC", word));

					tree.push(new Node("TOKEN", "|"));

					word = "";
					break;
				}

				case '>': {
					Boolean isAppend = false;

					if (input.charAt(i + 1) == '>') {
						isAppend = true;
						i += 1;
					}

					if (tree.empty() || !tree.peek().type.equals("GENERIC"))
						throw new LocatedException("Must supply command or other redirect", input, i);

					if (word.length() > 0)
						tree.push(new Node("GENERIC", word));

					word = "";

					Node node = new Node("TOKEN", isAppend ? ">>" : ">");
					tree.push(node);

					break;
				}

				default: {
					word += c;

					if (i == input.length() - 1)
						tree.push(new Node("GENERIC", word));

				}
			}
		}

		return tree;
	}
}

/**
 * Test cases command p1 p2 p3 ... [DONE] command p1 p2 p3 | command p1 p2 p3
 * [DONE] command p1 p2 p3 > path path path path [DONE] command p1 p2 p3 >> path
 * path path path
 */