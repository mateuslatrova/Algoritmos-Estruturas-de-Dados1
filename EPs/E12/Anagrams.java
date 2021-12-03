/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E12
 * Data: <27/11/2021>
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

import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Merge;

public class Anagrams {

    public class QueueStrings implements Comparable<QueueStrings> {

        private Queue<String> q = new Queue<String>();

        public void enqueue(String s) {
            q.enqueue(s);
        }

        public String dequeue() {
            return q.dequeue();
        }

        public String peek() {
            return q.peek();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }

        public int size() {
            return q.size();
        }

        public int compareTo(QueueStrings q2) {
            return q.peek().compareTo(q2.peek());
        }
    }
    
    private void run(String[] args) {
        int k = 2; 
        if (args.length > 0)
            k = Integer.parseInt(args[0]);

        String[] words = StdIn.readAllStrings();
        int N = words.length;

        SignedWord[] signedWords = new SignedWord[N];
        
        for (int i = 0; i < N; i++) {
            signedWords[i] = new SignedWord(words[i]);
        }

        Quick3way.sort(signedWords);

        Queue<QueueStrings> out = new Queue<QueueStrings>();

        int marker = 0, i = 0;
        for (i = 0; i < N; i++) {
            if (signedWords[i].compareTo(signedWords[marker]) != 0) {
                String[] currentAnagrams = new String[i-marker];
                
                for (int a = 0; a < i-marker; a++) 
                    currentAnagrams[a] = signedWords[marker+a].word();
                Quick3way.sort(currentAnagrams);

                QueueStrings newLine = new QueueStrings();
                for (String anagram: currentAnagrams) newLine.enqueue(anagram);
                out.enqueue(newLine);

                marker = i;
            }
        }

        // Repetindo para o último conjunto de anagramas
        String[] currentAnagrams = new String[i-marker]; 
        for (int a = 0; a < i-marker; a++) 
            currentAnagrams[a] = signedWords[a].word();
        Quick3way.sort(currentAnagrams);
        QueueStrings newLine = new QueueStrings();
        for (String anagram: currentAnagrams) newLine.enqueue(anagram);
        out.enqueue(newLine);

        int numSignatures = out.size();
        QueueStrings[] outArr = new QueueStrings[numSignatures];
        for (int j = 0; j < numSignatures; j++) {
            outArr[j] = out.dequeue();
        }

        int[] permutation = Merge.indexSort(outArr);

        for (int j = 0; j < numSignatures; j++) {
            QueueStrings currentQueue = outArr[permutation[j]];
            if (currentQueue.size() >= k) {
                StdOut.print("+");
                while (!currentQueue.isEmpty()) 
                    StdOut.printf(" " + currentQueue.dequeue());
                StdOut.println();
            }
        }
    }

    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        anagrams.run(args);
    }
}
