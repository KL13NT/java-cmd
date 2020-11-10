public class Utils {
	// All methods here should be public static. Return types are up to you.
	public static boolean isAbsolutePath(String Path){
		/*
		/path = absolute to home or partition
		./path = relative
		path = relative
		D:/ = absolute to specified partition
		*/
		if(Path.charAt(1)==':'||Path.charAt(0)=='/')return true;
		else return false;
	}
}
