import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {

	public static String args(ArrayList<String> args) throws Exception {
		if (args.size() < 1)
			throw new Exception("Must provide 1 parameter");

		String cmd = args.get(0);
		String[] cmds = { "cat", "cd", "cp", "ls", "mkdir", "pwd", "rmdir" };

		if (!Utils.arrayContains(cmds, cmd))
			throw new Exception("Command not found");

		File file = new File("./args/" + cmd + ".txt");

		Scanner sc = new Scanner(file);

		String output = "";

		while (sc.hasNextLine())
			output += sc.nextLine();

		sc.close();

		System.out.println(output);

		return output;
	}

	public static String help(ArrayList<String> args) throws FileNotFoundException {
		File file = new File("help.txt");

		Scanner sc = new Scanner(file);

		String output = "";

		while (sc.hasNextLine())
			output += sc.nextLine();

		sc.close();

		System.out.println(output);
		return output;
	}

	public static String clear(ArrayList<String> args) {
		System.out.println("\r\n".repeat(1000));

		return "";
	}

}
