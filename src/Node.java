import java.util.ArrayList;

public class Node {
	String type = "";
	ArrayList<String> params = new ArrayList<>();

	Node(String type, ArrayList<String> params) {

		System.out.println(params);
		this.params = params;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Type is " + this.type + "\nParams are " + this.params;
	}

}
