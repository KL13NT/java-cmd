import java.util.ArrayList;
import java.util.Stack;

public class Parser {
	public static Stack<Node> parse(String input) throws Exception {
		Stack<Node> tree = new Stack<>();

		String word = "";
		ArrayList<String> current = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c == ' ') {
				if (word.length() == 0)
					continue;

				current.add(word);
				word = "";

			}

			else if (c == '|') {
				if (word.length() > 0 || current.size() > 0) {
					if (word.length() > 0)
						current.add(word);

					tree.push(new Node("command", current));
				}

				current.clear();
				word = "";
			}

			// else if (c == '>') {
			// 	if (input.charAt(i - 1) == '>')
			// 		continue; // skips second >

			// 	current.add(input.substring(i, input.length() - 1));
			// 	tree.push(new Node("redirect", current));

			// 	current.clear();
			// 	word = "";

			// 	break;
			// }

			else
				word += c;
		}

		current.add(word);
		tree.push(new Node("command", current));

		return tree;
	}
}


/**
 * Test cases
 * command p1 p2 p3 ... [DONE]
 * command p1 p2 p3 | command p1 p2 p3 [DONE]
 * command p1 p2 p3 > path path path path
 * command p1 p2 p3 >> path path path path
 */