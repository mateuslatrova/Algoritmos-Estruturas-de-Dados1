// This can be improved a great deal.  Don't compute makespan all the time:
// keep values A and B that are the sum of the times of the jobs currently
// given to each machine (A and B start with zero and vary; max{A, B}
// is the current (partial) makespan).  Can update A and B quickly as
// we proceed in the recursion tree.  Should become faster than
// ScheduleGray.java, as here we have pruning.
//
// Details left to the reader :-)

public class Schedule
{
    static double best = Double.POSITIVE_INFINITY;
    
    public static void enumerate(double[] len, int[] a, int k) {
	if (k >= a.length) {
	    double m = makespan(len, a);
	    if (m < best) best = m;
	}
	if (makespan(len, a, k) >= best) return;
		a[k] = 0;
		enumerate(len, a, k + 1);
		a[k] = 1;
		enumerate(len, a, k + 1);	
    }

    public static double schedule(double[] len) {
	int[] a = new int[len.length];
	enumerate(len, a, 0);	
	return best;
    }

    public static double makespan(double[] len, int[] a) {
	return makespan(len, a, a.length);
    }
    
    public static double makespan(double[] len, int[] a, int k) {
	double A = 0.0, B = 0.0;
	for (int i = 0; i < k; i++) 
	    if (a[i] == 1) A += len[i];
	    else B += len[i];
	return Math.max(A, B);
    }
    
    public static void main(String[] args)
    {
	double[] len = StdIn.readAllDoubles();
	double opt = schedule(len);
	StdOut.println(opt);
    }
}
