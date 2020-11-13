public class Node {
	public String type = ""; // TOKEN | GENERIC
	public String val = "";

	Node(String type, String val)  {
		this.val = val;
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type + "+" + this.val;
	}
}
