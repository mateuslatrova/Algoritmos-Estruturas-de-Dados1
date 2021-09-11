/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E01
 * Data: <24/08/2021>
 * 
 * Baseado em:
 *    - E01 de MAC0323, feito por mim no último semestre.
 *    - https://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html -
 *  conhecimento da classe In e seus métodos.
 *    - https://www.w3schools.com/java/ref_string_compareto.asp - para 
 *   conhecer o funcionamento do método compareTo().
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Ortho {
    public static int binarySearch(String[] list, String word) {
        int left = 0;
        int right = list.length - 1;
        
        while (left <= right) {
            int middle = left+(right-left)/2;
            if (word.compareTo(list[middle]) < 0) 
                right = middle - 1;
            else if (word.compareTo(list[middle]) > 0)
                left = middle + 1;
            else 
                return middle;
        }
        return -1;
    }
    
    // Função do programa GetWords.java:
    public static String[] words(String s) {
		String r = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
		s = s.replaceAll("[^A-Za-z" + r + "\\s]", " ");
		s = s.trim();
		String[] w = s.split("\\s+");
		return w;
    }

    public static void main(String[] args) {
	    
        In dictFile = new In(args[0]);
        String[] dict = dictFile.readAllLines();

        String text = StdIn.readAll();
        String[] textWords = words(text);

	    for (String word: textWords) 
        {
            String lower = word.toLowerCase();
            String upper = word.toUpperCase();
            
            char firstLetterCap = Character.toUpperCase(word.charAt(0));
            StringBuilder titleBuilder = new StringBuilder(lower);
            titleBuilder.setCharAt(0, firstLetterCap);
            String title = titleBuilder.toString();

            // Caso 1: palavra em lower case:
            if (word.compareTo(lower) == 0) {
                if (binarySearch(dict, lower) == -1) StdOut.println(word);
            
            // Caso 2: palavra em title case:
            } else if (word.compareTo(title) == 0) {
                if (binarySearch(dict, title) == -1 && binarySearch(dict, lower) == -1) StdOut.println(word);
            
            // Caso 3: palavra em upper case:
            } else if (word.compareTo(upper) == 0){
                if (binarySearch(dict, upper) == -1 && binarySearch(dict, lower) == -1 && binarySearch(dict, title) == -1) StdOut.println(word);
            
            // Caso 4: case aleatório:
            } else {
                if (binarySearch(dict, word) == -1 && binarySearch(dict, lower) == -1 && binarySearch(dict, title) == -1) StdOut.println(word);
            }
        }
    }
}
