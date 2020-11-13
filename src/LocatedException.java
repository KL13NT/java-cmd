public class LocatedException extends Exception {
	private static final long serialVersionUID = 9137092008036181916L;

	private static String generateErrorString(String message, String input, Integer index) {
		String error = "Parser Exception at index " + index + "\n";
		error += message + "\n\n";
		error += input + "\n";
		error += " ".repeat(index - 1) + "^";

		return error;
	}

	/**
	 * Generates Parser exception
	 *
	 * @param message
	 * @param input
	 * @param index   must be at least 1
	 */
	LocatedException(String message, String input, Integer index) {
		super(generateErrorString(message, input, index));
	}
}
