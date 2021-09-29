/*
 * $ java-introcs RandomInts 10 1000 121 | java-introcs DivideBrute .
 * 0: 34 27 167 169 922 (sum: 1319)
 * 1: 360 721 240 (sum: 1321)
 * 2: 880 458 (sum: 1338)
 * 1319 (sum: 3978 / ideal: 1326 / mod 3 = 0)
 * $ java-introcs RandomInts 15 100 121 | java-introcs DivideBrute .
 * 0: 34 27 67 22 21 18 (sum: 189)
 * 1: 69 60 40 19 (sum: 188)
 * 2: 80 58 24 12 14 (sum: 188)
 * 188 (sum: 565 / ideal: 188 / mod 3 = 1)
 * $ 
 */

public class DivideBrute
{
    private static boolean verbose;
    private static int best = 0;
    private static int[] b;
    
    public static void enumerate(int[] val, int[] a, int k) {
	if (k == a.length) {
	    int t = min(val, a);
	    if (t > best) {
		best = t;
		copy(b, a);
	    }
	    return;
	} 
	a[k] = 0;
	enumerate(val, a, k + 1);
	a[k] = 1;
	enumerate(val, a, k + 1);
	a[k] = 2;
	enumerate(val, a, k + 1);
    }

    public static int scheduleBrute(int[] val) {
	int[] a = new int[val.length];
	b = new int[val.length];
	enumerate(val, a, 0);
	return best;
    }

    public static int min(int[] val, int[] a) {
	int N = a.length;
	int A = 0, B = 0, C = 0;
	for (int i = 0; i < N; i++)
	    switch (a[i]) {
	    case 0: A += val[i]; break;
	    case 1: B += val[i]; break;
	    case 2: C += val[i]; break;
	    } 
	return Math.min(Math.min(A, B), C);
    }

    public static int sum(int[] val) {
	int N = val.length, S = 0; 
	for (int i = 0; i < N; i++) S += val[i];
	return S;
    }

    public static void show(int[] a, int[] val) {
	for (int i = 0; i < 3; i++) 
	    show(a, val, i);
    }

    public static void show(int[] a, int[] val, int i) {    
	int N = a.length, s;
	StdOut.print( i + ": ");
	s = 0;
	for (int j = 0; j < N; j++) {
	    if (a[j] == i) {
		s += val[j];
		StdOut.print(val[j] + " ");
	    } 
	}
	StdOut.println("(sum: " + s + ")");
    }

    public static void copy(int[] a, int[] b) {
	for (int i = 0; i < a.length; i++) a[i] = b[i];
    }     
    
    public static void main(String[] args)
    {
	verbose = args.length > 0;
	int[] val = StdIn.readAllInts();
	int opt = scheduleBrute(val);
	int sum = sum(val);	
	if (opt >= sum / 3)
	    StdOut.println("There is a solution");
	else 
	    StdOut.println("There is no solution");
	if (verbose) { 
	    StdOut.println("Optimal value: " + opt
			   + " (sum: " + sum + " / want: " + sum/3
			   + " / sum mod 3 = " + sum % 3 +")");
	    if (verbose) show(b, val);
	} 
    }
}
