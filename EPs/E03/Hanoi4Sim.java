/*
 * $ java-introcs Hanoi4 2 | java-introcs Hanoi4Sim 2 .
 * Initial configuration:
 * 0: 2 1 
 * 1: 
 * 2: 
 * 3: 
 * 1 2
 * 0: 2 
 * 1: 
 * 2: 1 
 * 3: 
 * 2 1
 * 0: 
 * 1: 2 
 * 2: 1 
 * 3: 
 * 1 1
 * 0: 
 * 1: 2 1 
 * 2: 
 * 3: 
 * Final configuration:
 * 0: 
 * 1: 2 1 
 * 2: 
 * 3: 
 * Worked!
 * Number of moves: 3
 * $ java-introcs Hanoi4 21 | java-introcs Hanoi4Sim 21
 * Worked!
 * Number of moves: 321
 * Number of moves should be at most 321
 * $ 
 */

public class Hanoi4Sim
{
    public static Stack<Integer>[] initialize(int N) {
	Stack<Integer>[] s = (Stack<Integer>[]) new Stack[4];
	for (int i = 0; i < 4; i++) s[i] = new Stack<>();
	for (int i = N; i > 0; i--) s[0].push(i);
	return s; 
    }

    public static void print(Stack<Integer>[] s) {
	for (int i = 0; i < 4; i++) {
	    StdOut.print(i + ": ");
	    Stack<Integer> t = new Stack<>();
	    for (Integer d : s[i]) t.push(d);
	    for (Integer d : t) StdOut.print(d + " ");
	    StdOut.println();
	}
    }

    public static int simulate(int N, boolean verbose) {
	int[] peg = new int[N + 1];  // peg[d] = where disk d is; initially all 0
	Stack<Integer>[] s = initialize(N);
	int M = 0;
	if (verbose) { 
	    StdOut.println("Initial configuration:");
	    print(s);
	} 
	while (!StdIn.isEmpty()) {
	    int d = StdIn.readInt();
	    int p = StdIn.readInt();
	    if (verbose)
		StdOut.println("Move: " + d + " " + p);
	    if (d != s[peg[d]].peek()) {
		StdOut.println("Illegal: " + d + " not on top");
		print(s);
		return M;
	    }
	    if (!s[p].isEmpty() && d > s[p].peek()) {
		StdOut.println("Illegal: " + d + " larger than disk on top of peg " + p);
		print(s);
		return M;
	    }
	    s[peg[d]].pop();
	    s[p].push(d);
	    peg[d] = p;
	    if (verbose) print(s);
	    M++;
	}
	if (verbose) {
	    StdOut.println("Final configuration:");
	    print(s);
	}
	if (s[1].size() == N) StdOut.println("Worked!");
	else StdOut.println("Didn't work!");
	return M;
    }

    // n such that {n\choose2} < N \leq {n+1\choose2}
    // for N \geq 1
    public static int n(int N) {
	int n = 0, b = 0;
	while (b < N) { b += n; n++; }
	return n - 1;
    }

    public static boolean special(int N) {
	int n = n(N);
	return N == (n+1)*n/2;
    }

    public static int f(int n) {
	return (n - 1)*(1 << n) + 1;
    }
    
    public static void main(String[] args)
    {
	int N = Integer.parseInt(args[0]);
	boolean verbose = args.length > 1;
	int M = simulate(N, verbose);
	StdOut.println("Number of moves: " + M);
	if (special(N))
	    StdOut.println("Should be at most " + f(n(N)));
    }
}
