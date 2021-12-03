/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E11
 * Data: <16/11/2021>
 * 
 * Baseado em:
 *     
 *     - QuickX.java, do T08;
 *     - Quick.java de S&W.
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import pieces.Nut;
import pieces.Bolt;

public class Match {

	private static int[] nutsIndexes;
	private static int[] boltsIndexes;

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

	private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

	private static boolean less(Nut n, Bolt b) {
        return n.compareTo(b) < 0;
    }

	private static boolean less(Bolt b, Nut n) {
        return b.compareTo(n) < 0;
    }

	private static boolean equals(Nut n, Bolt b) {
        return n.compareTo(b) == 0;
    }

	private static boolean equals(Bolt b, Nut n) {
        return b.compareTo(n) == 0;
    }

	private static int partition(Nut[] nuts, Bolt pivot, int lo, int hi) {

		// Primeira solução ( funciona parcialmente ):
		int idx = lo; // pivot index after partition.
		boolean foundPivot = false;

		for (int j = lo; j < hi; j++) {
			if (equals(nuts[j],pivot) && !foundPivot) {
				exch(nuts, j, hi);
				exch(nutsIndexes, j, hi);
				foundPivot = true;
				j--;
			} else if (less(nuts[j],pivot) || equals(nuts[j],pivot)) {
				exch(nuts, idx, j);
				exch(nutsIndexes, idx, j);
				idx++;
			}
		}
		exch(nuts, idx, hi);
		exch(nutsIndexes, idx, hi);

		return idx;

		// Segunda solução (não deu tempo de terminar):
		//int i = lo, j = hi, idx = hi;
		//boolean foundPivot = false;
//
		//while (i < j) {
//
		//	while (nuts[i].compareTo(pivot) <= 0) {
		//		if (nuts[i].compareTo(pivot) == 0 && !foundPivot)
		//			foundPivot = true; idx = i;
		//		i++;
		//	}
//
		//	while (nuts[j].compareTo(pivot) > 0) {
		//		j--;
		//		if (nuts[j].compareTo(pivot) == 0 && !foundPivot)
		//			foundPivot = true; idx = j;
		//	}
		//	
		//	if (i < j) {
		//		exch(nuts, i, j);
		//		exch(nutsIndexes, i, j);
		//		i++; j--;
		//	} 
		//}
//
		//exch(nuts, idx, i);
		//exch(nutsIndexes, idx, i);
//
		//return idx;
	}

	private static int partition(Bolt[] bolts, Nut pivot, int lo, int hi) {

		// Primeira solução ( funciona parcialmente ):
		int idx = lo; // pivot index after partition.
		boolean foundPivot = false;

		for (int j = lo; j < hi; j++) {
			if (equals(bolts[j],pivot) && !foundPivot) {
				exch(bolts, j, hi);
				exch(boltsIndexes, j, hi);
				foundPivot = true;
				j--;
			} else if (less(bolts[j],pivot) || equals(bolts[j],pivot)) {
				exch(bolts, idx, j);
				exch(boltsIndexes, idx, j);
				idx++;
			}
		}
		exch(bolts, idx, hi);
		exch(boltsIndexes, idx, hi);

		return idx;

		//int i = lo, j = hi, idx = hi;
		//boolean foundPivot = false;
//
		//while (i <= j) {
//
		//	while (bolts[i].compareTo(pivot) <= 0) {
		//		if (bolts[i].compareTo(pivot) == 0 && !foundPivot)
		//			foundPivot = true; idx = i;
		//		i++;
		//	}
//
		//	while (bolts[j].compareTo(pivot) > 0) {
		//		j--;
		//		if (bolts[j].compareTo(pivot) == 0 && !foundPivot)
		//			foundPivot = true; idx = j;
		//	}
		//	
		//	if (i <= j) {
		//		exch(bolts, i, j);
		//		exch(boltsIndexes, i, j);
		//		i++; j--;
		//	} 
		//}

		//if (idx <= j)
		//exch(bolts, idx, j);
		//exch(boltsIndexes, idx, j);
//
		//return idx;
	}

	public static void sort(Nut[] nuts, Bolt[] bolts) {
        sort(nuts, bolts, 0, nuts.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Nut[] nuts, Bolt[] bolts, int lo, int hi) { 
        if (hi <= lo) return;
        int indexBoltPivot = partition(nuts, bolts[hi], lo, hi); // position of pivot after partition.
        int indexNutPivot = partition(bolts, nuts[indexBoltPivot], lo, hi);
        sort(nuts, bolts, lo, indexBoltPivot-1);
        sort(nuts, bolts, indexBoltPivot+1, hi);
    }

	public static int[] match(NutsAndBolts nab) {
		int N = nab.N();
		boolean sorted = true;

		Nut[] nuts = new Nut[N];
		Bolt[] bolts = new Bolt[N];
		nutsIndexes = new int[N];
		boltsIndexes = new int[N];

		nuts[0] = nab.nuts(0);
		bolts[0] = nab.bolts(0);

		for (int i = 1; i < N; i++) {
			nuts[i] = nab.nuts(i);
			bolts[i] = nab.bolts(i);
			if (sorted) {
				if (nuts[i-1].compareTo(bolts[i-1]) != 0 || nuts[i-1].compareTo(bolts[i]) > 0 || bolts[i-1].compareTo(nuts[i]) > 0)
					sorted = false; 
			}
			nutsIndexes[i] = boltsIndexes[i] = i;
		}
	
		if (!sorted) sort(nuts,bolts);

		int[] p = new int[N];
		for (int i = 0; i < N; i++) p[nutsIndexes[i]] = boltsIndexes[i];

		return p;
	}
} 
