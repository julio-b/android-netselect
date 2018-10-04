package unipi.sem6.netselect.TFTopsisNetSelect;

public class Network {
	public String name;
	public InterValFN[] criteria;
	public Double result;

	public Network(String name, InterValFN[] criteria) {
		this.name = name;
		this.criteria = criteria;
		this.result = null;
	}

	public Network(InterValFN[] criteria) {
		this(null, criteria);
	}

	public Network(Network n) {
		InterValFN[] clonedCriteria = new InterValFN[n.criteria.length];
		for (int i = 0; i < n.criteria.length; i++) {
			clonedCriteria[i] = new InterValFN(n.criteria[i]);
		}
		this.criteria = clonedCriteria;
		if (n.name != null)
			this.name = new String(n.name);
		if (n.result != null)
			this.result = new Double(n.result);
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
