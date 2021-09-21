/*
 * $ java-introcs Generator 1 60 abcd 121 | java-introcs CosmicRays .3 > strings
 * $ java-introcs Join < strings > join
 * $ cat strings join 
 * cdbaabcdbacdadcdbdaacbccdcaabccdadcadbdabcda
 * ddaacbabdbddacadcdbdaaacbccdcabdddcdaccda
 * cdbdaabcbabdbddacdadcdbdaaacbccdcaabccdadcadbcdabccda
 * $ cat strings join | java-introcs CheckJoin
 * Proposed join has 53 chars
 * Length should be 53
 * Passes length test?  Yes
 * Subsequence (1st string)?  Yes
 * Subsequence (2nd string)?  Yes
 * $ 
 */ 

public class CheckJoin
{
    public static boolean sstring(String ss, String tt) {
	char[] t = tt.toCharArray();
	char[] s = ss.toCharArray();

	int j = 0;
	for (int i = 0; i < s.length; i++) {
	    while (j < t.length && s[i] != t[j]) j++;
	    if (j == t.length)
		return false; 
	    j++;
	}
	return true;
    }

    public static void main(String[] args)
    {
	String sx = StdIn.readString();
	String sy = StdIn.readString();
	String join = StdIn.readString();

	StdOut.println("Proposed join has " + join.length() + " chars");
	int l = LongestCommonSubsequence.lcs(sx, sy).length();
	int minlen = sx.length() + sy.length() - l;
	StdOut.println("Length should be " + minlen);
	StdOut.println("Passes length test?  " + (join.length() <= minlen ? "Yes" : "No"));
	StdOut.println("Subsequence (1st string)?  " + (sstring(sx, join) ? "Yes" : "No"));
	StdOut.println("Subsequence (2nd string)?  " + (sstring(sy, join) ? "Yes" : "No"));
    }
}
