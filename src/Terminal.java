
/**
 * Terminal commands go here
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public void mv(ArrayList<String> params) throws IOException {
		String sourcePath = params.get(0);
		String destinationPath = params.get(1);
		
		File sourceFile = new File(sourcePath);
		File destFile = new File(destinationPath);

		move(sourceFile, destFile);
	}

	private static void copy(File source, File Dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(Dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			input.close();
			output.close();
		}
	}

	private static void move(File source, File Dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(Dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			input.close();
			output.close();
			source.delete();
		}
	}


	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
	}
}