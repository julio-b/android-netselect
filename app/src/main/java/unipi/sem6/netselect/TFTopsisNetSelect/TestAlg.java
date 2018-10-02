package unipi.sem6.netselect.TFTopsisNetSelect;

public class TestAlg {
	private static double[] A(double ... args) {return args;} //eye-candy new double[]{x,y,z..}

	//Absolutely Poor (AP)
	//Very Poor (VP)
	//Poor (P)
	//Medium Poor (MP)
	//Medium (M)
	//Medium Good (MG)
	//Good (G)
	//Very Good (VG)
	//Absolutely Good (AG)

	public static InterValFN AP = new InterValFN(A(0,    0,    0,    0,    0.8), A(0,    0,    0,    0,    1));
	public static InterValFN VP = new InterValFN(A(0.01, 0.02, 0.03, 0.07, 0.8), A(0,    0.01, 0.05, 0.08, 1));
	public static InterValFN P  = new InterValFN(A(0.04, 0.1,  0.18, 0.23, 0.8), A(0.02, 0.08, 0.2,  0.25, 1));
	public static InterValFN MP = new InterValFN(A(0.17, 0.22, 0.36, 0.42, 0.8), A(0.14, 0.18, 0.38, 0.45, 1));
	public static InterValFN M  = new InterValFN(A(0.32, 0.41, 0.58, 0.65, 0.8), A(0.28, 0.38, 0.6,  0.7,  1));
	public static InterValFN MG = new InterValFN(A(0.58, 0.63, 0.8,  0.86, 0.8), A(0.5,  0.6,  0.9,  0.92, 1));
	public static InterValFN G  = new InterValFN(A(0.72, 0.78, 0.92, 0.97, 0.8), A(0.7,  0.75, 0.95, 0.98, 1));
	public static InterValFN VG = new InterValFN(A(0.93, 0.98, 1,    1,    0.8), A(0.9,  0.95, 1,    1,    1));
	public static InterValFN AG = new InterValFN(A(1,    1,    1,    1,    0.8), A(1,    1,    1,    1,    1));

	public static double[] Weights = new double[] {0.0078125,   //C1 - Bandwidth
	                                               0.164062,    //C2 - Delay
	                                               0.164062,    //C3 - Jitter
	                                               0.164062,    //C4 - Packet loss
	                                               0.0246573,   //C5 - Price
	                                               0.470224,    //C6 - Service flexibility
	                                               0.00511916}; //C7 - Security

	public static void main(String[] args) {
		TFTopsisNetSelect test = new TFTopsisNetSelect(Weights);
		test.D.add(new Network("0", new InterValFN[] {MG, AG, VG, VG, VP, AG, VG}));
		test.D.add(new Network("1", new InterValFN[] {AG, MG, AG, MG, VP, VG, AG}));
		test.D.add(new Network("2", new InterValFN[] {M,  M,  MP, AG, P,  VG, AG}));
		test.D.add(new Network("3", new InterValFN[] {G,  G,  G,  G,  P,  AG, VG}));
		test.D.add(new Network("4", new InterValFN[] {VG, VG, MG, AG, MP, G,  G }));
		test.D.add(new Network("5", new InterValFN[] {MG, M,  MG, VG, MP, MG, MG}));
		test.D.add(new Network("6", new InterValFN[] {M,  MP, M,  AG, MP, G,  G }));

		double[] results = test.getResults();
		System.out.println("Results");
		for (int i = 0; i < test.D.size(); i++) {
			System.out.printf("%s - %f%n", test.D.get(i), results[i]);
		}

	}

}
