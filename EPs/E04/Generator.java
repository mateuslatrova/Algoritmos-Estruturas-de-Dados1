/*
 * $ java-algs4 Generator 1 60 actg 888
 * tctagcttgcggcttccctacactgacgccatagcgatgcaggagtcttaaaaggctagc
 * $ java-algs4 Generator 10 3 abc 888
 * abb
 * bcb
 * cbb
 * aac
 * aac
 * bca
 * cac
 * caa
 * acc
 * acc
 * $ java-algs4 Generator 15 8 0123456789 888
 * 99918848
 * 35612018
 * 42940869
 * [...]
 */

public class Generator {

    public static String randomString(int L, String alpha) {
	char[] a = new char[L];
	for (int i = 0; i < L; i++)  { 
	    int t = StdRandom.uniform(alpha.length());
	    a[i] = alpha.charAt(t);
	}
	return new String(a);
    }
    
    public static void main(String[] args) {
	int N = Integer.parseInt(args[0]);
	int L = Integer.parseInt(args[1]);
	String alpha = args[2];
	long seed = Long.parseLong(args[3]);
	StdRandom.setSeed(seed);
	for (int i = 0; i < N; i++)
	    StdOut.println(randomString(L, alpha));
    }

}
