/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E08
 * Data: <17/10/2021>
 * 
 * Baseado em:
 *     
 *     - método count do programa SumSearch.java(T05)
 *     - programas ThreeSumDeluxe.java, dado no enunciado.
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSumSuper {

    public static void printSums(int[] a, int x, int lo, int hi) {
        while (lo < hi) {
            if (a[hi] + a[lo] == x) {
                if (a[hi] != a[lo]) {
                    int numLos = 0, numHis = 0;
                    int first = a[lo], sec = a[hi];
                    while (a[lo] == first) {
                        numLos++; 
                        lo++;
                    }
                    while (a[hi] == sec) {
                        numHis++; 
                        hi--;
                    }
                    for (int i = 0; i < numLos*numHis; i++) StdOut.printf("%d %d %d\n", -x, first, sec);
                    continue;
                } else {
                    int reps = hi-lo+1;
                    int combs = reps*(reps-1)/2;
                    for (int i = 0; i < combs; i++) StdOut.printf("%d %d %d\n", -x, a[lo], a[hi]);
                    break; // add reps choose 2
                }  
            }
            if (a[hi] + a[lo] < x) lo++;
            else hi--;
        }
    }

    // print distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);

        for (int i = 0; i < n-1; i++) {
            printSums(a, -a[i], i+1, n-1);
        }
    } 

    public static int checkSums(int[] a, int x, int lo, int hi) {
        int t = 0;
        while (lo < hi) {
            if (a[hi] + a[lo] == x ) {
                if (a[hi] != a[lo]) {
                    int numLos = 0, numHis = 0;
                    int first = a[lo], sec = a[hi];
                    while (a[lo] == first) {
                        numLos++; 
                        lo++;
                    }
                    while (a[hi] == sec) {
                        numHis++; 
                        hi--;
                    }
                    t += numLos*numHis;
                    continue;
                } else {
                    int reps = hi-lo+1;
                    t += reps*(reps-1)/2;
                    break; // add reps choose 2
                }  
            }
            if (a[hi] + a[lo] < x) lo++;
            else hi--;  
        }
        return t;
    }

    // return number of distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);

        int count = 0;
        for (int i = 0; i < n-1; i++) {
            count += checkSums(a, -a[i], i+1, n-1);
        }
        return count;
    } 

    public static void main(String[] args)  { 
        int[] a = StdIn.readAllInts();
	    int count = count(a);
        StdOut.println(count);
        if (count < 30) printAll(a);
    }
}
