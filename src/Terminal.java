
/**
 * Terminal commands go here
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Terminal {

	public static void cp(ArrayList<String> params) throws IOException {
		String sourcePath = params.get(0);
		String destinationPath = params.get(1);
		File sourceFile = new File(sourcePath);
		File destFile = new File(destinationPath);

		copy(sourceFile, destFile);
	}

	public static void mv(ArrayList<String> params) throws IOException {
		String sourcePath = params.get(0);
		String destinationPath = params.get(1);
		
		File sourceFile = new File(sourcePath);
		File destFile = new File(destinationPath);

		move(sourceFile, destFile);
	}

	// TEST COMPLETED
	private static void copy(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			input.close();
			output.close();
		}
	}

	// TEST COMPLETED
	private static void move(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			input.close();
			output.close();
			source.delete();
		}
	}


	public static void main(String[] args) throws Exception {
		try
		{
			System.out.println("Hello, World!");
	
			ArrayList<String> test = new ArrayList<String>();
			test.add("F:\\College\\Operating Systems\\haha.txt");
			test.add("test.txt");
			mv(test);

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}