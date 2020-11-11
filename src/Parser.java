import java.util.Stack;

public class Parser {
	public static Stack<Node> parse(String input) throws Exception {
		Stack<Node> tree = new Stack<>();
		String word = "";

		if((input.charAt(0) == '|' || input.charAt(0) == '>')) throw new Error("You must supply a command first");

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
						throw new Error("Must supply command at position " + i + ":" + input.charAt(i));

					if (word.length() > 0) tree.push(new Node("GENERIC", word));


					tree.push(new Node("TOKEN", "|"));

					word = "";
					break;
				}

				/**
				 * cmd p1 > output.txt
				 * cmd p1 > output.txt > oiajsdsa
				 * cmd p1 >> output.txt
				 * cmd p1 >>> output.txt
				 * cmd p1 >> > output.txt
				 * cat .gitignore | cat > text.txt | echo
				*/

				case '>': {
					if (tree.peek().val.equals("|"))
						throw new Error("Must supply command or other redirect at position " + i + ":" + input.charAt(i));

					if (word.length() > 0)
						tree.push(new Node("GENERIC", word));

					if (input.charAt(i - 1) == '>')
						continue; // skips second >

					word = "";
					tree.push(new Node("TOKEN", ">"));

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
 * Test cases
 * command p1 p2 p3 ... [DONE]
 * command p1 p2 p3 | command p1 p2 p3 [DONE]
 * command p1 p2 p3 > path path path path [DONE]
 * command p1 p2 p3 >> path path path path
 */