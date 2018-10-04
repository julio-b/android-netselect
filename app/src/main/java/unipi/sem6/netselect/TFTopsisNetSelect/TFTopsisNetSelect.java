package unipi.sem6.netselect.TFTopsisNetSelect;

import java.util.*;
public class TFTopsisNetSelect {
	public ArrayList<Network> D;
	public ArrayList<Network> nD;
	public double[] Weights;
	public double[] maxDs;
	public double[] maxDsT;
	public double[] minAs;
	public double[] minAsT;
	public double[][] distances;
	public double[][] relativeCloseness;
	public double[] rcStar;

	public static final boolean DEBUG = false;

	public TFTopsisNetSelect(double [] Weights) {
		this.D = new ArrayList<Network>();
		this.Weights = Weights;
		this.nD = null;
		this.maxDs = null;
		this.maxDsT = null;
		this.minAs = null;
		this.minAsT = null;
		this.distances = null;
		this.relativeCloseness = null;
		this.rcStar = null;
	}

	private double[] get_extremes_for_criteria(int c) {
		int ncnt = this.D.size();
		Double[] Ds  = new Double[ncnt];
		Double[] DsT = new Double[ncnt];
		Double[] As  = new Double[ncnt];
		Double[] AsT = new Double[ncnt];

		for (int i = 0; i < ncnt; i++) {
			Ds[i]  = this.D.get(i).criteria[c].A_L.getMax();
			DsT[i] = this.D.get(i).criteria[c].A_U.getMax();
			As[i]  = this.D.get(i).criteria[c].A_L.getMin();
			AsT[i] = this.D.get(i).criteria[c].A_U.getMin();
		}

		return new double[] {Collections.max(Arrays.asList(Ds)),
		                     Collections.max(Arrays.asList(DsT)),
		                     Collections.min(Arrays.asList(As)),
		                     Collections.min(Arrays.asList(AsT))};
	}

	private void find_criterias_extrems() {
		int cl = this.D.get(0).criteria.length;
		maxDs  = new double[cl];
		maxDsT = new double[cl];
		minAs  = new double[cl];
		minAsT = new double[cl];

		for (int j = 0; j < cl; j++) {
			double[] tmp = this.get_extremes_for_criteria(j);
			maxDs[j]  = tmp[0];
			maxDsT[j] = tmp[1];
			minAs[j]  = tmp[2];
			minAsT[j] = tmp[3];
			if (DEBUG) {
				System.err.printf("maxcs %f : minAs %f - maxcsT %f : minAsT %f%n" ,
				                  maxDs[j], minAs[j], maxDsT[j], minAsT[j]);
			}
		}
	}

	//TODO negative criteria?
	private void normalization() {
		this.nD = new ArrayList<Network>();
		for (Network n : this.D) { //clone D
			this.nD.add(new Network(n));
		}
		for (Network n : this.nD) {
			for (int j = 0; j < n.criteria.length; j++) {
				//n.criteria[j] = n.criteria[j].mul(1/maxDsT[j], 1/maxDsT[j]);
				//n.criteria[j] = n.criteria[j].mul(1/maxDs[j], 1, 1/maxDsT[j], 1);
				//n.criteria[j] = n.criteria[j].mul(1/maxDs[j], 1/maxDs[j], 1/maxDsT[j], 1/maxDsT[j]);
				if (j == n.criteria.length / 2) {
					n.criteria[j] = n.criteria[j].mul(1 / maxDs[j], 1 / maxDsT[j]);
				} else if (j < n.criteria.length / 2) {
					n.criteria[j] = n.criteria[j].mul(1/maxDs[j]);
				} else {
					n.criteria[j] = n.criteria[j].mul(1/maxDsT[j]);
				}
				if (DEBUG) {
					System.err.printf("nd n %s c %d : %s%n", n, j, n.criteria[j]);
				}
			}
		}
	}

	private void weighted_normalization() {
		for (Network n : this.nD) {
			for (int j = 0; j < n.criteria.length; j++) {
				n.criteria[j] = n.criteria[j].mul(this.Weights[j]);
				if (DEBUG) {
					System.err.printf("wnd n %s c %d : %s%n", n, j, n.criteria[j]);
				}
			}
		}
	}

	private double[][] get_distances() {
		double[] ideal_pos = new double[] {1, 1, 1, 1, 1};
		double[] ideal_neg = new double[] {0, 0, 0, 0, 0};
		this.distances = new double[this.nD.size()][4];
		for (int i = 0; i < this.nD.size(); i++){
			Network n = this.nD.get(i);
			for (int j = 0; j < n.criteria.length; j++) {
				distances[i][0] += n.criteria[j].A_L.dist(ideal_pos);
				distances[i][1] += n.criteria[j].A_U.dist(ideal_pos);
				distances[i][2] += n.criteria[j].A_L.dist(ideal_neg);
				distances[i][3] += n.criteria[j].A_U.dist(ideal_neg);
			}
			if (DEBUG) {
				System.err.printf("d%s,%d: %f%n", n, 0, distances[i][0]);
				System.err.printf("d%s,%d: %f%n", n, 1, distances[i][1]);
				System.err.printf("d%s,%d: %f%n", n, 2, distances[i][2]);
				System.err.printf("d%s,%d: %f%n", n, 3, distances[i][3]);
			}
		}
		return this.distances;
	}

	private double[][] get_relative_closeness() {
		this.relativeCloseness = new double[this.nD.size()][2];
		for (int i = 0; i < this.nD.size(); i++) {
			this.relativeCloseness[i][0] = this.distances[i][2]
			                               / (this.distances[i][1] + this.distances[i][2]);
			this.relativeCloseness[i][1] = this.distances[i][3]
			                               / (this.distances[i][0] + this.distances[i][3]);
			if (DEBUG)
				System.err.printf("rc%s: %f , %f%n", this.D.get(i),
				                  relativeCloseness[i][0], relativeCloseness[i][1]);
		}
		return this.relativeCloseness;
	}

	private double[] get_rcstar() {
		this.rcStar = new double[this.nD.size()];
		for (int i = 0; i < this.nD.size(); i++) {
			this.rcStar[i] = (this.relativeCloseness[i][0] + this.relativeCloseness[i][1]) / 2;
			this.D.get(i).result = new Double(this.rcStar[i]);
			if (DEBUG)
				System.err.printf("rc*%s: %f%n", this.D.get(i), this.rcStar[i]);
		}
		return this.rcStar;
	}

	public double[] getResults() {
		this.find_criterias_extrems();
		this.normalization();
		this.weighted_normalization();
		this.get_distances();
		this.get_relative_closeness();
		return this.get_rcstar();
	}

}
