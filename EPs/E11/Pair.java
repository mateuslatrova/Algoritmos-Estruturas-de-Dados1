/* 
 * Illustrates the use of index sorting (indirect sorting)
 * 
 * $ java-algs4 Pair 6 10000 121 .
 *  -7594  -7594
 *   8783   8300
 *  -2264  -4370
 *   9133   9133
 *  -4370  -2264
 *   8300   8783
 * Time to produce pairing: 0.001
 *  -7594  -7594
 *   8300   8300
 *  -4370  -4370
 *   9133   9133
 *  -2264  -2264
 *   8783   8783
 * Pairing correct?  Yes
 * $ java-algs4 Pair 1000000 1000000000 121
 * Time to produce pairing: 0.539
 * Pairing correct?  Yes
 * $ java-algs4 Pair 10000000 1000000000 121
 * Time to produce pairing: 6.988
 * Pairing correct?  Yes
 * $ java-algs4 Pair 20000000 1000000000 121
 * Time to produce pairing: 15.058
 * Pairing correct?  Yes
 * $ 
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Pair {
    
    public static boolean check(int[] a, int[] b, int[] pairing) {
	int N = a.length;
	for (int i = 0; i < N; i++) 
	    if (a[pairing[i]] != b[i])
		return false;
	return true;
    }

    public static int[] pair(int[] a, int[] b) {
		int N = a.length;
		Integer[] A = new Integer[N];
		Integer[] B = new Integer[N];
		for (int i = 0; i < N; i++) {
		    A[i] = a[i]; B[i] = b[i];
		}
		int[] p = Merge.indexSort(A);
		int[] q = Merge.indexSort(B);
		int[] qq = inverse(q);
		int[] pairing = new int[N];
		for (int i = 0; i < N; i++) 
		    pairing[i] = p[qq[i]];
		return pairing;
    }

    public static int[] inverse(int[] q) {
		int N = q.length;
		int[] qq = new int[N];
		for (int i = 0; i < N; i++) 
		    qq[q[i]] = i;
		return qq;
    }

    public static void main(String[] args)
    {
	int N = Integer.parseInt(args[0]);
	int M = Integer.parseInt(args[1]);
	long seed = Long.parseLong(args[2]);
	boolean verbose = args.length > 3;
	
	StdRandom.setSeed(seed);
	
	int[] a = RandomIntsPlain.randomSeq(N, M);
	int[] b = a.clone();
	StdRandom.shuffle(b);

	if (verbose) 
	    for (int i = 0; i < N; i++) 
		StdOut.printf("%6d %6d\n", a[i], b[i]);

	Stopwatch sw = new Stopwatch();
	// want a[pairing[i]] = b[i] for all i
	int[] pairing = pair(a, b);
	StdOut.println("Time to produce pairing: " + sw.elapsedTime());
	
	if (verbose) 
	    for (int i = 0; i < N; i++) 
		StdOut.printf("%6d %6d\n", a[pairing[i]], b[i]);

	StdOut.println("Pairing correct?  " + (check(a, b, pairing) ? "Yes" : "No"));
    }
}
