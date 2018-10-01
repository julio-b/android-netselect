package unipi.sem6.netselect.TFTopsisNetSelect;

class InterValFN{
	public TrapezoidalFN A_L;
	public TrapezoidalFN A_U;

	public InterValFN(double[] A_L, double[] A_U) {
		this(new TrapezoidalFN(A_L), new TrapezoidalFN(A_U));
	}

	public InterValFN(double xL0, double xL1, double xL2, double xL3, double vL,
	                  double xU0, double xU1, double xU2, double xU3, double vU) {
		this(new double[]{xL0, xL1, xL2, xL3, vL}, new double[]{xU0, xU1, xU2, xU3, vU});
	}

	public InterValFN(TrapezoidalFN A_L, TrapezoidalFN A_U) {
		if (A_L.v > A_U.v)
			throw new IllegalArgumentException("error: vL > vU");
		this.A_L = A_L;
		this.A_U = A_U;
	}

	public InterValFN mul(double m) {
		return new InterValFN(this.A_L.mul(m), this.A_U.mul(m));
	}

	public InterValFN mul(double l, double u) {
		return new InterValFN(this.A_L.mul(l), this.A_U.mul(u));
	}

	public InterValFN mul(double l, double lv, double u, double uv) {
		return new InterValFN(this.A_L.mul(l, lv), this.A_U.mul(u, uv));
	}

	public InterValFN mul(TrapezoidalFN l, TrapezoidalFN u) {
		return new InterValFN(this.A_L.mul(l), this.A_U.mul(u));
	}

	public String toString() {
		return "[" + this.A_L + ", " + this.A_U + "]";
	}
}
