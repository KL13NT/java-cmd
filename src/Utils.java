public class Utils {
	// All methods here should be public static. Return types are up to you.

	public static Boolean arrayContains(String[] arr, String val) {
		for (String n : arr) {
			if (val.equalsIgnoreCase(n))
				return true;
		}

		return false;
	}
}
