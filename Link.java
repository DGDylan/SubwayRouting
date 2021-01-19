
public class Link {
	Station from;
	Station to;
	String line;
	
	public Link(Station from, Station to, String line) {
		this.from = from;
		this.to = to;
		this.line = line;
	}

	public Station getTo() {
		return this.to;
	}
	
	public String toString() {
		return line + "{" + from.name + " to " + to.name + "}";
	}
	
}
