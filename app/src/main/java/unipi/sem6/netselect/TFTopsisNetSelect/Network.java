package unipi.sem6.netselect.TFTopsisNetSelect;

class Network {
	public String name;
	public InterValFN[] criteria;

	Network(String name, InterValFN[] criteria) {
		this.name = name;
		this.criteria = criteria;
	}

	Network(InterValFN[] criteria) {
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
