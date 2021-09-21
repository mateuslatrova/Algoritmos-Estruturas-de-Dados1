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

public class Join {

    private static String LCS(String s, String t) {
        int M = s.length();
        int N = t.length();

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[M+1][N+1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int i = M-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    opt[i][j] = opt[i+1][j+1] + 1;
                } else 
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
            }
        }

        //for(int i = 0; i < M+1; i++) {
        //    for(int j = 0; j < N+1; j++) {
        //        if(i == 0 || j == 0)
        //            opt[i][j] = 0;
        //        else if(s.charAt(i-1) == t.charAt(j-1))
        //            opt[i][j] = opt[i-1][j-1] + 1;
        //        else {
        //            if(opt[i - 1][j] > opt[i][j - 1]) opt[i][j] = opt[i - 1][j];
        //            else opt[i][j] = opt[i][j - 1];
        //        }
        //    }
        //}

        for (int i = 0; i < M+1; i++) {
            for (int j = 0; j < N+1; j++) {
                StdOut.print(opt[i][j]+" ");
            }
            StdOut.println();
        }

        //StringBuilder reversedLCS = new StringBuilder();
        //int i = 0, j = 0, aux = opt[M][N];
        //while (i > 0 && j > 0 && aux > 0) {
        //    if (s.charAt(i-1) == t.charAt(j-1)) {
        //        reversedLCS.append(t.charAt(j-1));
        //        i--;
        //        j--;
        //        aux--;
        //    } else if (opt[i - 1][j] > opt[i][j - 1]) i--;
        //    else j--;
        //}

        // computing the LCS string using opt:
        //String lcs = "";
        //int i = 0, j = 0, ind = opt[0][0];
        //while (i < M-1 && j < N-1) {// && ind > 0) {
        //    if (s.charAt(i+1) == t.charAt(j+1)) {
        //        lcs += s.charAt(i+1);
        //        i++;
        //        j++;
        //        //ind--;
        //    } else if (opt[i+1][j] > opt[i][j+1]) i++;
        //    else j++;
        //}
        String lcs = "";
        return lcs;
    }

    public static void main(String[] args) {

        StdOut.println(LCS("acg","ggatacg"));
    }
}