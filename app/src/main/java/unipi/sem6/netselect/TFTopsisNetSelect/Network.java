package unipi.sem6.netselect.TFTopsisNetSelect;

public class Network {
	public String name;
	public InterValFN[] criteria;

	public Network(String name, InterValFN[] criteria) {
		this.name = name;
		this.criteria = criteria;
	}

	public Network(InterValFN[] criteria) {
		this(null, criteria);
	}

	public String printInfo() {
		String tmp =  "Net " + this.name + " { ";
		for (InterValFN c : criteria) {
			tmp += c.toString() + " ";
		}
		return tmp + "}";
	}

	public String toString() {
		return this.name != null ? this.name : "";
	}

}
