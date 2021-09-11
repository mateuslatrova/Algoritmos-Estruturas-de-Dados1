/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E02
 * Data: <24/08/2021>
 * 
 * Baseado em:
 * 
 *    - BouncingBall.java, MouseFollower.java e OneSimpleAttractor.java de Sedgewick & Wayne.
 *    - método signum: https://www.geeksforgeeks.org/java-signum-method-examples/
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

 import edu.princeton.cs.algs4.StdDraw;

public class Billiards {
    
    public static void main(String[] args) {

        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        double rx = 0.5, ry = 0.5;   
        double vx = 0.1, vy = -0.1;  
        double radius = 0.05;        
        double mass = 1.0;           
        double dt   = 0.5;           
        double drag = 0.02;          
        
        // Animação:
        while (true)  { 

            double fx = 0, fy = 0; // forças em x e y.
    
            if (StdDraw.isMousePressed()) {
                double mx = StdDraw.mouseX();
                double my = StdDraw.mouseY();
                double dx = rx - mx;
                double dy = ry - my;
                double signx = Math.signum(dx);
                double signy = Math.signum(dy);
                fx = signx*(5e-5/Math.pow(dx,2)) - (drag * vy);
                fy = signy*(5e-5/Math.pow(dy,2)) - (drag * vx);
            } else {    
                fx = - (drag * vx);
                fy = - (drag * vy);
            }

            // Atualizando a velocidade:
            vx += fx * dt / mass;
            vy += fy * dt / mass;

            // Verificando limite da velocidade:
            if (Math.abs(vx) > 1.0) vx = 1.0;
            if (Math.abs(vy) > 1.0) vy = 1.0;

            // Verificando ultrapassagem dos limites da parede:
            if (Math.abs(rx + vx*dt) > 1.0 - radius) vx = -vx;
            if (Math.abs(ry + vy*dt) > 1.0 - radius) vy = -vy;
 
            // Atualizando a posição:
            rx += vx * dt;
            ry += vy * dt;

            StdDraw.clear(StdDraw.LIGHT_GRAY);
            StdDraw.setPenColor(StdDraw.BLACK); 
            StdDraw.filledCircle(rx, ry, radius); 
            StdDraw.show();
            StdDraw.pause(20);
        } 
    } 
}
