/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E06
 * Data: <03/10/2021>
 * 
 * Baseado em:
 *     
 *     - Programa DivideBrute.java, apresentado no enunciado.
 *     - Programa Schedule.java, mostrado na aula do dia 23/09/2021.
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Divide {

    private static boolean verbose;
    private static int best = 0;
    private static int[] b;
    private static int lowerBound;
	private static int upperBound;
    private static int sum;
	private static int A = 0;
	private static int B = 0;
	private static int C = 0;
	private static boolean found = false;
    
    public static void enumerate(int[] val, int[] a, int k) {
		if (!found) {		
			if (k == a.length) {
	    	    int t = min(val, a);
				if (t >= lowerBound && t > best) {
	    		    best = t;
					found = true;
	    		    copy(b, a);
	    	    }
	    	    return;
	    	} 

			if (max(val, a, k) > upperBound) return;
		
			int auxA = A, auxB = B, auxC = C;

			a[k] = 0;
	    	enumerate(val, a, k + 1);
			resetABC(auxA, auxB, auxC);

	    	a[k] = 1;
	    	enumerate(val, a, k + 1);
			resetABC(auxA, auxB, auxC);

	    	a[k] = 2;
	    	enumerate(val, a, k + 1);
			resetABC(auxA, auxB, auxC);
		}
    }

	private static int min(int[] val, int[] a) {
		int last = val.length-1;
		switch (a[last]) {
			case 0: A += val[last]; break;
			case 1: B += val[last]; break;
			case 2: C += val[last]; break;
		}
		
		return Math.min(Math.min(A, B), C);
	}

	private static void resetABC(int a, int b, int c) {
		A = a;
		B = b;
		C = c;
	}

    public static int schedule(int[] val) {
	    int[] a = new int[val.length];
	    b = new int[val.length];
		a[0] = 0;
	    enumerate(val, a, 1);
	    return best;
    }

	public static int max(int[] val, int[] a) {
		return max(val, a, a.length);
	}

	public static int max(int[] val, int[] a, int k) {
		if (k > 0) {
			if (a[k-1] == 0) A += val[k-1];
			else if (a[k-1] == 1) B += val[k-1];
			else C += val[k-1];
		}

		return Math.max(Math.max(A, B), C);
	}

    public static int sum(int[] val) {
	    int N = val.length, S = 0; 
	    for (int i = 0; i < N; i++) S += val[i];
	    return S;
    }

    public static void show(int[] a, int[] val) {
	for (int i = 0; i < 3; i++) 
	    show(a, val, i);
    }

    public static void show(int[] a, int[] val, int i) {    
	    int N = a.length, s;
	    StdOut.print( i + ": ");
	    s = 0;
	    for (int j = 0; j < N; j++) {
	        if (a[j] == i) {
	    	    s += val[j];
	    	    StdOut.print(val[j] + " ");
	        } 
	    }
	    StdOut.println("(sum: " + s + ")");
    }

    public static void copy(int[] a, int[] b) {
	    for (int i = 0; i < a.length; i++) a[i] = b[i];
    }     
    
    public static void main(String[] args) {
	    verbose = args.length > 0;
	    int[] val = StdIn.readAllInts();
	    
		sum = sum(val);
        lowerBound = sum/3;	
		upperBound = lowerBound + sum % 3;
        int opt = schedule(val);
	    
	    if (opt >= lowerBound) {
	        StdOut.println("There is a solution");
			if (verbose) { 
				show(b, val);
				StdOut.println("Optimal value: " + opt
					   + " (sum: " + sum + " / want: " + sum/3
					   + " / sum mod 3 = " + sum % 3 +")");
			} 
		} else {
	        StdOut.println("There is no solution");
			if (verbose) { 
				show(b, val);
				StdOut.println("Best I found (not necessarily optimal): " 
                               + opt
					           + " (sum: " + sum + " / want: " + sum/3
					           + " / sum mod 3 = " + sum % 3 +")");
			} 
		}
    }
}

