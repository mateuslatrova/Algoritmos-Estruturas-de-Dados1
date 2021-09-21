/*
 * $ java-introcs Generator 1 60 abcd 121 | java-introcs CosmicRays .3
 * cbbaabcbbbdddcadcddadacbcdccabccdaaddadccdd
 * bdabbdbddddcdbddaacbcdabcdadcadcdacdbcda
 * $ 
 */ 

public class CosmicRays
{
    public static void main(String[] args)
    {
	double p = Double.parseDouble(args[0]);
	int N = 2;
	String t = StdIn.readString();

	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < t.length(); j++) {
		if (StdRandom.bernoulli(p)) continue;
		StdOut.print(t.charAt(j));
	    }
	    StdOut.println();	    
	}
    }
}
