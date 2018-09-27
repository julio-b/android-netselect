class TrapezoidalFN {
	public double[] x;
	public double v;

	public TrapezoidalFN(double[] X, double v) {
		this(X[0], X[1], X[2], X[3], v);
	}

	public TrapezoidalFN(double[] X) {
		this(X[0], X[1], X[2], X[3], X[4]);
	}

	public TrapezoidalFN(double x0, double x1, double x2, double x3, double v) {
		if (v < 0 || v > 1)
			//throw new IllegalArgumentException("error: v must be in range [0, 1]");
			System.err.println("error: v must be in range [0, 1] but is " + v);
		if (0 > x0 || x0 > x1 || x1 > x2 || x2 > x3 || x3 > 1)
			throw new IllegalArgumentException("error: should be 0 <= x0 <= x1 <= x2 <= x3 <= 1");
		this.x = new double[] {x0, x1, x2, x3};
		this.v = v;
	}

	public TrapezoidalFN mul(double x) {
		return this.mul(x, x);
	}

	public TrapezoidalFN mul(double x, double v) {
		return new TrapezoidalFN(this.x[0] * x,
		                         this.x[1] * x,
		                         this.x[2] * x,
		                         this.x[3] * x,
		                         this.v    * v);
	}

	public TrapezoidalFN mul(TrapezoidalFN m) {
		return new TrapezoidalFN(this.x[0] * m.x[0],
		                         this.x[1] * m.x[1],
		                         this.x[2] * m.x[2],
		                         this.x[3] * m.x[3],
		                         this.v    * m.v);
	}

	public double dist(TrapezoidalFN ideal) {
		return Math.sqrt( ( Math.pow(this.x[0] - ideal.x[0], 2)
		                  + Math.pow(this.x[1] - ideal.x[1], 2)
		                  + Math.pow(this.x[2] - ideal.x[2], 2)
		                  + Math.pow(this.x[3] - ideal.x[3], 2)
		                  + Math.pow(this.v    - ideal.v,    2)
		                  ) / 5
		                );
	}

	public double dist(double[] ideal) {
		return Math.sqrt( ( Math.pow(this.x[0] - ideal[0], 2)
		                  + Math.pow(this.x[1] - ideal[1], 2)
		                  + Math.pow(this.x[2] - ideal[2], 2)
		                  + Math.pow(this.x[3] - ideal[3], 2)
		                  + Math.pow(this.v    - ideal[4], 2)
		                  ) / 5
		                );
	}

	public double getMax() { return this.x[3]; }

	public double getMin() { return this.x[0]; }

	public String toString() {
		return   "("  + this.x[0]
		       + ", " + this.x[1]
		       + ", " + this.x[2]
		       + ", " + this.x[3]
		       + ", " + this.v
		       + ")";
	}

}
