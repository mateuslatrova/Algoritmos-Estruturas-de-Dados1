/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E04
 * Data: <24/08/2021>
 * 
 * Baseado em:
 *     
 *     - Programa LCSN.java, apresentado na aula de 14/09/2021.
 *     - Um trabalho que fiz há 2 anos: https://github.com/mateuslatrova/LongestCommonSubsequence/blob/master/LCSmain.c
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;

// Obs.: no meu computador, só consegui rodar os casos até 20k. A partir de 40k, não há
// memória o suficiente. Tentei arranjar um jeito de consertar mas não consegui =/

public class Join {

    private static int[][] lcs_matrix(char[] s, char[] t) {
        int M = s.length;
        int N = t.length;

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[M+1][N+1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int i = M-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (s[i] == t[j]) {
                    opt[i][j] = opt[i+1][j+1] + 1;
                } else 
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
            }
        }

        return opt;
    }

    // Computes the indexes of s and t where their LCS characters are present.
    private static int[][] lcsIndexes(char[] s, char[] t, int[][] opt) {
        int M = s.length;
        int N = t.length;
        int lcsLength = opt[0][0];

        String lcs = "";
        int[][] lcsIndexes = new int[2][lcsLength];
        int i = 0, j = 0, aux = 0;
        while (i < M && j < N) {
            if (s[i] == t[j] && opt[i+1][j] == opt[i][j+1]) {
                lcsIndexes[0][aux] = i;
                lcsIndexes[1][aux] = j;
                i++;
                j++;
                aux++;
            } else if (opt[i+1][j] > opt[i][j+1]) i++; 
            else j++;
        }

        return lcsIndexes;
    }

    private static String cheapJoin(char[] s, char[] t, int[][] lcsIndexes) {
        int lcsLength = lcsIndexes[0].length;
        int sLen = s.length;
        int tLen = t.length;
        String cheapJoin = "";

        int leftLimitS = 0;
        int leftLimitT = 0;

        for (int k = 0; k < lcsLength; k++) { // k = index for the lcsIndexes two arrays.
            leftLimitS = lcsIndexes[0][k];
            leftLimitT = lcsIndexes[1][k];

            if (k == 0) {
                for (int i = 0; i < leftLimitS; i++) cheapJoin += s[i];
                for (int j = 0; j < leftLimitT; j++) cheapJoin += t[j];
                cheapJoin += s[leftLimitS]; // appending char from LCS only once! 
            } else {
                for (int i = lcsIndexes[0][k-1]+1; i < leftLimitS; i++) cheapJoin += s[i];
                for (int j = lcsIndexes[1][k-1]+1; j < leftLimitT; j++) cheapJoin += t[j];
                cheapJoin += s[leftLimitS]; // appending char from LCS only once! 
            }
        }

        for (int i = leftLimitS+1; i < sLen; i++) cheapJoin += s[i];
        for (int j = leftLimitT+1; j < tLen; j++) cheapJoin += t[j];

        return cheapJoin;
    }

    public static void main(String[] args) {

        char[] s = StdIn.readLine().toCharArray();
        char[] t = StdIn.readLine().toCharArray();

        int[][] lcsMatrix = lcs_matrix(s, t);
        int lcsLength = lcsMatrix[0][0];
        int[][] lcsIndexes = lcsIndexes(s,t,lcsMatrix);

        String cheapJoin = cheapJoin(s, t, lcsIndexes);

        StdOut.println(cheapJoin);
    }
}