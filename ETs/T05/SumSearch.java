/* 
 * $ java-algs4 SumSearch 20 100 121
 * count:       0
 * count plain: 0
 * $ java-algs4 SumSearch 30 100 121
 * count:       1
 * count plain: 1
 * $ java-algs4 SumSearch 1000 10000 121
 * count:       3
 * count plain: 3
 * $ java-algs4 SumSearch 10000 100000 121
 * count:       160
 * count plain: 160
 * $ 
 */     

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class SumSearch
{
    // number of 0 <= i < j < a.length with a[i] + a[j] = x
    // a is supposed to be strictly increasing
    public static int count(int[] a, int x) {
		int N = a.length;
		int lo = 0, hi = N - 1;
		int t = 0;
		while (lo < hi) {
		    if (a[hi] + a[lo] == x) {
				t++;
				hi--; lo++;
				continue;
		    }
		    if (a[hi] + a[lo] < x) lo++;
		    else hi--;
		}
		return t;
    }

    // elementary algorithm
    public static int countPlain(int[] a, int x) {
		int N = a.length;
		int t = 0;
		for (int i = 0; i < N; i++) 
		    for (int j = i + 1; j < N; j++) 
				if (a[i] + a[j] == x) t++;
		return t;
    }

    public static void main(String[] args)
    {
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);
		long seed = Long.parseLong(args[2]);
		StdRandom.setSeed(seed);

		int[] a = RandomInts.randomSeq(N, M); // a strictly increasing
		int x = StdRandom.uniform(-2*M, 2*M + 1);
		StdOut.println("count:       " + count(a, x));
		StdOut.println("count plain: " + countPlain(a, x));
    }
}
