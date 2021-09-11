/******************************************************************************
 * From S&W (CS.06.Recursion.pdf)
 * 
 * $ java-introcs Hanoi 0
 *  
 * $ java-introcs Hanoi 1
 *  1R 
 * $ java-introcs Hanoi 3
 *  1R 2L 1R 3R 1R 2L 1R 
 * $ java-introcs Hanoi 4
 *  1L 2R 1L 3L 1L 2R 1L 4R 1L 2R 1L 3L 1L 2R 1L 
 * $ 
 ******************************************************************************/

public class Hanoi {

    public static String moves(int n, boolean left) {
        if (n == 0) return " ";
	String move;
	if (left) move = n + "L";
	else      move = n + "R";
	return moves(n - 1, !left) + move + moves(n - 1, !left);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdOut.println(moves(N, false));
    }

}

