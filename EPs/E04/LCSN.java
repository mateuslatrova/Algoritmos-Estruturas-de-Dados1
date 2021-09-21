/******************************************************************************
 *  Compilation:  javac LCSN
 *  Execution:    java LCSN < DATA/LCS.txt
 *  Dependencies: StdIn.java
 *  
 *  Reads in two strings from stdin and computes the length of a longest
 *  common subsequence.
 *
 ******************************************************************************/

public class LCSN {

    public static void main(String[] args) {
        String x = StdIn.readString();
        String y = StdIn.readString();
        int M = x.length();
        int N = y.length();

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[M+1][N+1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int i = M-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (x.charAt(i) == y.charAt(j))
                    opt[i][j] = opt[i+1][j+1] + 1;
                else 
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
            }
        }

	System.out.println(opt[0][0]);

    }

}
