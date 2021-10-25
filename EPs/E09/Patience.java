/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E09
 * Data: <25/10/2021>
 * 
 * Baseado em:
 *     
 *     - Dica do T06.
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Patience {
    
    private enum DisplayMode { sizeOnly, sizeAndLIS, complete }

    private static Stack<Integer[]>[] stacks; // each card placed onto the stack will
                                              // contain the number of the current card  
                                              // (first element) and the number of the 
                                              // top card from the previous stack(second
                                              // element).
    private static int numStacks;                                            
    private static DisplayMode mode;

    private static int findLeftMostStack(int number) {
        int left = 0;
        int right = numStacks - 1;
        int index = numStacks;
        
        while (left <= right) {
            int middle = left+(right-left)/2;
            int top = stacks[middle].peek()[0]; 
            if (number <= top) {
                index = middle;
                right = middle - 1;
            } else left = middle + 1;
        }
        return index;
    }

    private static void setup(int n) {
        stacks = (Stack<Integer[]>[]) new Stack[n];
        for (int i = 0; i < n; i ++) {
            stacks[i] = new Stack<Integer[]>();
        }
        numStacks = 0;
    }

    private static void solve(int[] seq) {
        int n = seq.length;
        setup(n);

        for (int number: seq) {
            int stackIndex = findLeftMostStack(number);

            Integer[] newCard = new Integer[2]; 
            if (stackIndex == 0) { 
                newCard[0] = number;
                newCard[1] = -1; 
            } else { 
                newCard[0] = number;
                newCard[1] = stacks[stackIndex-1].peek()[0]; // top of previous stack.
            }
            
            if (stackIndex == numStacks) numStacks++;
            
            stacks[stackIndex].push(newCard);
        }       
    }

    private static Stack<Integer> getLIS() {
        Stack<Integer> LIS = new Stack<Integer>();
        Integer currNumber = stacks[numStacks-1].peek()[0];
        Integer nextNumber = stacks[numStacks-1].peek()[1];

        for (int i = numStacks-2; i >= 0; i--) {
            LIS.push(currNumber);
            boolean foundNext = false;
            while (!foundNext) {
                Integer[] top = stacks[i].pop();
                if (top[0].compareTo(nextNumber) == 0) {
                    foundNext = true;
                    currNumber = top[0];
                    nextNumber = top[1];
                }
            }
        }
        LIS.push(currNumber);

        return LIS;
    }

    private static void showResults(int[] seq) {
        switch (mode) {
            case sizeOnly:
                StdOut.printf("LIS: %d elements\n", numStacks);
                break;
            case sizeAndLIS:
                {
                    StdOut.println("LIS:");
                    Stack<Integer> LIS = getLIS();
                    int j = 0;
                    for (int i = 0; i < numStacks; i++) {
                        StdOut.printf("%d: ", i);
                        int currNumber = LIS.pop();
                        while (seq[j] != currNumber) j++;
                        StdOut.printf("%d / %d\n", j, currNumber);
                    }
                
                    StdOut.printf("LIS: %d elements\n", numStacks);
                }
                break;
            case complete:
                {
                    for (int i = 0; i < numStacks; i++) {
                        Stack<Integer> currStack = new Stack<Integer>();
                        StdOut.printf(Integer.toString(i) + ": ");
                        for (Integer[] card: stacks[i]) currStack.push(card[0]);
                        for (Integer number: currStack) StdOut.printf("%d ", number);
                        StdOut.println(); // skip line;
                    }

                    StdOut.println("LIS:");
                    Stack<Integer> LIS = getLIS();
                    int j = 0;
                    for (int i = 0; i < numStacks; i++) {
                        StdOut.printf("%d: ", i);
                        int currNumber = LIS.pop();
                        while (seq[j] != currNumber) j++;
                        StdOut.printf("%d / %d\n", j, currNumber);
                    }

                    StdOut.printf("LIS: %d elements\n", numStacks);
                }
                break;
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) mode = DisplayMode.sizeOnly;
        else if (args[0].compareTo("+") == 0) mode = DisplayMode.sizeAndLIS;
        else mode = DisplayMode.complete;

        int[] sequence = StdIn.readAllInts();

        solve(sequence);

        showResults(sequence);
    }
}
